package com.sushobhan.politicalparty.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PoliticalLeaderDevelopment")
public class PoliticalLeaderDevelopmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long politicalLeaderId;
    @Column(nullable = false)
    private String developmentTitle;
    @ElementCollection
    @CollectionTable(name = "political_leader_activities", joinColumns = @JoinColumn(name = "political_leader_id"))
    @Column(name = "activities")
    private List<String> activities = new ArrayList<>();
    @Column(nullable = false)
    private BigDecimal budget;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private Integer activityYear;
    @Column(nullable = false)
    private Integer activityMonth;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "political_leader_id", nullable = false)
//    private PoliticalLeaderEntity politicalLeaderEntity;
}
