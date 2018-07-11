package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.ValueWrapper;

@Component
public class Test implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void run(String... arg0) throws Exception {
		HttpHeaders requestHeaders = generateCommonHeader();
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		String url = "http://edsartresource-lt.cloudapps.cisco.com:80/services/v2/groups/186/resources?onlyFirstLevelChildren=true&page=1&size=20";
		logger.info("Sending request to {}", url);
		ResponseEntity<ValueWrapper> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				ValueWrapper.class);
		logger.info("Response body is : {}", responseEntity.getBody().getWtf());
	}

	private HttpHeaders generateCommonHeader() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "Basic ZWRzYXJ0YXBpLmdlbjpidWJydURBWmF5dXF1M0U4ZUY3Ng==");
		return requestHeaders;
	}

}
