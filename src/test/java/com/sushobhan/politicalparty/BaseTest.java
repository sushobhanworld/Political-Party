package com.sushobhan.politicalparty;

import org.testcontainers.containers.MySQLContainer;

public class BaseTest {
    static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("political-party")
            .withUsername("root")
            .withPassword("root");

    static {
        mySQLContainer.start();
    }
}
