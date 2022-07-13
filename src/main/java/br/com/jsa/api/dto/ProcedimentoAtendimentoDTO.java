package br.com.jsa.api.dto;

import br.com.jsa.infra.model.Procedimento;

import java.math.BigDecimal;

public class ProcedimentoAtendimentoDTO {

	private String id;
	private String nome;
	private BigDecimal valor;
	private Long tempoDuracao;
	private boolean ativo;
	
	public ProcedimentoAtendimentoDTO(Procedimento p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.valor = p.getValor();
		this.ativo = p.isAtivo();
		this.tempoDuracao = p.getTempoDuracao();
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

}
