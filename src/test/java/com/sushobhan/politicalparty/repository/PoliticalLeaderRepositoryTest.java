package com.sushobhan.politicalparty.repository;

import com.sushobhan.politicalparty.entity.PoliticalLeaderEntity;
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
public class PoliticalLeaderRepositoryTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("political-party")
            .withUsername("root")
            .withPassword("root");

    @Autowired
    private PoliticalLeaderRepository politicalLeaderRepository;

    @Test
    void testRegisterLeaderToParty(){
        PoliticalLeaderEntity expectedPoliticalLeaderEntity = PoliticalLeaderEntity.builder()
                .partyId(123L)
                .leaderName("Kumar Sushobhan")
                .leaderState("Karnataka")
                .build();
        PoliticalLeaderEntity actualPoliticalLeaderEntity = politicalLeaderRepository.save(expectedPoliticalLeaderEntity);
        Assertions.assertThat(actualPoliticalLeaderEntity)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedPoliticalLeaderEntity);
    }


}