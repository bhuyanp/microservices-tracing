package com.example.microservice.customer;

import com.example.microservice.customer.repo.CustomerRepo;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import io.micrometer.tracing.SpanName;
import io.micrometer.tracing.annotation.NewSpan;
import io.micrometer.tracing.annotation.SpanTag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
@Observed(name = "customerSvc")
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final WebClient.Builder webClientBuilder;
    private final ObservationRegistry observationRegistry;

    @Lazy
    @Resource
    private CustomerService customerService;


    public Customer addCustomer(Customer pCustomer) {
        Customer customer = customerService.saveCustomer(pCustomer);
        //CompletableFuture.runAsync(()->requestCreditScore(customer));
        customerService.requestCreditScore(customer);
        return customer;
    }

    public Customer saveCustomer(Customer pCustomer) {
        log.info("Saving customer.");
        return customerRepo.save(pCustomer);
    }




    public void requestCreditScore(Customer pCustomer) {
        log.info("Requesting credit report for  customer:{}", pCustomer);

        webClientBuilder.build()
            .post()
            .uri("http://localhost:8080/api/v1/credit-report")
            .bodyValue(pCustomer)
            .retrieve()
            .bodyToMono(Integer.class).subscribe(creditScore -> {
                log.info("Received credit score:{}", creditScore);
                updateCustomerCreditScore(pCustomer.getId(), creditScore);
            });
    }

    public Optional<Customer> updateCustomerCreditScore(int id, int creditScore) {
        log.info("Updating credit score for {}", id);
        return customerRepo.findById(id)
                .map((customer) -> {
                    customer.setCreditScore(creditScore);
                    return customerRepo.save(customer);
                });
    }


    public Optional<Customer> getCustomer(int id) {
        return customerRepo.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }
}
