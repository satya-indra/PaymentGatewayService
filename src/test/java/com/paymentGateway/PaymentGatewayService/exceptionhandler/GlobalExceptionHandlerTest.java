package com.paymentGateway.PaymentGatewayService.exceptionhandler;

import com.paymentGateway.PaymentGatewayService.exceptionHandler.GlobalExceptionHandler;
import com.paymentGateway.PaymentGatewayService.exceptionHandler.PaymentServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandlePaymentServiceException() {
        // Arrange
        PaymentServiceException ex = new PaymentServiceException("Payment service exception");

        // Act
        ResponseEntity<Object> response = globalExceptionHandler.handlePaymentServiceException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Payment service exception", response.getBody());
    }

    @Test
    public void testHandleGeneralException() {
        // Arrange
        Exception ex = new Exception("General exception");

        // Act
        ResponseEntity<Object> response = globalExceptionHandler.handleGeneralException(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: General exception", response.getBody());
    }

    @Test
    public void testHandleResponseStatusException() {
        // Arrange
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");

        // Act
        ResponseEntity<Object> response = globalExceptionHandler.handleResponseStatusException(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("404 NOT_FOUND \"Resource not found\"", response.getBody());
    }
}
