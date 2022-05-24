package br.com.jsa;

import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.client.RestTemplate;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@EnableFeignClients
public class ProcedimentoApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClients.createDefault());
		return new RestTemplate(requestFactory);
	}

	@Bean
	public RequestInterceptor getInterceptorDeAutenticacao() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate template) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if(authentication == null)
					return;
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication .getDetails();
				template.header("Authorization", "bearer "+details.getTokenValue()); 
				
			}
		};
	}
	

	public static void main(String[] args) {
		SpringApplication.run(ProcedimentoApplication.class, args);
	}

}
