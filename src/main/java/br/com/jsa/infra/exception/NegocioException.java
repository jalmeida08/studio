package br.com.jsa.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -873484771690333971L;

	public NegocioException(String message) {
		super(message);
	}
	
	public NegocioException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
