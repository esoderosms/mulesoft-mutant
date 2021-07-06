package com.soderling.challenge.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.soderling.challenge.model.Counter;
import com.soderling.challenge.model.Diagonal;
import com.soderling.challenge.utils.SearchResult;
import com.soderling.challenge.utils.Matrix;

public class BusinessDNAAnalizerHelper {	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean isMutant(String [][]dna) {
		boolean keep;		
		SearchResult searchResult = new SearchResult();
        
		logger.info("Horizontal Search Start");
		
        //horizontal search
		for (int x = 0; x < dna.length; x++) {
			searchResult.setCoincidence(0);
			for (int y = 0; y < dna[x].length-1; y++) {
				keep = searchHorizontal(x, y, dna, searchResult);
				if (searchResult.getBooleano()) {
					logger.info("Horizontal search is: " + searchResult.getBooleano());
					return searchResult.getBooleano();
				}
				if(keep == false) {
					break;
				} 				
			}
		}
		
		logger.info("Horizontal search is: " + searchResult.getBooleano());
		
		searchResult.getCounterList().clear();
		
		//vertical search	
		if (searchResult.getBooleano() == false) {
			
			logger.info("Vertical Search Start");
			
			String[][] invertDna = Matrix.invert(dna);
			
			for (int x = 0; x < invertDna.length; x++) {
				searchResult.setCoincidence(0);
				for (int y = 0; y < invertDna[x].length-1; y++) {
					keep = searchHorizontal(x, y, invertDna, searchResult);
					if (searchResult.getBooleano()) {
						logger.info("Vertical search is: " + searchResult.getBooleano());
						return searchResult.getBooleano();
					}
					if(keep == false) {
						break;
					} 				
				}
			}
			
			logger.info("Vertical search is: " + searchResult.getBooleano());
		}
		
		searchResult.getCounterList().clear();
		
		// oblique search
		if (searchResult.getBooleano() == false) {
			
			logger.info("Oblique Search Start");
			
			searchResult.setCoincidence(0);
			
			List<Diagonal> DiagonalsList = mapDiagonal();
			
			for (int i=1; i<= 10; i++) {
				List<Diagonal> diagonalPosList = getDiagonal(DiagonalsList, i);
				Object[] posArray = diagonalPosList.toArray();
				for(int j=0; j<= posArray.length; j++) {
					Diagonal diagonalPos = (Diagonal) posArray[j];
					Diagonal diagonalNextPos = (Diagonal) posArray[j+1];
					keep = searchOblique(diagonalPos, diagonalNextPos, dna, searchResult);
					if (searchResult.getBooleano()) {
						logger.info("Oblique search is: " + searchResult.getBooleano());
						return searchResult.getBooleano();
					}
					if (keep == false) {
						break;
					}
				}				
			}
			
			logger.info("Oblique search is: " + searchResult.getBooleano());
		}		
		
		return searchResult.getBooleano();
		
	}
	
	private boolean searchHorizontal(int x, int y, String[][] matrix, SearchResult searchResult) {
		int abscissa = x;
		
		if (y==5) return false;		

		String letter = matrix[x][y];
		String nextLetter = matrix[x][y+1];

		if (letter.equals(nextLetter)) {
			searchResult.getCounterList().add(new Counter(abscissa, letter));
			
			searchResult.setCoincidence(getCoincidence(searchResult.getCounterList(), abscissa, letter));
			
			if (searchResult.getCoincidence()==3) {
				searchResult.setBooleano(true);
				return false; 
			}	
			return true;
		} else {
			if (matrix.length - y >=4) return true;
		}

		return false;
	}
	
	private boolean searchOblique(Diagonal pos, Diagonal nextPost , String[][] matriz, SearchResult searchResult) {
		
		String letter = matriz[pos.getX()][pos.getY()];
		String nextLetter = matriz[nextPost.getX()][nextPost.getY()];

		if (letter.equals(nextLetter)) {
			searchResult.getCounterList().add(new Counter(pos.getDiagonal(), letter));
			
			searchResult.setCoincidence(getCoincidence(searchResult.getCounterList(), pos.getDiagonal(), letter));
			
			if (searchResult.getCoincidence()==3) {
				searchResult.setBooleano(true);
				return false; 
			}	
			return true;
		}

		return false;
	}
	
	private List<Diagonal> mapDiagonal() {

		List<Diagonal> DiagonalsList = new ArrayList<Diagonal>();
		
		DiagonalsList.add(new Diagonal(1, 3, 0));
		DiagonalsList.add(new Diagonal(1, 2, 1));
		DiagonalsList.add(new Diagonal(1, 1, 2));
		DiagonalsList.add(new Diagonal(1, 0, 3));
		DiagonalsList.add(new Diagonal(2, 4, 0));
		DiagonalsList.add(new Diagonal(2, 3, 1));
		DiagonalsList.add(new Diagonal(2, 2, 2));
		DiagonalsList.add(new Diagonal(2, 1, 3));
		DiagonalsList.add(new Diagonal(2, 0, 4));
		DiagonalsList.add(new Diagonal(3, 5, 0));
		DiagonalsList.add(new Diagonal(3, 4, 1));
		DiagonalsList.add(new Diagonal(3, 3, 2));
		DiagonalsList.add(new Diagonal(3, 2, 3));
		DiagonalsList.add(new Diagonal(3, 1, 4));
		DiagonalsList.add(new Diagonal(3, 0, 5));
		DiagonalsList.add(new Diagonal(4, 5, 1));
		DiagonalsList.add(new Diagonal(4, 4, 2));
		DiagonalsList.add(new Diagonal(4, 3, 3));
		DiagonalsList.add(new Diagonal(4, 2, 4));
		DiagonalsList.add(new Diagonal(4, 1, 5));
		DiagonalsList.add(new Diagonal(5, 5, 2));
		DiagonalsList.add(new Diagonal(5, 4, 3));
		DiagonalsList.add(new Diagonal(5, 3, 4));
		DiagonalsList.add(new Diagonal(5, 2, 5));
		DiagonalsList.add(new Diagonal(6, 3, 5));
		DiagonalsList.add(new Diagonal(6, 2, 4));
		DiagonalsList.add(new Diagonal(6, 1, 3));
		DiagonalsList.add(new Diagonal(6, 0, 2));
		DiagonalsList.add(new Diagonal(7, 4, 5));
		DiagonalsList.add(new Diagonal(7, 3, 4));
		DiagonalsList.add(new Diagonal(7, 2, 3));
		DiagonalsList.add(new Diagonal(7, 1, 2));
		DiagonalsList.add(new Diagonal(7, 0, 1));
		DiagonalsList.add(new Diagonal(8, 5, 5));
		DiagonalsList.add(new Diagonal(8, 4, 4));
		DiagonalsList.add(new Diagonal(8, 3, 3));
		DiagonalsList.add(new Diagonal(8, 2, 2));
		DiagonalsList.add(new Diagonal(8, 1, 1));
		DiagonalsList.add(new Diagonal(8, 0, 0));
		DiagonalsList.add(new Diagonal(9, 5, 4));
		DiagonalsList.add(new Diagonal(9, 4, 3));
		DiagonalsList.add(new Diagonal(9, 3, 2));
		DiagonalsList.add(new Diagonal(9, 2, 1));
		DiagonalsList.add(new Diagonal(9, 1, 0));
		DiagonalsList.add(new Diagonal(10, 5, 3));
		DiagonalsList.add(new Diagonal(10, 4, 2));
		DiagonalsList.add(new Diagonal(10, 3, 1));
		DiagonalsList.add(new Diagonal(10, 2, 0));
		
		return DiagonalsList;		

	}
	
	private int getCoincidence(List<Counter> counterList, int abscissa, String letter) {
		List<Counter> filteredCounterList;
		
		filteredCounterList = counterList.stream().filter(Counter -> abscissa == Counter.getAbscissa() && letter.equals(Counter.getLetter()))
		.collect(Collectors.toList());
		
		for (Counter counter: filteredCounterList) {
			logger.info("axis/diagonal " + counter.getAbscissa() + ";" + "letter: " + counter.getLetter() + ";" + "coincidence:" + filteredCounterList.size());
		}
		
		return filteredCounterList.size();
	}
	
	private List<Diagonal> getDiagonal(List<Diagonal> DiagonalsList, int numero) {
		List<Diagonal> filteredDiagonalList;
		
		filteredDiagonalList = DiagonalsList.stream().filter(Diagonal -> numero == Diagonal.getDiagonal())
		.collect(Collectors.toList());
		
		return filteredDiagonalList;
	}	

}
