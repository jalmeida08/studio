package br.com.jsa.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IndentificadorInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 6284702715771186962L;

	public IndentificadorInvalidoException(String idNaoLocalizado) {
		super("Identificador (" + idNaoLocalizado + ") informado não localizado.");
	}
}
