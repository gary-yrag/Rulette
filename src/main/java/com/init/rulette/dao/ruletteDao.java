package com.init.rulette.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.rulette.entitysJPA.rulette;

public interface RuletteDao extends JpaRepository<rulette, Long> {
	
}
