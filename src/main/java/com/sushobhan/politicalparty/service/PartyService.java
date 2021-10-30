package com.sushobhan.politicalparty.service;

import com.sushobhan.politicalparty.payload.PartyDto;
import com.sushobhan.politicalparty.payload.PartyResponse;

public interface PartyService {
    PartyDto createParty(PartyDto partyDto);
    PartyResponse getAllParties(int pageNo, int pageSize, String sortBy, String sortDir);
}
