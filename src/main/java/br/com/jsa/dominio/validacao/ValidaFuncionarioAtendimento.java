package br.com.jsa.dominio.validacao;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jsa.infra.exception.NegocioException;
import br.com.jsa.infra.model.Procedimento;

public class ValidaFuncionarioAtendimento {

	public static void validaSeFuncionarioPodeAtender(List<Procedimento> listaProcedimento, String idFuncionario) {
		List<String> listaFuncionariosInaptos = listaProcedimento
			.stream()
			.filter(i -> !i.getFuncionarios().contains(idFuncionario))
			.map(i -> i.getNome())
			.collect(Collectors.toList());
			
		if(listaFuncionariosInaptos.size() > 0)
			throw new NegocioException(
					"O funcionario não pode realizar o(s) procedimento(s) " +
					listaFuncionariosInaptos.toString()
			);
	}
	
}
