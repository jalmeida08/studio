package br.com.jsa.api.dto;

import java.util.List;

import br.com.jsa.infra.model.Procedimento;

public class ProcedimentoDTO {

	private String id;
	private String nome;
	private Double valor;
	private float tempoDuracao;
	private boolean ativo;
	private List<String> funcionarios;
	
	public ProcedimentoDTO(Procedimento p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.valor = p.getValor();
		this.tempoDuracao = p.getTempoDuracao();
		this.ativo = p.isAtivo();
		this.funcionarios = p.getFuncionarios();
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public float getTempoDuracao() {
		return tempoDuracao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public List<String> getFuncionarios() {
		return funcionarios;
	}

}
