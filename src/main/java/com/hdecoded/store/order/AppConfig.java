package com.hdecoded.store.order;

import com.hdecoded.store.order.paymentprovider.PayPalPaymentService;
import com.hdecoded.store.order.paymentprovider.PaymentService;
import com.hdecoded.store.order.paymentprovider.StripePaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${store.payProvider}")
    private String paymentProvider;

    @Bean
    public PaymentService stripe(){
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal(){
        return new PayPalPaymentService();
    }

    @Bean
    public OrderService orderService(){
        if(paymentProvider.equals("paypal"))
            return new OrderService(paypal());
        return new OrderService(stripe());
    }
}
