package br.com.jsa.api.form;

public class UsuarioForm {

	private String email;
	private String pessoaId; 

	public UsuarioForm(String email, String pessoaId) {
		super();
		this.email = email;
		this.pessoaId = pessoaId;
	}

	public String getEmail() {
		return email;
	}

	public String getPessoaId() {
		return pessoaId;
	}

}
