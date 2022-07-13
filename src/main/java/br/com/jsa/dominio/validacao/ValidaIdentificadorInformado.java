package br.com.jsa.dominio.validacao;

import java.util.Optional;

import br.com.jsa.infra.exception.IndentificadorInvalidoException;

public interface ValidaIdentificadorInformado<T, C> {
	
	public C identificadorIsValid(T idInformado, Optional<C> dadosBusca)
			throws IndentificadorInvalidoException;

}
