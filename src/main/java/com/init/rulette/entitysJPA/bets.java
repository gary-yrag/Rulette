package com.init.rulette.entitysJPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BETS")
public class bets {
	@Id
	@Column(name="ID", nullable=false,length=3)
	private Long id;
	@Column(name="RULETTE_ID", nullable=false, length = 3)
	private Long rulette_id;
	@Column(name="USER_ID",nullable=false,length=3)
	private Long user_id;
	@Column(name="BET_VALUE", nullable=false,length=6)
	private Float bet_value;
	@Column(name="BETTING_CRITERION",nullable=false,length=10)
	private String betting_criterion;
	
	public bets() {
		
	}
	
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
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Float getBet_value() {
		return bet_value;
	}
	public void setBet_value(Float bet_value) {
		this.bet_value = bet_value;
	}
	public String getBetting_criterion() {
		return betting_criterion;
	}
	public void setBetting_criterion(String betting_criterion) {
		this.betting_criterion = betting_criterion;
	}
}
