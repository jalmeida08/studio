package br.com.jsa.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.jsa.infra.model.Atendimento;

public class ValidaInclusaoAtendimentoDTO {

	private List<AtendimentoDTO> listaAtendimentoConflitante;
	private AtendimentoDTO atendimentoSolicitado;

	public ValidaInclusaoAtendimentoDTO(List<AtendimentoDTO> listaAtendimento, Atendimento atendimento) {
		this.listaAtendimentoConflitante = listaAtendimento;
		this.atendimentoSolicitado = new AtendimentoDTO(atendimento);
	}

	public List<AtendimentoDTO> getListaAtendimentoConflitante() {
		return listaAtendimentoConflitante;
	}

	public AtendimentoDTO getAtendimentoSolicitado() {
		return atendimentoSolicitado;
	}

}
