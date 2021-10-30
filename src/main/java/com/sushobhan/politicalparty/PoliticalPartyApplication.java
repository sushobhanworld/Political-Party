package com.sushobhan.politicalparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class PoliticalPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoliticalPartyApplication.class, args);
    }

}
