package br.com.jsa.api.dto;

import java.time.LocalDate;

import br.com.jsa.dominio.model.Funcionario;

public class FuncionarioDTO extends PessoaDTO{

	private String id;

	public FuncionarioDTO(Funcionario f, String nome, LocalDate dataNascimento) {
		this.id = f.getId();
		this.pessoaId = f.getPessoaId();
		this.dataNascimento = dataNascimento;
		this.nome = nome;
	}
	
	public FuncionarioDTO(Funcionario f) {
		this.id = f.getId();
		this.pessoaId = f.getPessoaId();
	}
	
	public String getId() {
		return id;
	}
	
	
}
