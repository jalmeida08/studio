package br.com.jsa.api.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.jsa.infra.model.Procedimento;

public class ProcedimentoForm {

	private String nome;
	private BigDecimal valor;
	private Long tempoDuracao;
	private Boolean ativo;
	private List<String> idFuncionario = new ArrayList<String>();

	public Procedimento toProcedimento() {
		Procedimento p = new Procedimento();
		p.setAtivo(ativo);
		p.setFuncionarios(idFuncionario);
		p.setNome(nome);
		p.setTempoDuracao(tempoDuracao);
		p.setValor(valor);
		return p;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getTempoDuracao() {
		return tempoDuracao;
	}

	public void setTempoDuracao(Long tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<String> getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(List<String> idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	
}
