package com.init.rulette.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.init.rulette.entitysJPA.bets;

/**
 * Repository
 * @author cracker
 *
 */

@Repository
public interface BetsDao extends JpaRepository<bets, Long>{
	
	@Query("SELECT *FROM BETS WHERE RULETTE_ID=%?1")
	List<bets>findByRuletteId(Long RuletteId);
}
