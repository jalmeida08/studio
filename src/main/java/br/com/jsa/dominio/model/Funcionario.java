package br.com.jsa.dominio.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("funcionario")
public class Funcionario {
	@Id
	private String id;
	private String pessoaId;
	@Version
	private BigDecimal versao;

	public String getId() {
		return id;
	}

	public String getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(String pessoaId) {
		this.pessoaId = pessoaId;
	}

	public BigDecimal getVersao() {
		return versao;
	}

}
