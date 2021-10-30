package com.sushobhan.politicalparty.repository;

import com.sushobhan.politicalparty.entity.PartyEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PartyRepositoryTest {

    @Container
    MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("political-party")
            .withUsername("root")
            .withPassword("root");

    @Autowired
    private PartyRepository partyRepository;

    @Test
    void savePartyTest(){
        PartyEntity expectedPartyEntity = PartyEntity.builder()
                .partyName("DMK-Test")
                .founderName("Rajnikanth")
                .foundationYear(1995d)
                .build();
        PartyEntity actualPartyEntity = partyRepository.save(expectedPartyEntity);

        Assertions.assertThat(actualPartyEntity).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedPartyEntity);
    }
}