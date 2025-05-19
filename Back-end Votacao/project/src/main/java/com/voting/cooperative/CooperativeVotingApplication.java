package com.voting.cooperative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CooperativeVotingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CooperativeVotingApplication.class, args);
    }
}