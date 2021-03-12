package com.init.rulette.dao.advanced;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.rulette.dao.BetsDao;
import com.init.rulette.entitysJPA.bets;

/**
 * Contract of interface
 * @author cracker
 *
 */
@Service
public class CBetsAdvanced implements IBetsAdvanced {

	@Autowired
	private BetsDao betsdaoRepository;
	
	@Override
	public List<bets> findByRuletteId(Long RuletteId) {
		// TODO Auto-generated method stub
		return betsdaoRepository.findByRuletteId(RuletteId);		
	}

}
