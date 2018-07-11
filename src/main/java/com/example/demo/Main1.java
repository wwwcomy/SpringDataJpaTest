package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableAutoConfiguration
@EnableJpaAuditing
@ComponentScan(basePackages = "com.example.demo", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DemoApplication.class) })
public class Main1 {
	static Logger logger = LoggerFactory.getLogger(Main1.class);

	@Bean
	public RestTemplate restOperations() {
		RestTemplate rest = new RestTemplate();
		// this is crucial!
		rest.getMessageConverters().add(0, mappingJacksonHttpMessageConverter());
		return rest;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(myObjectMapper());
		return converter;
	}

	@Bean
	public ObjectMapper myObjectMapper() {
		ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return om;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Main1.class);
		app.setWebEnvironment(false);
		app.run(args);
	}

}
