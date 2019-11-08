package com.valentin.sarbacane.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.CounterDto;

@ActiveProfiles("test")
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipientControllerTest {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	int randomServerPort;
	

	// test saving into database, and returns 200
	@Test
	public void save_recipient_then_return_200() throws URISyntaxException, RestClientException, IOException { 

		final String baseUrl = "http://localhost:" + randomServerPort + "/documents/upload";
		URI uri = new URI(baseUrl);

		MockMultipartFile mockCsvFile = new MockMultipartFile("csvFile", "recipient.txt", "text/csv", "email;phone\n test2@gmail.com;0632147590\n test3@gmail.com;0686829876".getBytes());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);


		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
		ContentDisposition contentDisposition = ContentDisposition
				.builder("form-data")
				.name("csvFile")
				.filename("recipient.csv")
				.build();
		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
		HttpEntity<byte[]> fileEntity = new HttpEntity<>(mockCsvFile.getBytes(), fileMap);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", fileEntity);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<CounterDto> result = restTemplate.postForEntity(uri, requestEntity, CounterDto.class);

		assertEquals(200, result.getStatusCodeValue());
	}

	//	// test empty file
		@Test
		public void save_empty_file_then_return_400() throws URISyntaxException, RestClientException, IOException { 
	
			final String baseUrl = "http://localhost:" + randomServerPort + "/documents/upload";
			URI uri = new URI(baseUrl);
	
	
			MockMultipartFile mockCsvFile = new MockMultipartFile("csvFile", "recipient.txt", "text/csv", "".getBytes());
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);


			MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
			ContentDisposition contentDisposition = ContentDisposition
					.builder("form-data")
					.name("csvFile")
					.filename("recipient.csv")
					.build();
			fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
			HttpEntity<byte[]> fileEntity = new HttpEntity<>(mockCsvFile.getBytes(), fileMap);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", fileEntity);

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			ResponseEntity<CounterDto> result = restTemplate.postForEntity(uri, requestEntity, CounterDto.class);

			assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		}
	// test wrong file format 
		@Test
		public void save_wrong_file_then_return_415() throws URISyntaxException, RestClientException, IOException { 
	
			final String baseUrl = "http://localhost:" + randomServerPort + "/documents/upload";
			URI uri = new URI(baseUrl);
	

			MockMultipartFile mockCsvFile = new MockMultipartFile("csvFile", "recipient.txt", "text/csv", "zrrzger".getBytes());
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);


			MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
			ContentDisposition contentDisposition = ContentDisposition
					.builder("form-data")
					.name("csvFile")
					.filename("recipient.txt")
					.build();
			fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
			HttpEntity<byte[]> fileEntity = new HttpEntity<>(mockCsvFile.getBytes(), fileMap);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", fileEntity);

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			ResponseEntity<CounterDto> result = restTemplate.postForEntity(uri, requestEntity, CounterDto.class);
			assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, result.getStatusCode());
		}
}
