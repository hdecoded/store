package com.hdecoded.store.order.paymentprovider;

//@Service("paypal")
public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("PAYPAL PAYMENT SERVICE");
        System.out.println("Amount: " + amount);
    }
}
