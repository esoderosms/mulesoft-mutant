package com.soderling.challenge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soderling.challenge.model.Dna;
import com.soderling.challenge.model.Stats;
import com.soderling.challenge.repository.DnaRepository;
import com.soderling.challenge.utils.Matrix;
import com.soderling.challenge.service.utils.BusinessDNAAnalizerHelper;



@Service
public class DnaServiceImpl implements DnaService {	
	
	@Autowired
	private DnaRepository dnaRepository;

	@Override
	public boolean isMutant(String[] dnaArray) {
		boolean isMutant = false;
		
		String[][] dna = Matrix.build(dnaArray);
		
		isMutant = new BusinessDNAAnalizerHelper().isMutant(dna);		
		
		Dna newDna = new Dna(dnaArray, isMutant);
		
		this.Store(newDna);
		
		return isMutant;        		
	}
	
	public Stats ratio(){		
		List<Dna> dnalist = dnaRepository.findAll();
		
		return this.getRatio(dnalist);		
	}
	
	public Dna Store(Dna dna) {
		return dnaRepository.save(dna);
	}
	
	private Stats getRatio(List<Dna> dnalist) {
		int countMutantDna = 0;
		int countHumanDna = 0;
		float ratio;
		
		for(Dna adn: dnalist) {
			if(adn.isMutant()) 
				countMutantDna++;
			else countHumanDna++;
		}
		
		if (countHumanDna == 0) {
			ratio = 1f;
		} else ratio = (float) countMutantDna / countHumanDna;		
		
		Stats stats = new Stats(countMutantDna, countHumanDna, ratio);
		
		return stats;		
	}
	
	
	
	

}
