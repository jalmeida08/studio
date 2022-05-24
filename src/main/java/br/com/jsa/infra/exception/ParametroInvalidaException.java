package br.com.jsa.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParametroInvalidaException extends RuntimeException {

	private static final long serialVersionUID = -216008925991767096L;


	public ParametroInvalidaException(String message) {
		super(message);
	}
}
