package br.com.jsa.api.dto;

public class VerificaIdFuncionarioDTO {

	private String idFuncionario;
	private boolean valido;
	
	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

}
