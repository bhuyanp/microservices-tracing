package com.example.microservice.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Customer Service")
@CrossOrigin("localhost:8080")
public class CustomerController {
    private final CustomerService customerService;

    public static final String URI = "/api/v1/customer";

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path=URI, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all customers")
    public List<Customer> getAllCustomers(){
       return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Add new customer")
    @PostMapping(path = URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get customer by id")
    @GetMapping(path=URI+"/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id){
        ResponseEntity responseEntity;
        return customerService.getCustomer(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update customer credit score")
    @PatchMapping(path=URI+"/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomerCreditScore(@PathVariable int id, @RequestParam(required = true) int creditScore){
        log.info("Updating credit score for {} with score {}",id,creditScore);
        return customerService.updateCustomerCreditScore(id, creditScore)
                .map(customer-> ResponseEntity.ok(customer))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

}
