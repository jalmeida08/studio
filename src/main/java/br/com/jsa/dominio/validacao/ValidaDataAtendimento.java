package br.com.jsa.dominio.validacao;

import java.time.LocalDateTime;

public class ValidaDataAtendimento {
	
	public static boolean testaInicioDatasAtendimento(LocalDateTime dataBaseAtendimento, LocalDateTime dataHoraPretendida,
			LocalDateTime dataHoraPretendidoFimCalculado) {
		if((dataBaseAtendimento.isAfter(dataHoraPretendida) || dataBaseAtendimento.isEqual(dataHoraPretendida))
				&& (dataBaseAtendimento.isBefore(dataHoraPretendidoFimCalculado))){
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean testaMeioFimDatasAtendimento(LocalDateTime dataBaseAtendimento, LocalDateTime dataFimBaseAtendimento,
			LocalDateTime dataHoraPretendida, LocalDateTime dataHoraPretendidoFimCalculado) {
		
		if((dataBaseAtendimento.isBefore(dataHoraPretendida) || dataBaseAtendimento.isEqual(dataHoraPretendida))
				&& (dataFimBaseAtendimento.isAfter(dataHoraPretendidoFimCalculado) || (
						dataFimBaseAtendimento.isAfter(dataHoraPretendida) && dataFimBaseAtendimento.isBefore(dataHoraPretendidoFimCalculado)))
				) {
			return true;
		} else {
			return false;
		}
	}
}
