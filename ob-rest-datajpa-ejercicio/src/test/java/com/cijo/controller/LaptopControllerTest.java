package com.cijo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.cijo.model.Laptop;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LaptopControllerTest {

	private TestRestTemplate restTemplate;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port );
		restTemplate = new TestRestTemplate(restTemplateBuilder);
	}

	@Test
	void findAll() {
		ResponseEntity<Laptop[]> response = restTemplate.getForEntity("/api/v1.0/laptops", Laptop[].class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		List<Object> laptop = Arrays.asList(response.getBody());
	}

	@Test
	void findById() {
		ResponseEntity<Laptop> response = restTemplate.getForEntity("/api/v1.0/laptops/16", Laptop.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void create() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String json = """
				{
					"marca": "Asusito",
					"modelo": "Asus Prochofer",
					"almacenamiento": 1000,
					"ram": 32
				}
								""";
		
		HttpEntity<String> request = new HttpEntity<>(json, headers);
		ResponseEntity<Laptop> response =
				restTemplate.exchange("/api/v1.0/laptop", HttpMethod.POST, request, Laptop.class);
		
		Laptop result = response.getBody();
		
		assertEquals(1L, result.getId());
		assertEquals("Asusito", result.getMarca());
	}
	
	@Test
	void contextLoad() {
		System.getenv().forEach((key,value)-> System.out.println(key + " "+ value));
	}
}
