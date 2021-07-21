package com.soderling.challenge.service.utils;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import com.soderling.challenge.exception.ExceptionNotExistDna;
import com.soderling.challenge.exception.ExceptionNotUniformDna;

public class ValidDna {
	private final static Logger logger = LoggerFactory.getLogger(ValidDna.class);
	
	public static String[] convertDna(String bodyRequest) throws ExceptionNotExistDna, ExceptionNotUniformDna {
		JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> parser = jsonParser.parseMap(bodyRequest);

        if (!parser.containsKey("dna")) {
        	ExceptionNotExistDna exceptionNotExistDna = new ExceptionNotExistDna("The chain does not contain a dna");
        	logger.error(exceptionNotExistDna.getMessage(), exceptionNotExistDna);
        	throw exceptionNotExistDna;          
        }
        
        List<String> dnaList = (List<String>) parser.get("dna");
        
        int lenghtRow = dnaList.get(0).length();
        
        for(String dnaRow : dnaList) {
        	if (dnaRow.length() != lenghtRow) {
        		ExceptionNotUniformDna exceptionNotUniformDna = new ExceptionNotUniformDna("The chain is not uniform");
            	logger.error(exceptionNotUniformDna.getMessage(), exceptionNotUniformDna);
            	throw exceptionNotUniformDna;
        	}
        }
        
        return dnaList.toArray(new String[]{});
	}

}
