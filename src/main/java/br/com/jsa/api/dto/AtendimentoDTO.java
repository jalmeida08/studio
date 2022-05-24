package br.com.jsa.api.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.jsa.infra.model.Atendimento;
import br.com.jsa.infra.model.EstadoAtendimento;

public class AtendimentoDTO {

	private String id;
	private String idCliente;
	private String idFuncionario;
	private Double valor;
	private Float desconto;
	private LocalDateTime dataHoraAtendimento;
	private LocalDateTime dataHoraFimAtendimento;
	private EstadoAtendimento estadoAtendimento;
	private List<String> procedimentos = new ArrayList<>();

	public AtendimentoDTO(Atendimento a) {
		id = a.getId();
		idCliente = a.getIdCliente();
		valor = a.getValor();
		desconto = a.getDesconto();
		dataHoraAtendimento = a.getDataHoraAtendimento();
		dataHoraFimAtendimento = a.getDataHoraFimAtendimento();
		estadoAtendimento = a.getEstadoAtendimento();
		procedimentos = a.getProcedimentos();
		idFuncionario = a.getIdFuncionario();
	}

	public String getId() {
		return id;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public Double getValor() {
		return valor;
	}

	public Float getDesconto() {
		return desconto;
	}

	public LocalDateTime getDataHoraAtendimento() {
		return dataHoraAtendimento;
	}

	public LocalDateTime getDataHoraFimAtendimento() {
		return dataHoraFimAtendimento;
	}

	public EstadoAtendimento getEstadoAtendimento() {
		return estadoAtendimento;
	}

	public List<String> getProcedimentos() {
		return procedimentos;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}
}
