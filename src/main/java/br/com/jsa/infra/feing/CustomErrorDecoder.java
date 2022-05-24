package br.com.jsa.infra.feing;

import org.springframework.http.HttpStatus;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		String requestUrl = response.request().url();
		
		Response.Body responseBody = response.body();
		HttpStatus responseStatus = HttpStatus.valueOf(response.status());

		if (responseStatus.is5xxServerError()) {
			return new RuntimeException(requestUrl +" - "+ responseBody);
		} else if (responseStatus.is4xxClientError()) {
			System.out.println(requestUrl +" - "+ responseBody);
			return new RuntimeException(requestUrl +" - "+ responseBody);
		} else {
			return new Exception("Generic exception");
		}
	}
}