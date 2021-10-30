package com.sushobhan.politicalparty.utlils;

import com.sushobhan.politicalparty.entity.PartyEntity;
import com.sushobhan.politicalparty.entity.PoliticalLeaderEntity;
import com.sushobhan.politicalparty.payload.PartyDto;
import com.sushobhan.politicalparty.payload.PoliticalLeaderDto;

public class Mapper {
    public PoliticalLeaderDto mapToDTO(PoliticalLeaderEntity politicalLeaderEntity) {
        PoliticalLeaderDto politicalLeaderDto = new PoliticalLeaderDto();
        politicalLeaderDto.setPartyId(politicalLeaderEntity.getPartyId());
        politicalLeaderDto.setLeaderName(politicalLeaderEntity.getLeaderName());
        politicalLeaderDto.setLeaderState(politicalLeaderEntity.getLeaderState());
        return politicalLeaderDto;
    }

    public PartyDto mapToDTO(PartyEntity partyEntity) {
        PartyDto partyDto = new PartyDto();
        partyDto.setId(partyEntity.getId());
        partyDto.setPartyName(partyEntity.getPartyName());
        partyDto.setFounderName(partyEntity.getFounderName());
        partyDto.setFoundationYear(partyEntity.getFoundationYear());
        return partyDto;
    }
}
