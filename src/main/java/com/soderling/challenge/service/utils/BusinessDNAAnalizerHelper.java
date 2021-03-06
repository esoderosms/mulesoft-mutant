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
			
			List<Diagonal> DiagonalsList = getDiagonals(dna);
			
			int idDiagonal = (dna.length-1) * (-1);
			
			while (idDiagonal != dna.length-1) {
				List<Diagonal> diagonalPosList = getDiagonal(DiagonalsList, idDiagonal);
				Object[] posArray = diagonalPosList.toArray();
				if(posArray.length>=4) {
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
				
				idDiagonal++;
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
	
	private List<Diagonal> getDiagonals(String [][]dna) {

		List<Diagonal> DiagonalsList = new ArrayList<Diagonal>();
		
		int m = dna[0].length;
        int n = dna.length;
        

        for (int diagonal = 1 - m; diagonal <= n - 1; diagonal += 1) {
            for (int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal); vertical < n && horizontal < m; vertical += 1, horizontal += 1) {
                DiagonalsList.add(new Diagonal(diagonal, horizontal, vertical));
            }
        }
		
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
