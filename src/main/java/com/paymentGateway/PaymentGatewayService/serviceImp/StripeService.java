package com.paymentGateway.PaymentGatewayService.serviceImp;

import com.paymentGateway.PaymentGatewayService.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeService implements PaymentService {

    @Override
    public Charge createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws  StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) (total * 100)) // amount in cents
                .setCurrency(currency)
                .setDescription(description)
                .putMetadata("order_id", "6735")
                .build();

        return Charge.create(params);
    }

    @Override
    public Object executePayment(String paymentId, String payerId) {
        // Implementation details for Stripe...
        return null;
    }
}
