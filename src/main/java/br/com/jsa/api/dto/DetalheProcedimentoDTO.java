package br.com.jsa.api.dto;

import java.util.List;

import br.com.jsa.infra.model.Procedimento;

public class DetalheProcedimentoDTO {

	private String id;
	private String nome;
	private Double valor;
	private float tempoDuracao;
	private boolean ativo;
	private List<FuncionarioDTO> funcionarios;

	public DetalheProcedimentoDTO(Procedimento p, List<FuncionarioDTO> lstDadosFunci) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.valor = p.getValor();
		this.tempoDuracao = p.getTempoDuracao();
		this.ativo = p.isAtivo();
		this.funcionarios = lstDadosFunci;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public float getTempoDuracao() {
		return tempoDuracao;
	}

	public void setTempoDuracao(float tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<FuncionarioDTO> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioDTO> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
