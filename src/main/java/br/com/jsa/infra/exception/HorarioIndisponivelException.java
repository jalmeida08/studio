package br.com.jsa.infra.exception;

public class HorarioIndisponivelException extends Throwable {

	private static final long serialVersionUID = -3960565204580653007L;

	public HorarioIndisponivelException(String message) {
		super(message);
	}

}
