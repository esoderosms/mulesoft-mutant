package com.soderling.challenge.utils;

import java.util.ArrayList;
import java.util.List;

import com.soderling.challenge.model.Counter;

public class SearchResult {
	private boolean booleano;
	private int coincidence;
	private List<Counter> counterList;	

	public SearchResult() {
		super();
		this.booleano = false;
		this.coincidence = 0;
		this.counterList = new ArrayList<Counter>();
	}

	public boolean getBooleano() {
		return booleano;
	}

	public void setBooleano(boolean booleano) {
		this.booleano = booleano;
	}

	public int getCoincidence() {
		return coincidence;
	}

	public void setCoincidence(int coincidence) {
		this.coincidence = coincidence;
	}

	public List<Counter> getCounterList() {
		return counterList;
	}

	public void setCounterList(List<Counter> counterList) {
		this.counterList = counterList;
	}	
	
}
