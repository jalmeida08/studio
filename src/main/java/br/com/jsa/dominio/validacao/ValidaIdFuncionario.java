package br.com.jsa.dominio.validacao;

import java.util.Optional;

import br.com.jsa.api.dto.FuncionarioDTO;
import br.com.jsa.infra.exception.IndentificadorInvalidoException;

public class ValidaIdFuncionario implements ValidaIdentificadorInformado<String, FuncionarioDTO>{

	@Override
	public FuncionarioDTO identificadorIsValid(String idInformado, Optional<FuncionarioDTO> dadosBusca)
			throws IndentificadorInvalidoException {
		
		if(dadosBusca.isPresent() && idInformado.equals(dadosBusca.get().getId()))
			return dadosBusca.get();
		
		throw new IndentificadorInvalidoException(idInformado);
	}
	
	

}
