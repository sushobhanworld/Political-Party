package com.sushobhan.politicalparty.service.impl;

import com.sushobhan.politicalparty.entity.PoliticalLeaderDevelopmentEntity;
import com.sushobhan.politicalparty.exception.ResourceNotFoundException;
import com.sushobhan.politicalparty.payload.PoliticalLeaderDevelopmentDto;
import com.sushobhan.politicalparty.repository.PoliticalLeaderDevelopmentRepository;
import com.sushobhan.politicalparty.repository.PoliticalLeaderRepository;
import com.sushobhan.politicalparty.service.PoliticalLeaderDevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoliticalLeaderDevelopmentServiceImpl implements PoliticalLeaderDevelopmentService {

    private final PoliticalLeaderRepository politicalLeaderRepository;
    private final PoliticalLeaderDevelopmentRepository politicalLeaderDevelopmentRepository;

    @Autowired
    public PoliticalLeaderDevelopmentServiceImpl(PoliticalLeaderRepository politicalLeaderRepository, PoliticalLeaderDevelopmentRepository politicalLeaderDevelopmentRepository) {
        this.politicalLeaderRepository = politicalLeaderRepository;
        this.politicalLeaderDevelopmentRepository = politicalLeaderDevelopmentRepository;
    }

    @Override
    public PoliticalLeaderDevelopmentDto addDevelopment(PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto) {
        politicalLeaderRepository.findById(politicalLeaderDevelopmentDto.getPoliticalLeaderId()).orElseThrow(() -> new ResourceNotFoundException("Party","id", politicalLeaderDevelopmentDto.getPoliticalLeaderId()));
        PoliticalLeaderDevelopmentEntity politicalLeaderDevelopmentEntity = mapToEntity(politicalLeaderDevelopmentDto);
        PoliticalLeaderDevelopmentEntity saveDevelopment = politicalLeaderDevelopmentRepository.save(politicalLeaderDevelopmentEntity);
        return mapToDTO(saveDevelopment);
    }

    @Override
    public List<PoliticalLeaderDevelopmentDto> getAllDevelopmentWorks(long politicalLeaderId) {
        List<PoliticalLeaderDevelopmentEntity> allDevelopmentWorks = politicalLeaderDevelopmentRepository.findByPoliticalLeaderIdOrderByActivityYearDesc(politicalLeaderId);
        return allDevelopmentWorks.stream()
                .map(work ->mapToDTO(work))
                .collect(Collectors.toList());
    }

    @Override
    public PoliticalLeaderDevelopmentDto updateDevelopmentWork(PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto, long developmentWorkId) {
        PoliticalLeaderDevelopmentEntity politicalLeaderDevelopmentEntity = politicalLeaderDevelopmentRepository.findById(developmentWorkId).orElseThrow(() -> new ResourceNotFoundException("PoliticalLeaderDevelopment", "Id", developmentWorkId));
        politicalLeaderDevelopmentEntity.setPoliticalLeaderId(politicalLeaderDevelopmentDto.getPoliticalLeaderId());
        politicalLeaderDevelopmentEntity.setDevelopmentTitle(politicalLeaderDevelopmentDto.getDevelopmentTitle());
        politicalLeaderDevelopmentEntity.setActivities(politicalLeaderDevelopmentDto.getActivities());
        politicalLeaderDevelopmentEntity.setBudget(politicalLeaderDevelopmentDto.getBudget());
        politicalLeaderDevelopmentEntity.setBudget(politicalLeaderDevelopmentDto.getBudget());
        politicalLeaderDevelopmentEntity.setState(politicalLeaderDevelopmentDto.getState());
        politicalLeaderDevelopmentEntity.setActivityMonth(politicalLeaderDevelopmentDto.getActivityMonth());
        politicalLeaderDevelopmentEntity.setActivityYear(politicalLeaderDevelopmentDto.getActivityYear());

        //PoliticalLeaderDevelopmentEntity updatedDevelopmentWork = mapToEntity(politicalLeaderDevelopmentDto);
        PoliticalLeaderDevelopmentEntity saveUpdatedDevelopmentWork = politicalLeaderDevelopmentRepository.save(politicalLeaderDevelopmentEntity);
        return mapToDTO(saveUpdatedDevelopmentWork);
    }

    // convert Entity into DTO
    private PoliticalLeaderDevelopmentDto mapToDTO(PoliticalLeaderDevelopmentEntity politicalLeaderDevelopmentEntity){
        PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto = new PoliticalLeaderDevelopmentDto();
        politicalLeaderDevelopmentDto.setPoliticalLeaderId(politicalLeaderDevelopmentEntity.getPoliticalLeaderId());
        politicalLeaderDevelopmentDto.setDevelopmentTitle(politicalLeaderDevelopmentEntity.getDevelopmentTitle());
        politicalLeaderDevelopmentDto.setActivities(politicalLeaderDevelopmentEntity.getActivities());
        politicalLeaderDevelopmentDto.setBudget(politicalLeaderDevelopmentEntity.getBudget());
        politicalLeaderDevelopmentDto.setState(politicalLeaderDevelopmentEntity.getState());
        politicalLeaderDevelopmentDto.setActivityMonth(politicalLeaderDevelopmentEntity.getActivityMonth());
        politicalLeaderDevelopmentDto.setActivityYear(politicalLeaderDevelopmentEntity.getActivityYear());
        return politicalLeaderDevelopmentDto;
    }

    // convert DTO into Entity
    private PoliticalLeaderDevelopmentEntity mapToEntity(PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto){
        PoliticalLeaderDevelopmentEntity politicalLeaderDevelopmentEntity = new PoliticalLeaderDevelopmentEntity();
        politicalLeaderDevelopmentEntity.setPoliticalLeaderId(politicalLeaderDevelopmentDto.getPoliticalLeaderId());
        politicalLeaderDevelopmentEntity.setDevelopmentTitle(politicalLeaderDevelopmentDto.getDevelopmentTitle());
        politicalLeaderDevelopmentEntity.setActivities(politicalLeaderDevelopmentDto.getActivities());
        politicalLeaderDevelopmentEntity.setBudget(politicalLeaderDevelopmentDto.getBudget());
        politicalLeaderDevelopmentEntity.setBudget(politicalLeaderDevelopmentDto.getBudget());
        politicalLeaderDevelopmentEntity.setState(politicalLeaderDevelopmentDto.getState());
        politicalLeaderDevelopmentEntity.setActivityMonth(politicalLeaderDevelopmentDto.getActivityMonth());
        politicalLeaderDevelopmentEntity.setActivityYear(politicalLeaderDevelopmentDto.getActivityYear());
        return politicalLeaderDevelopmentEntity;
    }
}
