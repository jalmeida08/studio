package br.com.jsa.infra.feing;

import org.springframework.context.annotation.Bean;

import feign.codec.ErrorDecoder;

public class MyFeingClientConfiguration {
	
	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
	
}
