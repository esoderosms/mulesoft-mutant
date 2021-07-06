package com.soderling.challenge.utils;

public class Matrix {
	
	public static String[][] invert(String[][] dna){
		String[][] invert = new String[6][6];
		for (int x=0; x < dna.length; x++) {
			  for (int y=0; y < dna[x].length; y++) {
				  invert[y][x] = dna[x][y];
			  }
		}
		
		return invert;		
	}
	
	public static String[][] build(String[] ejemplo){	
		String[][] matrix = new String[6][6];
		
		for (int x=0; x < ejemplo.length; x++) {
			  for (int y=0; y < ejemplo.length; y++) {
				  //matrix[x][y] = String.valueOf(ejemplo[x].charAt(y));
				  matrix[x][y] = Character.toString(ejemplo[x].charAt(y));
			  }
		}
		
		return matrix;
	}

}
