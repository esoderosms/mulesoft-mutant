package com.soderling.challenge.utils;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class ValidDna {
	
	private final static Logger logger = LoggerFactory.getLogger(ValidDna.class);
	
	public static String[] convertDna(String bodyRequest) throws Exception {
		JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> parser = jsonParser.parseMap(bodyRequest);

        if (!parser.containsKey("dna")) {
            Exception myException = new Exception();
            logger.error("La cadena no contiene un dna", myException);
            throw myException;
        }
        
        List<String> dnaList = (List<String>) parser.get("dna");
        
        return dnaList.toArray(new String[]{});
	}

}
