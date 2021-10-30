package com.sushobhan.politicalparty.controller;

import com.sushobhan.politicalparty.payload.PoliticalLeaderDevelopmentDto;
import com.sushobhan.politicalparty.payload.PoliticalLeaderDto;
import com.sushobhan.politicalparty.service.PoliticalLeaderDevelopmentService;
import com.sushobhan.politicalparty.service.PoliticalLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/politics/api/v1")
public class PoliticalLeaderController {
    private final PoliticalLeaderService politicalLeaderService;
    private final PoliticalLeaderDevelopmentService politicalLeaderDevelopmentService;

    @Autowired
    public PoliticalLeaderController(PoliticalLeaderService politicalLeaderService, PoliticalLeaderDevelopmentService politicalLeaderDevelopmentService) {
        this.politicalLeaderService = politicalLeaderService;
        this.politicalLeaderDevelopmentService = politicalLeaderDevelopmentService;
    }

    @PostMapping("/leader/register-leader")
    public ResponseEntity<PoliticalLeaderDto> registerLeader(@Valid @RequestBody PoliticalLeaderDto politicalLeaderDto) {
        return new ResponseEntity<>(politicalLeaderService.registerLeaderToParty(politicalLeaderDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/party/delete/{leaderId}")
    public ResponseEntity<String> deletePartyLeader(@PathVariable(name = "leaderId") long leaderId){
        politicalLeaderService.deletePartyLeaderFromParty(leaderId);
        return new ResponseEntity<>("Political leader deleted successfully.", HttpStatus.OK);
    }

    @PostMapping("/development/add-development")
    public ResponseEntity<PoliticalLeaderDevelopmentDto> addDevelopmentForLeader(@Valid @RequestBody PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto){
        return new ResponseEntity<>(politicalLeaderDevelopmentService.addDevelopment(politicalLeaderDevelopmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/development/get-development-by-{leaderId}")
    public List<PoliticalLeaderDevelopmentDto> getAllDevelopmentWorksByALeader(@PathVariable(name = "leaderId") long leaderId){
        return politicalLeaderDevelopmentService.getAllDevelopmentWorks(leaderId);
    }

    @PutMapping("/development/update-development/{id}")
    public ResponseEntity<PoliticalLeaderDevelopmentDto> updateDevelopmentWork(@RequestBody PoliticalLeaderDevelopmentDto politicalLeaderDevelopmentDto, @PathVariable(name = "id") long id){
        return new ResponseEntity<>(politicalLeaderDevelopmentService.updateDevelopmentWork(politicalLeaderDevelopmentDto, id), HttpStatus.OK);
    }
}
