package br.com.jsa.api.dto;

import java.time.LocalDate;

public class BuscaDadosAgendaFuncionarioDTO {

	private String id;
	private LocalDate dia;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

}
