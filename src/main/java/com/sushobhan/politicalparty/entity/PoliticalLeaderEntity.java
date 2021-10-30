package com.sushobhan.politicalparty.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PoliticalLeader")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoliticalLeaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long partyId;
    @Column(name = "leader_name", nullable = false)
    private String leaderName;
    @Column(name = "leader_state", nullable = false)
    private String leaderState;

//    @OneToMany(mappedBy = "politicalLeaderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<PoliticalLeaderDevelopmentEntity> leaderDevelopmentWorkEntities = new HashSet<>();
}
