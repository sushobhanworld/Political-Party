package com.sushobhan.politicalparty.repository;

import com.sushobhan.politicalparty.entity.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PartyRepository extends JpaRepository<PartyEntity, Long> {

}
