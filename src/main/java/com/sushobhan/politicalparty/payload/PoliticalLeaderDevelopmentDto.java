package com.sushobhan.politicalparty.payload;

import com.sushobhan.politicalparty.payload.validator.MonthRange;
import com.sushobhan.politicalparty.payload.validator.YearRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class PoliticalLeaderDevelopmentDto {

    public PoliticalLeaderDevelopmentDto(Long politicalLeaderId, String developmentTitle, List<String> activities, BigDecimal budget, String state, Integer activityYear, Integer activityMonth) {
        this.politicalLeaderId = politicalLeaderId;
        this.developmentTitle = developmentTitle;
        this.activities = activities;
        this.budget = budget;
        this.state = state;
        this.activityYear = activityYear;
        this.activityMonth = activityMonth;
    }

    @NotNull
    private Long politicalLeaderId;

    @NotEmpty
    @Size(min = 5, max = 100, message = "Development title should be at least 5 characters and max 100 characters")
    private String developmentTitle;

    @NotEmpty
    @Size(min = 5, max = 100, message = "Activity should be at least 5 characters and max 100 characters")
    private List<String> activities;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal budget;

    @NotEmpty
    @Size(min = 3, max = 50, message = "State should be at least 3 characters and max 50 characters")
    private String state;

    @YearRange
    private Integer activityYear;

    @MonthRange
    private Integer activityMonth;
}
