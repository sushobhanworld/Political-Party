package com.sushobhan.politicalparty.service.impl;

import com.sushobhan.politicalparty.entity.PoliticalLeaderDevelopmentEntity;
import com.sushobhan.politicalparty.entity.PoliticalLeaderEntity;
import com.sushobhan.politicalparty.exception.ResourceNotFoundException;
import com.sushobhan.politicalparty.payload.PoliticalLeaderDevelopmentDto;
import com.sushobhan.politicalparty.payload.PoliticalLeaderDto;
import com.sushobhan.politicalparty.repository.PoliticalLeaderDevelopmentRepository;
import com.sushobhan.politicalparty.repository.PoliticalLeaderRepository;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PoliticalLeaderDevelopmentServiceImplTest {

    private final PoliticalLeaderRepository politicalLeaderRepository = Mockito.mock(PoliticalLeaderRepository.class);
    private final PoliticalLeaderDevelopmentRepository politicalLeaderDevelopmentRepository = Mockito.mock(PoliticalLeaderDevelopmentRepository.class);

    @Captor
    private ArgumentCaptor<PoliticalLeaderEntity> argumentCaptor;

    PoliticalLeaderDevelopmentServiceImpl politicalLeaderDevelopmentService;

    @BeforeEach
    void setUp() {
        politicalLeaderDevelopmentService = new PoliticalLeaderDevelopmentServiceImpl(politicalLeaderRepository, politicalLeaderDevelopmentRepository);
    }

    @Test
    void addDevelopment() {
        PoliticalLeaderEntity politicalLeaderEntity = new PoliticalLeaderEntity(123L, 255L, "Kumar Sushobhan", "Bihar");
        List<String> activityList = Arrays.asList("road patching","hospital construction","new sewage pipeline","covid oxygen plant","dam construction");
        PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto = new PoliticalLeaderDevelopmentDto(123L, "corporation development", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);

        PoliticalLeaderDevelopmentEntity expectedResponse = new PoliticalLeaderDevelopmentEntity(2L, 123L, "corporation development", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);

        Mockito.when(politicalLeaderRepository.findById(123L)).thenReturn(Optional.of(politicalLeaderEntity));
        Mockito.when(politicalLeaderDevelopmentRepository.save(Mockito.any())).thenReturn(expectedResponse);

        PoliticalLeaderDevelopmentDto actualResponse = politicalLeaderDevelopmentService.addDevelopment(politicalLeaderDevelopmentDto);
        Assertions.assertThat(actualResponse.getPoliticalLeaderId()).isEqualTo(expectedResponse.getPoliticalLeaderId());
        Assertions.assertThat(actualResponse.getDevelopmentTitle()).isEqualTo(expectedResponse.getDevelopmentTitle());
        Assertions.assertThat(actualResponse.getActivities()).isEqualTo(expectedResponse.getActivities());
        Assertions.assertThat(actualResponse.getBudget()).isEqualTo(expectedResponse.getBudget());
        Assertions.assertThat(actualResponse.getState()).isEqualTo(expectedResponse.getState());
        Assertions.assertThat(actualResponse.getActivityYear()).isEqualTo(expectedResponse.getActivityYear());
        Assertions.assertThat(actualResponse.getActivityMonth()).isEqualTo(expectedResponse.getActivityMonth());
    }

    @Test
    void addDevelopmentWithArgumentCaptor() {
        PoliticalLeaderEntity politicalLeaderEntity = new PoliticalLeaderEntity(123L, 255L, "Kumar Sushobhan", "Bihar");
        List<String> activityList = Arrays.asList("road patching","hospital construction","new sewage pipeline","covid oxygen plant","dam construction");
        PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto = new PoliticalLeaderDevelopmentDto(123L, "corporation development", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);

        PoliticalLeaderDevelopmentEntity expectedResponse = new PoliticalLeaderDevelopmentEntity(2L, 123L, "corporation development", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);

        Mockito.when(politicalLeaderRepository.findById(123L)).thenReturn(Optional.of(politicalLeaderEntity));
        Mockito.when(politicalLeaderDevelopmentRepository.save(Mockito.any())).thenReturn(expectedResponse);

        politicalLeaderDevelopmentService.addDevelopment(politicalLeaderDevelopmentDto);

        Mockito.verify(politicalLeaderDevelopmentRepository, Mockito.times(1)).save(ArgumentMatchers.any(PoliticalLeaderDevelopmentEntity.class));

    }

    @Test
    void getAllDevelopmentWorks() {

    }

    @Test
    void updateDevelopmentWork() {
        List<String> activityList = Arrays.asList("road patching","hospital construction","new sewage pipeline","covid oxygen plant","dam construction");
        PoliticalLeaderDevelopmentEntity politicalLeaderDevelopmentEntityEXISTING = new PoliticalLeaderDevelopmentEntity(2L, 123L, "corporation development", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);

        PoliticalLeaderDevelopmentDto updatedPoliticalLeaderDevelopmentDto = new PoliticalLeaderDevelopmentDto(123L, "corporation1 development1", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);

        PoliticalLeaderDevelopmentEntity expectedResponse = new PoliticalLeaderDevelopmentEntity(2L, 123L, "corporation1 development1", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);

        Mockito.when(politicalLeaderDevelopmentRepository.findById(2L)).thenReturn(Optional.of(politicalLeaderDevelopmentEntityEXISTING));
        Mockito.when(politicalLeaderDevelopmentRepository.save(Mockito.any())).thenReturn(expectedResponse);

        PoliticalLeaderDevelopmentDto actualResponse = politicalLeaderDevelopmentService.updateDevelopmentWork(updatedPoliticalLeaderDevelopmentDto, 2L);

        Assertions.assertThat(actualResponse.getPoliticalLeaderId()).isEqualTo(expectedResponse.getPoliticalLeaderId());
        Assertions.assertThat(actualResponse.getDevelopmentTitle()).isEqualTo(expectedResponse.getDevelopmentTitle());
        Assertions.assertThat(actualResponse.getActivities()).isEqualTo(expectedResponse.getActivities());
        Assertions.assertThat(actualResponse.getBudget()).isEqualTo(expectedResponse.getBudget());
        Assertions.assertThat(actualResponse.getState()).isEqualTo(expectedResponse.getState());
        Assertions.assertThat(actualResponse.getActivityYear()).isEqualTo(expectedResponse.getActivityYear());
        Assertions.assertThat(actualResponse.getActivityMonth()).isEqualTo(expectedResponse.getActivityMonth());
    }

    @Test
    @DisplayName("Should throw exception when PoliticalLeaderID is not found")
    void throwExceptionWhenPoliticalLeaderIdIsNotFound() {
        List<String> activityList = Arrays.asList("road patching","hospital construction","new sewage pipeline","covid oxygen plant","dam construction");
        PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto = new PoliticalLeaderDevelopmentDto(123L, "corporation development", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);
        Mockito.when(politicalLeaderRepository.findById(123L)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> politicalLeaderDevelopmentService.addDevelopment(politicalLeaderDevelopmentDto))
                .isInstanceOf(ResourceNotFoundException.class);

        then(politicalLeaderDevelopmentRepository).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Should throw exception when PoliticalLeaderDevelopmentID is not found")
    void throwExceptionWhenPoliticalLeaderDevelopmentIdIsNotFound() {
        List<String> activityList = Arrays.asList("road patching","hospital construction","new sewage pipeline","covid oxygen plant","dam construction");
        PoliticalLeaderDevelopmentDto updatedPoliticalLeaderDevelopmentDto = new PoliticalLeaderDevelopmentDto(123L, "corporation1 development1", activityList, new BigDecimal("212.45"), "West Bengal", 2022, 11);
        Mockito.when(politicalLeaderDevelopmentRepository.findById(2L)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> politicalLeaderDevelopmentService.updateDevelopmentWork(updatedPoliticalLeaderDevelopmentDto, 2L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}