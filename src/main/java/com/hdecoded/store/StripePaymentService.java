package com.hdecoded.store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("stripe")
@Primary
public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("STRIPE PAYMENT SERVICE");
        System.out.println("Amount: " + amount);
    }
}
