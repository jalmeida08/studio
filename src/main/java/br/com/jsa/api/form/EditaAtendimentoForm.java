package br.com.jsa.api.form;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EditaAtendimentoForm {
	
	private String id;
	private Float desconto;
	private LocalDateTime dataHoraAtendimento;
	private List<String> procedimentos = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<String> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<String> procedimentos) {
		this.procedimentos = procedimentos;
	}
	
	
	

}
