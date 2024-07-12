package com.paymentGateway.PaymentGatewayService.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class PaymentDetails {
    @NotNull("Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private Double amount;

    @NotBlank(message = "To Currency is required")
    private String toCurrency;

    @NotBlank(message = "From Currency is required")
    private String fromCurrency;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @NotNull("User details are required")
    private UserDetails userDetails;
}
