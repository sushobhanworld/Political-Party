package com.sushobhan.politicalparty.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "party", uniqueConstraints = {@UniqueConstraint(columnNames = {"party_name"})})
public class PartyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "party_name", nullable = false)
    private String partyName;
    @Column(name = "founder_name", nullable = false)
    private String founderName;
    @Column(name = "foundation_year", nullable = false)
    private Double foundationYear;
}
