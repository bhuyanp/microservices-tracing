package com.example.microservice.creditreport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.random.RandomGenerator;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CreditReportController {
    public static final String URI = "/api/v1/credit-report";
    private final CreditReportService creditReportService;

    @PostMapping(path = URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public int getCreditScore(@RequestBody Customer customer){
        log.info("Checking credit history for customer {}",customer);
        creditReportService.updateCreditScore(customer);
        return RandomGenerator.getDefault().nextInt();
    }

    @GetMapping(path = URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public int getCreditScore(){
        log.info("Inside get credit score");
        return RandomGenerator.getDefault().nextInt();
    }

}
