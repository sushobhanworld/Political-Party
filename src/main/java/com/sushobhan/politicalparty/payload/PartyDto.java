package com.sushobhan.politicalparty.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDto {

    private Long id;
    @NotEmpty(message = "PartyName field is required")
    @Size(min = 5, max = 100, message = "Party name should be at least 5 characters and max 100 characters")
    private String partyName;
    @NotEmpty(message = "FounderName field is required")
    @Size(min = 5, max = 100, message = "Founder name should be at least 5 characters and max 100 characters")
    private String founderName;
    @NotEmpty(message = "FoundationYear field is required")
    private Double foundationYear;

    public PartyDto(String partyName, String founderName, Double foundationYear) {
        this.partyName = partyName;
        this.founderName = founderName;
        this.foundationYear = foundationYear;
    }
}
