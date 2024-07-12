package com.paymentGateway.PaymentGatewayService.serviceImp;

import com.paymentGateway.PaymentGatewayService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFactory {

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private StripeService stripeService;

    public PaymentService getPaymentService(String serviceType) {
        if ("PayPal".equalsIgnoreCase(serviceType)) {
            return payPalService;
        } else if ("Stripe".equalsIgnoreCase(serviceType)) {
            return stripeService;
        }
        throw new IllegalArgumentException("Unknown payment service type: " + serviceType);
    }
}
