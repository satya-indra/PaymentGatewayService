package com.paymentGateway.PaymentGatewayService.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PaymentResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String transactionId;
    private double amount;
    private String status;
    private String createTime;
    private String updateTime;
    private String cardNumber;
    private String expireDate;
}
