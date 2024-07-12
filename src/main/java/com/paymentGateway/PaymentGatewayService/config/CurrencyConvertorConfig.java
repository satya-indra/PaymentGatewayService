package com.paymentGateway.PaymentGatewayService.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CurrencyConvertorConfig {
    @Value("${exchange.api.key}")
    private String apiKey;

    @Value("${exchange.api.url}")
    private String apiUrl;

}
