package com.init.rulette.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.rulette.entitysJPA.bets;

/**
 * Repository
 * @author cracker
 *
 */

@Repository
public interface BetsDao extends JpaRepository<bets, Long>{
	
}
