package com.paymentGateway.PaymentGatewayService.controller;

import com.paymentGateway.PaymentGatewayService.model.PaymentDetails;
import com.paymentGateway.PaymentGatewayService.model.TransactionResponse;
import com.paymentGateway.PaymentGatewayService.serviceImp.PaymentProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PaymentGatewayControllerTest {

    @InjectMocks
    private PaymentGatewayController paymentGatewayController;

    @Mock
    private PaymentProcessor paymentProcessor;

    public PaymentGatewayControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessPayment() {
        // Arrange
        PaymentDetails paymentDetails = Mockito.mock(PaymentDetails.class);
        CompletableFuture<String> paymentStatus = CompletableFuture.completedFuture("Payment Successful");
        when(paymentProcessor.processPayment(paymentDetails)).thenReturn(paymentStatus);

        // Act
        TransactionResponse response = paymentGatewayController.processPayment(paymentDetails);

        // Assert
        assertTrue(response.isPaymentStatus());
        assertEquals("Transaction Successfully", response.getResponse());
    }
}


