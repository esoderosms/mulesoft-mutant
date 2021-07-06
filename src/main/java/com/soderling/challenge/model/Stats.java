package com.soderling.challenge.model;

public class Stats {
	private int countMutantDna;
	private int countHumanDna;
	private float ratio;	
	
	public Stats(int countMutantDna, int countHumanDna, float ratio) {
		super();
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.ratio = ratio;
	}
	
	public int getCountMutantDna() {
		return countMutantDna;
	}
	public void setCountMutantDna(int countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	public int getCountHumanDna() {
		return countHumanDna;
	}
	public void setCountHumanDna(int countHumanDna) {
		this.countHumanDna = countHumanDna;
	}
	public float getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	

}
