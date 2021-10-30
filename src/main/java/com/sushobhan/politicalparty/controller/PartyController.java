package com.sushobhan.politicalparty.controller;

import com.sushobhan.politicalparty.payload.PartyDto;
import com.sushobhan.politicalparty.payload.PartyResponse;
import com.sushobhan.politicalparty.service.PartyService;
import com.sushobhan.politicalparty.utlils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/politics/api/v1")
public class PartyController {
    private final PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @PostMapping("/addParty")
    public ResponseEntity<PartyDto> createPoliticalParty(@RequestBody PartyDto partyDto) {
        return new ResponseEntity<>(partyService.createParty(partyDto), HttpStatus.CREATED);
    }

    @GetMapping("/parties/get/all")
    public PartyResponse getAllPoliticalParties(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return partyService.getAllParties(pageNo, pageSize, sortBy, sortDir);
    }
}
