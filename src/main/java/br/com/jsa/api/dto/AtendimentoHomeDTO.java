package br.com.jsa.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.jsa.infra.model.Atendimento;
import br.com.jsa.infra.model.EstadoAtendimento;

public class AtendimentoHomeDTO {

	private String id;
	private ClienteDTO cliente;
	private FuncionarioDTO funcionario;
	private BigDecimal valor;
	private BigDecimal desconto;
	private LocalDateTime dataHoraAtendimento;
	private LocalDateTime dataHoraFimAtendimento;
	private EstadoAtendimento estadoAtendimento;
	private List<String> procedimentos = new ArrayList<>();

	public AtendimentoHomeDTO(Atendimento a, ClienteDTO c, FuncionarioDTO f) {
		id = a.getId();
		cliente = c;
		valor = a.getValor();
		desconto = a.getDesconto();
		dataHoraAtendimento = a.getDataHoraAtendimento();
		dataHoraFimAtendimento = a.getDataHoraFimAtendimento();
		estadoAtendimento = a.getEstadoAtendimento();
		procedimentos = a.getProcedimentos();
		funcionario = f;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getDesconto() {
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

	public ClienteDTO getCliente() {
		return cliente;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

}