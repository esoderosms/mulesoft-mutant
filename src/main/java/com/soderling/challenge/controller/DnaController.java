package com.soderling.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soderling.challenge.model.Stats;
import com.soderling.challenge.service.DnaService;
import com.soderling.challenge.utils.ValidDna;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/dna")
public class DnaController {	
	
	@Autowired
	private DnaService adnService;	
	
	@PostMapping("/mutant")
	public ResponseEntity<?> ismutantAdn(@RequestBody String body) throws Exception {
		Map<String, Boolean> response = new HashMap<>();
		
		String[] dnaArray = ValidDna.convertDna(body);		
		
		boolean result = adnService.isMutant(dnaArray);
		
		response.put("ismutant", result);
		
		if (result) {			
			return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.FORBIDDEN);
		}
	}
	
	
	@GetMapping("/stats")
	@Produces("application/json")
	public Stats stats(){
		return adnService.ratio();
	}
	

}
