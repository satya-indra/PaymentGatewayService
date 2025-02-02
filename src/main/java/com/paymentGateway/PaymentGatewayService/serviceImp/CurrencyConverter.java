package com.paymentGateway.PaymentGatewayService.serviceImp;

import com.paymentGateway.PaymentGatewayService.config.CurrencyConvertorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyConverter {
    @Autowired
    private CurrencyConvertorConfig config;
    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s", config.getApiUrl(), fromCurrency);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Double> rates = (Map<String, Double>) response.get("rates");
        double rate =  rates.get(toCurrency);

        return amount * rate;
    }
}
