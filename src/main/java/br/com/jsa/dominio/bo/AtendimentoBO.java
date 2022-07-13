package br.com.jsa.dominio.bo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jsa.dominio.validacao.ValidaDataAtendimento;
import br.com.jsa.infra.exception.NegocioException;
import br.com.jsa.infra.model.Atendimento;
import br.com.jsa.infra.model.Procedimento;

public class AtendimentoBO {

	public BigDecimal calculaValorAtendimento(List<Procedimento> listaProcedimentoAtendimento, BigDecimal desconto) {
		
		var valor = listaProcedimentoAtendimento
			.stream()
			.map(a -> a.getValor())
			.reduce(BigDecimal::add)
			.orElse(BigDecimal.ZERO);
		if(desconto != null && desconto.compareTo(BigDecimal.ZERO) > 0)
			return returnaValorAtendimentoComDesconto(valor, desconto);
		else
			return valor.setScale(2);
	}

	private BigDecimal returnaValorAtendimentoComDesconto(BigDecimal valor, BigDecimal desconto) {
		var cemPorCento = new BigDecimal(100);
		var pencentual = valor.multiply(desconto.divide(cemPorCento));
		return valor.subtract(pencentual);
	}

	public LocalDateTime calcularDataFimProcedimento(LocalDateTime dataAgendamento,
			List<Procedimento> listaProcedimentoAtendimento) {
		
		var somaTempoAtendimento = listaProcedimentoAtendimento
				.parallelStream()
				.map(p -> p.getTempoDuracao())
				.reduce(Long::sum)
				.orElseThrow(() -> new NegocioException("Não foi possível calcular o tempo de atendimento para os procedimentos listados"));
		
		return dataAgendamento.plusMinutes(somaTempoAtendimento.longValue());
		
	}


	public List<Atendimento> listarAtendimentosNoMesmoHorario(List<Atendimento> listaAtendimentoPeriodo,
			LocalDateTime dataHoraAtendimento, LocalDateTime dataHoraFimAtendimento) {
		
		return listaAtendimentoPeriodo
			.parallelStream()
			.filter(a -> (verificaInicioDataAtendimento(a, dataHoraAtendimento, dataHoraFimAtendimento)) 
					|| (verificaDataMeioFimAtendimento(a, dataHoraAtendimento, dataHoraFimAtendimento)))
			.collect(Collectors.toList()); 
	}

	private boolean verificaInicioDataAtendimento(Atendimento atendimento, LocalDateTime dataHoraAtendimento,
			LocalDateTime dataHoraFimAtendimento) {
		
		return ValidaDataAtendimento
			.testaInicioDatasAtendimento(atendimento.getDataHoraAtendimento(), dataHoraAtendimento, dataHoraFimAtendimento);
	}

	private boolean verificaDataMeioFimAtendimento(Atendimento atendimento, LocalDateTime dataHoraAtendimento,
			LocalDateTime dataHoraFimAtendimento) {
		return ValidaDataAtendimento
				.testaMeioFimDatasAtendimento(atendimento.getDataHoraAtendimento(), atendimento.getDataHoraFimAtendimento(),
						dataHoraAtendimento, dataHoraFimAtendimento);
	}
}
