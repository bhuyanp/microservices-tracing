package com.example.microservice.customer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
@OpenAPIDefinition(
        info = @Info(title = "Customer API",
                description = """
                ##### Customer REST API comes with all basic operations for customers
                """,
                version = "1.0",
                contact=@Contact(name="Prasanta Bhuyan",email = "prasanta.k.bhuyan@gmail.com"))
)
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }

    @Bean
    public CommandLineRunner getCommandLineRunner(){
        return args -> System.out.println("Application URL: http://localhost:8080/");
    }


    @GetMapping
    public String home(){
        return "redirect:/swagger-ui/index.html";
    }
}
