package com.paymentGateway.PaymentGatewayService.serviceImp;

import com.paymentGateway.PaymentGatewayService.model.PaymentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessor {
    @Autowired
    private PaymentServiceFactory paymentServiceFactory;
    public boolean processPayment(PaymentDetails paymentDetails) {
        return false;
    }
}
