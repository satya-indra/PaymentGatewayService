package com.paymentGateway.PaymentGatewayService.serviceImp;

import com.paymentGateway.PaymentGatewayService.service.PaymentService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PayPalService implements PaymentService  {
    @Autowired
    private APIContext apiContext;
    @Override
    public Map<String, String> createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {

        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(List.of(transaction));

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
        payment = payment.create(apiContext);
        return Map.of("paymentId", payment.getId(), "payer", payment.getPayer().getFundingOptionId());

    }

    @Override
    public Map<String,String> executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        payment = payment.execute(apiContext, paymentExecution);
        return  Map.of( "transcationId",payment.getId(), "status",payment.getState(),"createDate", payment.getCreateTime(), "updateDate", payment.getUpdateTime());
    }
}
