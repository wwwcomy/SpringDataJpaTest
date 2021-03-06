package com.example.demo;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * There's an annotation called @ConfigurationProperties to populate some config
 * class properties, to replace @Value if there're lots of properties
 * 
 * @author xingnliu
 *
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() throws NoSuchAlgorithmException {
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
				// bypass the no subject matching error
				new javax.net.ssl.HostnameVerifier() {
					public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
						return true;
					}
				});
		CloseableHttpClient httpClient = null;
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault(),
				new NoopHostnameVerifier());
		httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", sslsf).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		connManager.setMaxTotal(200);
		connManager.setDefaultMaxPerRoute(20);
		httpClient = HttpClients.custom().setConnectionManager(connManager).build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		factory.setConnectTimeout(60000);
		factory.setReadTimeout(300000);
		factory.setConnectionRequestTimeout(2000);
		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}

	@Bean
	public Docket createRestApi() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		tokenPar.name("token").description("Token").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		pars.add(tokenPar.build());
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")).paths(PathSelectors.any())
				.build().globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("wwwcomy", "", "wwwcomy@gmail.com");
		return new ApiInfoBuilder().title("Task Manager").description("Task management service").contact(contact)
				.version("1.0").build();
	}
}
