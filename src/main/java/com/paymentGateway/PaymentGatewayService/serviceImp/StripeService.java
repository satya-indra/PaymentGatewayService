package com.paymentGateway.PaymentGatewayService.serviceImp;

import com.paymentGateway.PaymentGatewayService.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Service
public class StripeService implements PaymentService {

    @Override
    public Map<String, String> createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws  StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) (total * 100)) // amount in cents
                .setCurrency(currency)
                .setDescription(description)
                .putMetadata("order_id", "6735")
                .build();
        Charge charge = Charge.create(params);
        return Map.of( "transcationId",charge.getId(), "status",charge.getStatus(),"createDate", new Date().toString(), "updateDate", new Date().toString());

    }

    @Override
    public  Map<String, String> executePayment(String paymentId, String payerId) {
        // Implementation details for Stripe...
        return null;
    }
}
