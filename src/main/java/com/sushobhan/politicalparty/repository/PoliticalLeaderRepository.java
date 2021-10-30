package com.sushobhan.politicalparty.repository;

import com.sushobhan.politicalparty.entity.PoliticalLeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoliticalLeaderRepository extends JpaRepository<PoliticalLeaderEntity, Long> {

}
