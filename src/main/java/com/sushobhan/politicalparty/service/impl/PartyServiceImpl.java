package com.sushobhan.politicalparty.service.impl;

import com.sushobhan.politicalparty.entity.PartyEntity;
import com.sushobhan.politicalparty.payload.PartyDto;
import com.sushobhan.politicalparty.payload.PartyResponse;
import com.sushobhan.politicalparty.repository.PartyRepository;
import com.sushobhan.politicalparty.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyServiceImpl implements PartyService {

    private final PartyRepository partyRepository;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @Override
    public PartyDto createParty(PartyDto partyDto) {
        // convert DTO to entity
        PartyEntity partyEntity = mapToEntity(partyDto);
        PartyEntity saveParty = partyRepository.save(partyEntity);
        // convert entity to DTO
        return mapToDTO(saveParty);
    }

    @Override
    public PartyResponse getAllParties(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<PartyEntity> allParties = partyRepository.findAll(pageable);

        // get content for page object
        List<PartyEntity> partyEntityList = allParties.getContent();
        List<PartyDto> partyDtoList = partyEntityList.stream()
                .map(partyEntity -> mapToDTO(partyEntity))
                .collect(Collectors.toList());
        PartyResponse partyResponse = new PartyResponse();
        partyResponse.setPartyList(partyDtoList);
        partyResponse.setPageNo(allParties.getNumber());
        partyResponse.setPageSize(allParties.getSize());
        partyResponse.setTotalElements(allParties.getTotalElements());
        partyResponse.setTotalPages(allParties.getTotalPages());
        partyResponse.setLast(allParties.isLast());
        return partyResponse;
    }

    // convert Entity into DTO
    private PartyDto mapToDTO(PartyEntity partyEntity) {
        PartyDto partyDto = new PartyDto();
        partyDto.setId(partyEntity.getId());
        partyDto.setPartyName(partyEntity.getPartyName());
        partyDto.setFounderName(partyEntity.getFounderName());
        partyDto.setFoundationYear(partyEntity.getFoundationYear());
        return partyDto;
    }

    // convert DTO into Entity
    private PartyEntity mapToEntity(PartyDto partyDto) {
        PartyEntity partyEntity = new PartyEntity();
        partyEntity.setId(partyDto.getId());
        partyEntity.setPartyName(partyDto.getPartyName());
        partyEntity.setFounderName(partyDto.getFounderName());
        partyEntity.setFoundationYear(partyDto.getFoundationYear());
        return partyEntity;
    }
}
