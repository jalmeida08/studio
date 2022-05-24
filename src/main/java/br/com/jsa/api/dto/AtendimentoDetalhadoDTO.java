package br.com.jsa.api.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.jsa.infra.model.Atendimento;
import br.com.jsa.infra.model.EstadoAtendimento;

public class AtendimentoDetalhadoDTO {

	private String id;
	private ClienteDTO cliente;
	private FuncionarioDTO funcionario;
	private Double valor;
	private Float desconto;
	private LocalDateTime dataHoraAtendimento;
	private LocalDateTime dataHoraFimAtendimento;
	private EstadoAtendimento estadoAtendimento;
	private List<ProcedimentoAtendimentoDTO> procedimentos = new ArrayList<>();

	public AtendimentoDetalhadoDTO(Atendimento a, ClienteDTO c, FuncionarioDTO f, List<ProcedimentoAtendimentoDTO> listaProcedimento) {
		id = a.getId();
		cliente = c;
		valor = a.getValor();
		desconto = a.getDesconto();
		dataHoraAtendimento = a.getDataHoraAtendimento();
		dataHoraFimAtendimento = a.getDataHoraFimAtendimento();
		estadoAtendimento = a.getEstadoAtendimento();
		procedimentos = listaProcedimento;
		funcionario = f;
	}

	public String getId() {
		return id;
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

	public List<ProcedimentoAtendimentoDTO> getProcedimentos() {
		return procedimentos;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

}