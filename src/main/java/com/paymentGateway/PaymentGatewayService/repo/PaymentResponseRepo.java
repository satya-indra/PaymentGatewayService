package com.paymentGateway.PaymentGatewayService.repo;

import com.paymentGateway.PaymentGatewayService.model.PaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentResponseRepo extends JpaRepository<PaymentResponse, Long> {
}
