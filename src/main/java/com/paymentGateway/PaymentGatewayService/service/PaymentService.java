package com.paymentGateway.PaymentGatewayService.service;

public interface PaymentService {
    Object createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws Exception;
    Object executePayment(String paymentId, String payerId) throws Exception;
}
