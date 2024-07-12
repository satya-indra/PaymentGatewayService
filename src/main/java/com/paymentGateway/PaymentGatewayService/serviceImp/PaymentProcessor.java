package com.paymentGateway.PaymentGatewayService.serviceImp;

import com.paymentGateway.PaymentGatewayService.model.PaymentDetails;
import com.paymentGateway.PaymentGatewayService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentProcessor {
    @Autowired
    private PaymentServiceFactory paymentServiceFactory;
    @Async
    public CompletableFuture<String> processPayment(PaymentDetails paymentDetails) {
        PaymentService paymentService = paymentServiceFactory.getPaymentService(paymentDetails.getPaymentMethod());
        try {


            // Simulate a long-running payment processing task
            Thread.sleep(5000);

            Object result =  paymentService.createPayment(paymentDetails.getAmount(),paymentDetails.getCurrency(), "","testing","testing application","","");

            return CompletableFuture.completedFuture("Payment processed successfully with details: " + paymentDetails);
        } catch (InterruptedException e) {
            return CompletableFuture.failedFuture(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
