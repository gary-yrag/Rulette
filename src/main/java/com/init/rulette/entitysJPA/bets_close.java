package com.init.rulette.entitysJPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BETS_CLOSE")
public class bets_close {

	@Id
	@Column(name="ID", nullable=false, length=3)
	private Long id;
	@Column(name="RULETTE_ID", nullable=false, length=3)
	private Long rulette_id;
	@Column(name="WINNER_NUMBER",nullable=false, length=3)
	private Long winner_number;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRulette_id() {
		return rulette_id;
	}
	public void setRulette_id(Long rulette_id) {
		this.rulette_id = rulette_id;
	}
	public Long getWinner_number() {
		return winner_number;
	}
	public void setWinner_number(Long winner_number) {
		this.winner_number = winner_number;
	}

}
