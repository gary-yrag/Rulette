package com.init.rulette.dao.advanced;

import java.util.List;

import com.init.rulette.entitysJPA.bets;

/**
 * Interface
 * @author cracker
 *
 */
public interface IBetsAdvanced {
	List<bets> findByRuletteId(Long RuletteId);
}
