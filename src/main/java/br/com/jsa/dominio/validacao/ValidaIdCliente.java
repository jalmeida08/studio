package br.com.jsa.dominio.validacao;


import java.util.Optional;

import br.com.jsa.api.dto.ClienteDTO;
import br.com.jsa.infra.exception.IndentificadorInvalidoException;

public class ValidaIdCliente implements ValidaIdentificadorInformado<String, ClienteDTO>{

	@Override
	public ClienteDTO identificadorIsValid(String idInformado, Optional<ClienteDTO> dadosBusca)
			throws IndentificadorInvalidoException {
		
		if(dadosBusca.isPresent() && idInformado.equals(dadosBusca.get().getId()))
			return dadosBusca.get();
		
		throw new IndentificadorInvalidoException(idInformado);
	}
	
	

}
