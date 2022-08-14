package br.com.jsa.api.form;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PessoaForm {

	@NotBlank(message = "Nome obrigatório") @Min(value = 3, message = "Mínimo de 3 caracteres")
	private String nome;
	@NotNull (message = "Data de nascimento obrigatório")
	private LocalDate dataNascimento;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
