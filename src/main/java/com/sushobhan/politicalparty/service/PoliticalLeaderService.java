package com.sushobhan.politicalparty.service;

import com.sushobhan.politicalparty.payload.PoliticalLeaderDto;

public interface PoliticalLeaderService {
    PoliticalLeaderDto registerLeaderToParty(PoliticalLeaderDto politicalLeaderDto);
    void deletePartyLeaderFromParty(long leaderId);
}
