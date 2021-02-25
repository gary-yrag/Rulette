package com.init.rulette.entitysJPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="RULETTE")
public class rulette {
	
	@Column(name="ID", nullable=false, length=3)
	private Long id;
	@Column(name="STATUS",nullable = false, length = 20)
	private String status;
	
	public rulette() {		
	}
	
	public rulette(Long id, String status) {		
		this.id = id;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}
