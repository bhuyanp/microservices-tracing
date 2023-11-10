package com.example.microservice.creditreport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.random.RandomGenerator;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreditReportService {
    private final WebClient webClient;
    public void updateCreditScore(Customer customer){
        log.info("Sending credit score to customer service for customer "+customer);
        webClient
                .patch()
                .uri("http://localhost:8080/api/v1/customer/"+customer.getId()+"?creditScore="+ RandomGenerator.getDefault().nextInt(600,10))
                .exchangeToMono(result-> Mono.just(result)).block();

    }
}
