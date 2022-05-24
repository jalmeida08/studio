package br.com.jsa.infra.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Atendimento")
public class Atendimento implements Serializable {

	private static final long serialVersionUID = -2483376118144321689L;

	@Id
	private String id;
	private String idCliente;
	private String idFuncionario;
	private Double valor;
	private Float desconto;
	private LocalDateTime dataHoraAtendimento;
	private LocalDateTime dataHoraFimAtendimento;
	private EstadoAtendimento estadoAtendimento;
	private List<String> procedimentos = new ArrayList<>();
	@Version
	private Long versao;

	public String getIdCliente() {
		return idCliente;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Float getDesconto() {
		return desconto;
	}

	public void setDesconto(Float desconto) {
		this.desconto = desconto;
	}

	public LocalDateTime getDataHoraAtendimento() {
		return dataHoraAtendimento;
	}

	public void setDataHoraAtendimento(LocalDateTime dataHoraAtendimento) {
		this.dataHoraAtendimento = dataHoraAtendimento;
	}

	public LocalDateTime getDataHoraFimAtendimento() {
		return dataHoraFimAtendimento;
	}

	public void setDataHoraFimAtendimento(LocalDateTime dataHoraFimAtendimento) {
		this.dataHoraFimAtendimento = dataHoraFimAtendimento;
	}

	public EstadoAtendimento getEstadoAtendimento() {
		return estadoAtendimento;
	}

	public void setEstadoAtendimento(EstadoAtendimento estadoAtendimento) {
		this.estadoAtendimento = estadoAtendimento;
	}

	public String getId() {
		return id;
	}

	public Long getVersao() {
		return versao;
	}

	public List<String> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<String> procedimentos) {
		this.procedimentos = procedimentos;
	}
}
