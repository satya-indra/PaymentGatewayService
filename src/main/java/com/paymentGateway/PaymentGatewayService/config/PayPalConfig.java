package com.paymentGateway.PaymentGatewayService.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {

    private String clientId = "YOUR_CLIENT_ID";
    private String clientSecret = "YOUR_CLIENT_SECRET";
    private String mode = "sandbox"; // Use "sandbox" for testing and "live" for production

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(clientId, clientSecret, mode);
        return context;
    }
}
