package com.sushobhan.politicalparty.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalLeaderDto {
    @NotNull
    private Long partyId;
    @NotEmpty
    @Size(min = 5, max = 100, message = "Leader name should be at least 5 characters and max 100 characters")
    private String leaderName;
    @NotEmpty
    @Size(min = 5, max = 100, message = "Leader state should be at least 5 characters and max 100 characters")
    private String leaderState;
}
