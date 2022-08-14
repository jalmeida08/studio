package br.com.jsa.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PessoaDTO {
	@JsonProperty("id")
	protected String pessoaId;
	protected String nome;
	protected LocalDate dataNascimento;

	public String getPessoaId() {
		return pessoaId;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
