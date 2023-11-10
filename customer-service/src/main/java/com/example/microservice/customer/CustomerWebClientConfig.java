package com.example.microservice.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CustomerWebClientConfig {

    @Bean
    public WebClient getWebclient(){
        return WebClient.builder().build();
    }

}
