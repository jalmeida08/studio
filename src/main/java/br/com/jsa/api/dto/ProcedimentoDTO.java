package br.com.jsa.api.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.jsa.infra.model.Procedimento;

public class ProcedimentoDTO {

	private String id;
	private String nome;
	private BigDecimal valor;
	private Long tempoDuracao;
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

	public BigDecimal getValor() {
		return valor;
	}

	public Long getTempoDuracao() {
		return tempoDuracao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public List<String> getFuncionarios() {
		return funcionarios;
	}

}
