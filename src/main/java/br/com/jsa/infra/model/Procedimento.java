package br.com.jsa.infra.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "procedimento")
public class Procedimento implements Serializable {

	private static final long serialVersionUID = 8925827411036874972L;
	@Id
	private String id;
	private String nome;
	private Double valor;
	private Long tempoDuracao;
	private LocalDateTime dataFimAtendimento;
	private boolean ativo;
	private List<String> funcionarios;
	
	@Version
	private Long versao;

	
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

	public Long getTempoDuracao() {
		return tempoDuracao;
	}

	public void setTempoDuracao(Long tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}

	public List<String> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<String> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getId() {
		return id;
	}

	public Long getVersao() {
		return versao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDateTime getDataFimAtendimento() {
		return dataFimAtendimento;
	}

	public void setDataFimAtendimento(LocalDateTime dataFimAtendimento) {
		this.dataFimAtendimento = dataFimAtendimento;
	}

}
