package br.com.jsa.api.dto;

public class VerificaIdDTO {

	private String id;
	private boolean valido;

	public VerificaIdDTO(String id, boolean valido) {
		this.id = id;
		this.valido = valido;
	}

	public String getId() {
		return id;
	}

	public boolean isValido() {
		return valido;
	}
	
}
