package br.com.jsa.dominio.bo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jsa.infra.exception.NegocioException;
import br.com.jsa.infra.model.Atendimento;
import br.com.jsa.infra.model.Procedimento;

public class AtendimentoBO {

	public Double calculaValorAtendimento(List<Procedimento> listaProcedimentoAtendimento, Float desconto) {
		
		var valor = listaProcedimentoAtendimento
			.stream()
			.map(a -> a.getValor())
			.reduce(Double::sum)
			.orElse(0D);
		if(desconto != null && desconto > 0)
			return new BigDecimal(valor - ((valor*desconto)/100)).setScale(2).doubleValue();
		else
			return new BigDecimal(valor).setScale(2).doubleValue();
	}

	public LocalDateTime calcularDataFimProcedimento(LocalDateTime dataAgendamento,
			List<Procedimento> listaProcedimentoAtendimento) {
		
		var somaTempoAtendimento = listaProcedimentoAtendimento
				.parallelStream()
				.map(p -> p.getTempoDuracao())
				.reduce(Long::sum)
				.orElseThrow(() -> new NegocioException("Não foi possível calcular o tempo de atendimento para os procedimentos listados"));
		
		return dataAgendamento.plusMinutes(somaTempoAtendimento); 
		
	}

//	public boolean verificaDisponibilidadeAtenda(List<Atendimento> listaAtendimentoPeriodo,
//			LocalDateTime dataHoraAtendimento, LocalDateTime dataHoraFimAtendimento) {
//		return listaAtendimentoPeriodo
//			.parallelStream()
//			.filter(
//					a -> (
//							(a.getDataHoraAtendimento().isAfter(dataHoraAtendimento) || a.getDataHoraAtendimento().isEqual(dataHoraAtendimento))
//							&& (a.getDataHoraAtendimento().isBefore(dataHoraFimAtendimento))
//						  ) || (
//							((a.getDataHoraAtendimento().isBefore(dataHoraAtendimento) || a.getDataHoraAtendimento().isEqual(dataHoraAtendimento))
//									&& (a.getDataHoraFimAtendimento().isAfter(dataHoraFimAtendimento) || (
//											a.getDataHoraFimAtendimento().isAfter(dataHoraAtendimento) && a.getDataHoraFimAtendimento().isBefore(dataHoraFimAtendimento)))
//									)
//							)							
//					).findAny().isPresent(); 
//	}
	
	public List<Atendimento> listarAtendimentosNoMesmoHorario(List<Atendimento> listaAtendimentoPeriodo,
			LocalDateTime dataHoraAtendimento, LocalDateTime dataHoraFimAtendimento) {
		return listaAtendimentoPeriodo
			.parallelStream()
			.filter(
					a -> (
							(a.getDataHoraAtendimento().isAfter(dataHoraAtendimento) || a.getDataHoraAtendimento().isEqual(dataHoraAtendimento))
							&& (a.getDataHoraAtendimento().isBefore(dataHoraFimAtendimento))
						  ) || (
							((a.getDataHoraAtendimento().isBefore(dataHoraAtendimento) || a.getDataHoraAtendimento().isEqual(dataHoraAtendimento))
									&& (a.getDataHoraFimAtendimento().isAfter(dataHoraFimAtendimento) || (
											a.getDataHoraFimAtendimento().isAfter(dataHoraAtendimento) && a.getDataHoraFimAtendimento().isBefore(dataHoraFimAtendimento)))
									)
							)							
					).collect(Collectors.toList()); 
	}
}
