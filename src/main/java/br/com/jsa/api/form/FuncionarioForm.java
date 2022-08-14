package br.com.jsa.api.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.jsa.dominio.model.Funcionario;

public class FuncionarioForm {

	private PessoaForm pessoa;
	@Email
	@NotBlank
	private String email;
	
	public FuncionarioForm() {	}
	
	public Funcionario toFuncionario() {
		return new Funcionario();
	}
	
	public PessoaForm getPessoaForm() {
		return pessoa;
	}

	public void setPessoa(PessoaForm pessoa) {
		this.pessoa = pessoa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
