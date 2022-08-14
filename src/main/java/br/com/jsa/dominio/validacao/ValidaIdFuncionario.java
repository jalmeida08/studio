package br.com.jsa.dominio.validacao;

import java.util.Optional;

import br.com.jsa.dominio.model.Funcionario;
import br.com.jsa.infra.exception.IndentificadorInvalidoException;

public class ValidaIdFuncionario implements ValidaIdentificadorInformado<String, Funcionario>{

	@Override
	public Funcionario identificadorIsValid(String idInformado, Optional<Funcionario> dadosBusca)
			throws IndentificadorInvalidoException {
		
		if(dadosBusca.isPresent() && idInformado.equals(dadosBusca.get().getId()))
			return dadosBusca.get();
		
		throw new IndentificadorInvalidoException(idInformado);
	}
	
	

}
