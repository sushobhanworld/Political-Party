package com.sushobhan.politicalparty.service.impl;

import com.sushobhan.politicalparty.entity.PartyEntity;
import com.sushobhan.politicalparty.entity.PoliticalLeaderEntity;
import com.sushobhan.politicalparty.exception.ResourceNotFoundException;
import com.sushobhan.politicalparty.payload.PoliticalLeaderDto;
import com.sushobhan.politicalparty.repository.PartyRepository;
import com.sushobhan.politicalparty.repository.PoliticalLeaderRepository;
import com.sushobhan.politicalparty.utlils.Mapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class PoliticalLeaderServiceImplTest {

    private final PartyRepository partyRepository = Mockito.mock(PartyRepository.class);
    private final PoliticalLeaderRepository politicalLeaderRepository = Mockito.mock(PoliticalLeaderRepository.class);
    private final Mapper mapper = Mockito.mock(Mapper.class);

    @Captor
    private ArgumentCaptor<PoliticalLeaderEntity> argumentCaptor;

    PoliticalLeaderServiceImpl politicalLeaderService;

    @BeforeEach
    void setUp() {
        politicalLeaderService = new PoliticalLeaderServiceImpl(partyRepository, politicalLeaderRepository);
    }


    @Test
    @DisplayName("Should delete party leader from political party")
    void deletePartyLeaderFromParty() {
        PoliticalLeaderEntity politicalLeaderEntity = new PoliticalLeaderEntity(123L, 2L, "Kumar Sushobhan", "West Bengal");

        PoliticalLeaderDto expectedResponse = new PoliticalLeaderDto(2L, "Kumar Sushobhan", "West Bengal");

        Mockito.when(politicalLeaderRepository.findById(123L)).thenReturn(Optional.of(politicalLeaderEntity));
        Mockito.when(mapper.mapToDTO(Mockito.any(PoliticalLeaderEntity.class))).thenReturn(expectedResponse);

        politicalLeaderService.deletePartyLeaderFromParty(123L);

        Mockito.verify(politicalLeaderRepository, Mockito.times(1)).delete(ArgumentMatchers.any(PoliticalLeaderEntity.class));
    }

    @Test
    @DisplayName("Should delete party leader from political party with argument captor")
    void deletePartyLeaderFromPartyWithArgumentCaptor() {
        PoliticalLeaderEntity politicalLeaderEntity = new PoliticalLeaderEntity(123L, 2L, "Kumar Sushobhan", "West Bengal");

        PoliticalLeaderDto expectedResponse = new PoliticalLeaderDto(2L, "Kumar Sushobhan", "West Bengal");

        Mockito.when(politicalLeaderRepository.findById(123L)).thenReturn(Optional.of(politicalLeaderEntity));
        Mockito.when(mapper.mapToDTO(Mockito.any(PoliticalLeaderEntity.class))).thenReturn(expectedResponse);

        politicalLeaderService.deletePartyLeaderFromParty(123L);

        Mockito.verify(politicalLeaderRepository, Mockito.times(1)).delete(argumentCaptor.capture());

        Assertions.assertThat(argumentCaptor.getValue().getPartyId()).isEqualTo(2L);
        Assertions.assertThat(argumentCaptor.getValue().getId()).isEqualTo(123L);
        Assertions.assertThat(argumentCaptor.getValue().getLeaderName()).isEqualTo("Kumar Sushobhan");
        Assertions.assertThat(argumentCaptor.getValue().getLeaderState()).isEqualTo("West Bengal");
    }

    @Test
    @DisplayName("Should register leader to a political party")
    void registerLeaderToParty() {
        PartyEntity partyEntity = new PartyEntity(246L, "India Party", "Ram Kumar", 1996D);
        PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto(2L, "Kumar Sushobhan", "West Bengal");
        PoliticalLeaderEntity expectedResponse = new PoliticalLeaderEntity(246L, 2L, "Kumar Sushobhan", "West Bengal");

        Mockito.when(partyRepository.findById(2L)).thenReturn(Optional.of(partyEntity));
        Mockito.when(politicalLeaderRepository.save(Mockito.any())).thenReturn(expectedResponse);

        PoliticalLeaderDto actualResponse = politicalLeaderService.registerLeaderToParty(politicalLeaderDto);
        Assertions.assertThat(actualResponse.getPartyId()).isEqualTo(expectedResponse.getPartyId());
        Assertions.assertThat(actualResponse.getLeaderName()).isEqualTo(expectedResponse.getLeaderName());
        Assertions.assertThat(actualResponse.getLeaderState()).isEqualTo(expectedResponse.getLeaderState());
    }

    @Test
    @DisplayName("Should throw exception when PartyID is not found")
    void throwExceptionWhenPartyIdIsNotFound() {
        PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto(2L, "Kumar Sushobhan", "West Bengal");
        Mockito.when(partyRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> politicalLeaderService.registerLeaderToParty(politicalLeaderDto))
                .isInstanceOf(ResourceNotFoundException.class);

        then(politicalLeaderRepository).shouldHaveNoInteractions();
    }
}