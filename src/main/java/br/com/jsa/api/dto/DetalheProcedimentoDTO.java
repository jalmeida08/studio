package br.com.jsa.api.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.jsa.dominio.model.Procedimento;

public class DetalheProcedimentoDTO {

	private String id;
	private String nome;
	private BigDecimal valor;
	private Long tempoDuracao;
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
