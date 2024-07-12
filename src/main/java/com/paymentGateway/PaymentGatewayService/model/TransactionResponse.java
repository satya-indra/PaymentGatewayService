package com.paymentGateway.PaymentGatewayService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
     boolean paymentStatus;
     String response;
}
