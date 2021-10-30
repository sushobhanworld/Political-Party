package com.sushobhan.politicalparty.service;

import com.sushobhan.politicalparty.payload.PoliticalLeaderDevelopmentDto;

import java.util.List;

public interface PoliticalLeaderDevelopmentService {
    PoliticalLeaderDevelopmentDto addDevelopment(PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto);
    List<PoliticalLeaderDevelopmentDto> getAllDevelopmentWorks(long politicalLeaderId);
    PoliticalLeaderDevelopmentDto updateDevelopmentWork(PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto, long developmentWorkId);
}
