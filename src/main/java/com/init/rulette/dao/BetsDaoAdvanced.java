package com.init.rulette.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.init.rulette.entitysJPA.bets;

@Repository
public interface BetsDaoAdvanced extends CrudRepository<bets, Long>{
	@Query(value="SELECT *FROM BETS WHERE RULETTE_ID=?1", nativeQuery=true)
	List<bets>findByRuletteId(Long RuletteId);
}
