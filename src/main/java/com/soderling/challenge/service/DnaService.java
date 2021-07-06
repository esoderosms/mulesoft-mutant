package com.soderling.challenge.service;

import com.soderling.challenge.model.Dna;
import com.soderling.challenge.model.Stats;

public interface DnaService {
	boolean isMutant(String[] arrayDna);
	Stats ratio();
	Dna Store(Dna adn);
}
