package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestTemplateTest {

	@Autowired
	RestTemplate restTemplate;

	@Test
	public void callOther() throws UnsupportedEncodingException {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "Basic ZWRzYXJ0YXBpLmdlbjpidWJydURBWmF5dXF1M0U4ZUY3Ng==");
		org.springframework.http.HttpEntity<?> requestEntity = new org.springframework.http.HttpEntity<Object>(
				requestHeaders);
		String url = "http://edsartapi-dev.cloudapps.cisco.com:80/services/v2/roles/Access Management:ART Admin:AdminManager/users";
		
		System.out.println(url);
		ResponseEntity<List> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, List.class);
		List result = entity.getBody();
		System.out.println("---------------");
		System.out.println(result);
		System.out.println("===============");
	}

	@Test
	public void callOther1() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "Basic ZWRzYXJ0YXBpLmdlbjpidWJydURBWmF5dXF1M0U4ZUY3Ng==");
		org.springframework.http.HttpEntity<?> requestEntity = new org.springframework.http.HttpEntity<Object>(
				requestHeaders);
		String url = "http://edsartapi-dev.cloudapps.cisco.com:80/services/v2/roles/Access Management:ART Admin:AdminManager/users";
		ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		// ResponseEntity<List> entity = restTemplate.getForEntity(url, List.class);
		String result = entity.getBody();
		System.out.println("---------------");
		System.out.println(result);
		System.out.println("===============");
	}
}
