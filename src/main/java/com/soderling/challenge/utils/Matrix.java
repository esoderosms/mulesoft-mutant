package com.soderling.challenge.utils;

public class Matrix {
	
	public static String[][] invert(String[][] dna){
		String[][] invert = new String[dna.length][dna.length];
		for (int x=0; x < dna.length; x++) {
			  for (int y=0; y < dna[x].length; y++) {
				  invert[y][x] = dna[x][y];
			  }
		}
		
		return invert;		
	}
	
	public static String[][] build(String[] stringArray){	
		String[][] matrix = new String[stringArray.length][stringArray.length];
		
		for (int x=0; x < stringArray.length; x++) {
			  for (int y=0; y < stringArray.length; y++) {
				  matrix[x][y] = Character.toString(stringArray[x].charAt(y));
			  }
		}
		
		return matrix;
	}

}
