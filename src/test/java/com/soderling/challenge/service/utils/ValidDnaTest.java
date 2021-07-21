package com.soderling.challenge.service.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soderling.challenge.exception.ExceptionNotExistDna;
import com.soderling.challenge.exception.ExceptionNotUniformDna;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValidDnaTest {
	
	@Test
    public void testExceptionNotUniformDna()
    {
		String mutantDna = "{\"dna\":[\"ATGCGAA\",\"CGAGGC\",\"TTATGT\",\"AGAACG\",\"CCGCTA\",\"TCACTG\"]}";

        new ValidDna();
        
		ExceptionNotUniformDna exceptionNotUniformDna = assertThrows(ExceptionNotUniformDna.class, () -> ValidDna.convertDna(mutantDna));		

        String expectedMessage = "The chain is not uniform";

        String actualMessage = exceptionNotUniformDna.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }
	
	@Test
    public void testExceptionNotExistDna()
    {
		String mutantDna = "{\"param\":[\"ATGCGA\",\"CGAGGC\",\"TTATGT\",\"AGAACG\",\"CCGCTA\",\"TCACTG\"]}";

        new ValidDna();
        
		ExceptionNotExistDna exceptionNotExistDna = assertThrows(ExceptionNotExistDna.class, () -> ValidDna.convertDna(mutantDna));		

        String expectedMessage = "The chain does not contain a dna";

        String actualMessage = exceptionNotExistDna.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

}
