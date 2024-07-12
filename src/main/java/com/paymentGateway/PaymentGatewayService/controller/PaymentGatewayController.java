package com.paymentGateway.PaymentGatewayService.controller;

import com.paymentGateway.PaymentGatewayService.model.PaymentDetails;
import com.paymentGateway.PaymentGatewayService.model.TransactionResponse;
import com.paymentGateway.PaymentGatewayService.serviceImp.PaymentProcessor;
import com.paymentGateway.PaymentGatewayService.serviceImp.PaymentServiceFactory;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1")
public class PaymentGatewayController {

    @Autowired
    private PaymentProcessor paymentProcessor;



    @PostMapping("/processPayment")
    @Operation(summary = "process the transactions")
    public TransactionResponse processPayment(@RequestBody PaymentDetails paymentDetails) {
        CompletableFuture<String> paymentStatus = paymentProcessor.processPayment(paymentDetails);
        // Logic to handle payment processing and integration
        // Return transaction status and confirmation details
         return new TransactionResponse(paymentStatus.isDone(), "Transaction Successfully");
      }

}
