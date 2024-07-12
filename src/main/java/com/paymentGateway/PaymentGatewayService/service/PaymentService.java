package com.paymentGateway.PaymentGatewayService.service;

import java.util.Map;

public interface PaymentService {
    Map<String,String> createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws Exception;
    Map<String,String> executePayment(String paymentId, String payerId) throws Exception;
}
