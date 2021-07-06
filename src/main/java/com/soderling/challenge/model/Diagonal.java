package com.soderling.challenge.model;

public class Diagonal {
	private int diagonal;
	private int x;
	private int y;	
	
	public Diagonal(int diagonal, int x, int y) {
		super();
		this.diagonal = diagonal;
		this.x = x;
		this.y = y;
	}
	
	public int getDiagonal() {
		return diagonal;
	}
	public void setDiagonal(int diagonal) {
		this.diagonal = diagonal;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
