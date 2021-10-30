package com.sushobhan.politicalparty.repository;

import com.sushobhan.politicalparty.entity.PoliticalLeaderDevelopmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PoliticalLeaderDevelopmentRepository extends JpaRepository<PoliticalLeaderDevelopmentEntity, Long> {
    //@Query(value = "select * from PoliticalLeaderDevelopmemt where politicalLeaderId = ?1 order by activityYear desc", nativeQuery = true)
    List<PoliticalLeaderDevelopmentEntity> findByPoliticalLeaderIdOrderByActivityYearDesc(long politicalLeaderId);
}
