package com.hdecoded.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
    @Scope("prototype")
    public OrderService orderService(){
        if(paymentProvider.equals("stripe"))
            return new OrderService(stripe());
        else if(paymentProvider.equals("paypal"))
            return new OrderService(paypal());
        return new OrderService(stripe());
    }
}
