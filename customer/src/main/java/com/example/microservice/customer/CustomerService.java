package com.example.microservice.customer;

import com.example.microservice.customer.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepo customerRepo;

    public Customer addCustomer(Customer customer) {
        log.info("Adding customer:{}", customer);
        return customerRepo.save(customer);
    }

    public Optional<Customer> updateCustomerCreditScore(int id, int creditScore) {
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
