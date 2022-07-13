package br.com.jsa.api.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.jsa.infra.model.Atendimento;
import br.com.jsa.infra.model.EstadoAtendimento;

public class AtendimentoForm {

	@NotBlank(message = "É obrigatório informar a qual cliente pertence esse atendimento")
	private String idCliente;
	private String idFuncionario;
	private BigDecimal desconto;
//	@NotBlank(message = "Data agendamento é obrigatório")
	private LocalDateTime dataHoraAtendimento;
	private EstadoAtendimento estadoAtendimento;
	@Size(min = 1, message = "É obrigatório escolher pelo menos um procedimento")
	private List<String> procedimentos = new ArrayList<>();

	public Atendimento toAtendimento() {
		var a = new Atendimento();
		a.setDesconto(desconto);
		a.setDataHoraAtendimento(dataHoraAtendimento);
		a.setEstadoAtendimento(estadoAtendimento);
		a.setProcedimentos(procedimentos);
		a.setIdCliente(idCliente);
		a.setIdFuncionario(idFuncionario);
		return a;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public void setDataHoraAtendimento(LocalDateTime dataAtendimento) {
		this.dataHoraAtendimento = dataAtendimento;
	}

	public void setEstadoAtendimento(EstadoAtendimento estadoAtendimento) {
		this.estadoAtendimento = estadoAtendimento;
	}

	public void setProcedimentos(List<String> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public LocalDateTime getDataHoraAtendimento() {
		return dataHoraAtendimento;
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
