package com.example.microservice.customer;

import com.example.microservice.customer.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final WebClient webClient;

    public Customer addCustomer(Customer pCustomer) {
        log.info("Adding customer:{}", pCustomer);
        Customer customer = customerRepo.save(pCustomer);
        webClient.post()
                .uri("http://localhost:8080/api/v1/credit-report")
                .bodyValue(customer)
                .exchangeToMono(Mono::just).block();
        return customer;

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
