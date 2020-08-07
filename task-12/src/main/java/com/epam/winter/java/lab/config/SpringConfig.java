package com.epam.winter.java.lab.config;

import com.epam.winter.java.lab.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    User getUser(){
        return new User();
    }

    @Bean
    Author getAuthor(){
        return new Author();
    }

    @Bean
    Book getBook(){
        return new Book();
    }

    @Bean
    Delivery getDeliveryBook(){
        return new Delivery();
    }

    @Bean
    SubscriptionCard getSubscriptionCard(){
        return new SubscriptionCard();
    }
}
