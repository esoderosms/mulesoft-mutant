package com.soderling.challenge;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soderling.challenge.model.Stats;
import com.soderling.challenge.service.DnaServiceImpl;
import com.soderling.challenge.utils.ValidDna;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DnaServiceTest {
	@MockBean
	private DnaServiceImpl service;	
	
	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testStatusOk() throws Exception {		
		String mutantDna = "{\"dna\":[\"ATGCGA\",\"CGAGGC\",\"TTATGT\",\"AGAACG\",\"CCGCTA\",\"TCACTG\"]}";

		Mockito.when(service.isMutant(ValidDna.convertDna(mutantDna))).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dna/mutant").accept(MediaType.APPLICATION_JSON)
				.content(mutantDna).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		Assert.assertTrue(result.getResponse().getStatus() == 200);
	}
	
	@Test
	public void testStatusForbidden() throws Exception {		
		String humanDna = "{\"dna\":[\"ATGCGA\",\"CGGTGC\",\"TTATAT\",\"AGAAGG\",\"CCGCTA\",\"TCACTG\"]}";

		Mockito.when(service.isMutant(ValidDna.convertDna(humanDna))).thenReturn(false);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dna/mutant").accept(MediaType.APPLICATION_JSON)
				.content(humanDna).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		Assert.assertTrue(result.getResponse().getStatus() == 403);
	}
	
	@Test
	public void testStatsRatioOne() throws Exception {
		Stats stats = new Stats(1, 1, 1f);
		String jsonStats = mapper.writeValueAsString(stats);

		Mockito.when(service.ratio()).thenReturn(stats);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dna/stats");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		Assert.assertEquals(jsonStats, result.getResponse().getContentAsString());
	}
	 


}
