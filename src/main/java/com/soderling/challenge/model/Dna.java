package com.soderling.challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dna")
public class Dna {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String[] dna;
	private boolean isMutant;	
	
	public Dna() {}
	
	public Dna(String[] dna, boolean isMutant) {
		super();
		this.dna = dna;
		this.isMutant = isMutant;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getDna() {
		return dna;
	}
	public void setDna(String[] dna) {
		this.dna = dna;
	}
	public boolean isMutant() {
		return isMutant;
	}
	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	
}
