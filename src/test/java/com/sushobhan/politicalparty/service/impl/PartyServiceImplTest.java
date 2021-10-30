package com.sushobhan.politicalparty.service.impl;

import com.sushobhan.politicalparty.entity.PartyEntity;
import com.sushobhan.politicalparty.entity.PoliticalLeaderEntity;
import com.sushobhan.politicalparty.payload.PartyDto;
import com.sushobhan.politicalparty.repository.PartyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PartyServiceImplTest {

    private final PartyRepository partyRepository = Mockito.mock(PartyRepository.class);
    private PartyServiceImpl partyService;

    @Captor
    private ArgumentCaptor<PartyEntity> argumentCaptor;

    @BeforeEach
    void setUp() {
        partyService = new PartyServiceImpl(partyRepository);
    }

    @Test
    void createParty() {
        PartyEntity expectedResponse = new PartyEntity(123L, "AIADMK Party", "Jayalalitha", 1985D);
        PartyDto partyDto = new PartyDto(123L, "AIADMK Party", "Jayalalitha", 1985D);
        Mockito.when(partyRepository.save(Mockito.any(PartyEntity.class))).thenReturn(expectedResponse);

        // actual
        PartyDto actualResponse = partyService.createParty(partyDto);
        Assertions.assertThat(actualResponse.getId()).isEqualTo(expectedResponse.getId());
        Assertions.assertThat(actualResponse.getPartyName()).isEqualTo(expectedResponse.getPartyName());
        Assertions.assertThat(actualResponse.getFounderName()).isEqualTo(expectedResponse.getFounderName());
        Assertions.assertThat(actualResponse.getFoundationYear()).isEqualTo(expectedResponse.getFoundationYear());

        Mockito.verify(partyRepository, Mockito.times(1)).save(ArgumentMatchers.any(PartyEntity.class));
    }

    @Test
    void createPartyWithArgumentCaptor() {
        PartyEntity expectedResponse = new PartyEntity(123L, "AIADMK Party", "Jayalalitha", 1985D);
        PartyDto partyDto = new PartyDto(123L, "AIADMK Party", "Jayalalitha", 1985D);
        Mockito.when(partyRepository.save(Mockito.any(PartyEntity.class))).thenReturn(expectedResponse);

        // actual
        PartyDto actualResponse = partyService.createParty(partyDto);

        Mockito.verify(partyRepository, Mockito.times(1)).save(argumentCaptor.capture());

        Assertions.assertThat(argumentCaptor.getValue().getId()).isEqualTo(123L);
        Assertions.assertThat(argumentCaptor.getValue().getPartyName()).isEqualTo("AIADMK Party");
        Assertions.assertThat(argumentCaptor.getValue().getFounderName()).isEqualTo("Jayalalitha");
        Assertions.assertThat(argumentCaptor.getValue().getFoundationYear()).isEqualTo(1985D);
    }
}