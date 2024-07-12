package com.paymentGateway.PaymentGatewayService.serviceImp;

import com.paymentGateway.PaymentGatewayService.model.PaymentDetails;
import com.paymentGateway.PaymentGatewayService.model.PaymentResponse;
import com.paymentGateway.PaymentGatewayService.repo.PaymentResponseRepo;
import com.paymentGateway.PaymentGatewayService.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PaymentProcessor {
    @Autowired
    private PaymentServiceFactory paymentServiceFactory;
    @Autowired
    private PaymentResponseRepo paymentResponseRepo;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private CurrencyConverter currencyConverter;


    @Async
    public CompletableFuture<String> processPayment(PaymentDetails paymentDetails) {
        double amount = currencyConverter.convertCurrency(paymentDetails.getAmount(),paymentDetails.getFromCurrency(),paymentDetails.getToCurrency());
        log.debug("Processing payment for amount: {}", amount);

        PaymentService paymentService = paymentServiceFactory.getPaymentService(paymentDetails.getPaymentMethod());
        try {
            Thread.sleep(5000);
            Map<String,String> result =  paymentService.createPayment(amount, paymentDetails.getToCurrency(), "","testing","testing application","","");
            if(paymentDetails.getPaymentMethod().equalsIgnoreCase("paypal")) {
                result =  paymentService.executePayment(result.get("paymentId"),result.get("payer"));
            }
            String tokenizeCardDetails = tokenizeCardDetails(paymentDetails.getUserDetails().getCardNumber());
            PaymentResponse paymentResponse = new PaymentResponse();
            paymentResponse.setAmount(amount);
            paymentResponse.setCardNumber(tokenizeCardDetails);
            paymentResponse.setCreateTime(result.get("createDate"));
            paymentResponse.setUpdateTime(result.get("upadateDate"));
            paymentResponse.setExpireDate(paymentDetails.getUserDetails().getExpiryDate().toString());
            paymentResponse.setFullName(paymentDetails.getUserDetails().getCardHolderName());
            paymentResponse.setTransactionId(result.get("transactionId"));
            paymentResponse.setStatus(result.get("status"));
            paymentResponse = paymentResponseRepo.save(paymentResponse);
            log.info("Payment processed successfully with ID: {}", paymentResponse.getId());

            // publish the result to kafka
            kafkaTemplate.send("payment_topic", "Payment processed: " + paymentResponse);

            return CompletableFuture.completedFuture("Payment processed successfully with details: " + result);
        } catch (InterruptedException e) {
            return CompletableFuture.failedFuture(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String tokenizeCardDetails(String creditCardNumber) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(creditCardNumber.getBytes());

            // Combine the hash with random bytes for added security
            byte[] tokenBytes = new byte[hashedBytes.length + 24];
            secureRandom.nextBytes(tokenBytes);
            System.arraycopy(hashedBytes, 0, tokenBytes, 0, hashedBytes.length);

            return "TOKEN_" + Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash credit card number", e);
        }
    }
}
