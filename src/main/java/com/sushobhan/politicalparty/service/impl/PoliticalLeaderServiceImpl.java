package com.sushobhan.politicalparty.service.impl;

import com.sushobhan.politicalparty.entity.PartyEntity;
import com.sushobhan.politicalparty.entity.PoliticalLeaderEntity;
import com.sushobhan.politicalparty.exception.ResourceNotFoundException;
import com.sushobhan.politicalparty.payload.PoliticalLeaderDto;
import com.sushobhan.politicalparty.repository.PartyRepository;
import com.sushobhan.politicalparty.repository.PoliticalLeaderRepository;
import com.sushobhan.politicalparty.service.PoliticalLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PoliticalLeaderServiceImpl implements PoliticalLeaderService {

    private final PartyRepository partyRepository;
    private final PoliticalLeaderRepository politicalLeaderRepository;

    @Autowired
    public PoliticalLeaderServiceImpl(PartyRepository partyRepository, PoliticalLeaderRepository politicalLeaderRepository) {
        this.partyRepository = partyRepository;
        this.politicalLeaderRepository = politicalLeaderRepository;
    }

    @Override
    public PoliticalLeaderDto registerLeaderToParty(PoliticalLeaderDto politicalLeaderDto) {
        partyRepository.findById(politicalLeaderDto.getPartyId()).orElseThrow(() -> new ResourceNotFoundException("Party","id", politicalLeaderDto.getPartyId()));
        PoliticalLeaderEntity politicalLeaderEntity = mapToEntity(politicalLeaderDto);
        PoliticalLeaderEntity saveLeader = politicalLeaderRepository.save(politicalLeaderEntity);
        return mapToDTO(saveLeader);
    }

    @Override
    public void deletePartyLeaderFromParty(long leaderId) {
        PoliticalLeaderEntity politicalLeaderEntity = politicalLeaderRepository.findById(leaderId).orElseThrow(() -> new ResourceNotFoundException("PoliticalLeaderEntity", "id", leaderId));
        politicalLeaderRepository.delete(politicalLeaderEntity);
    }

    // convert Entity into DTO
    private PoliticalLeaderDto mapToDTO(PoliticalLeaderEntity politicalLeaderEntity) {
        PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto();
        politicalLeaderDto.setPartyId(politicalLeaderEntity.getPartyId());
        politicalLeaderDto.setLeaderName(politicalLeaderEntity.getLeaderName());
        politicalLeaderDto.setLeaderState(politicalLeaderEntity.getLeaderState());
        return politicalLeaderDto;
    }

    // convert DTO into Entity
    private PoliticalLeaderEntity mapToEntity(PoliticalLeaderDto politicalLeaderDto) {
        PoliticalLeaderEntity politicalLeaderEntity = new PoliticalLeaderEntity();
        politicalLeaderEntity.setPartyId(politicalLeaderDto.getPartyId());
        politicalLeaderEntity.setLeaderName(politicalLeaderDto.getLeaderName());
        politicalLeaderEntity.setLeaderState(politicalLeaderDto.getLeaderState());
        return politicalLeaderEntity;
    }
}
