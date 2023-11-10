package com.example.microservice.creditreport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int social;
    private boolean fraudDetected;
    private int creditScore;
}
