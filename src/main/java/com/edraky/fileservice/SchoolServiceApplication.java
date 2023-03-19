package com.edraky.fileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SchoolServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolServiceApplication.class, args);
    }


}
