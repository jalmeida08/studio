package br.com.jsa.infra.exception;

public class DadoInvalidoException extends Exception {

	private static final long serialVersionUID = 5454395926926908951L;
	
	public DadoInvalidoException(String message) {
		super(message);
	}
}
