package br.com.jsa.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.jsa.api.dto.ClienteDTO;
import br.com.jsa.infra.feing.clientFallbackFactory;

@FeignClient(
		value = "cadastro-basico",
		path = "/cliente",
		contextId = "cadastroBasicoCliente",
		fallbackFactory = clientFallbackFactory.class
		)
public interface ClienteClient {
	
	@GetMapping(value = "/{idCliente}")
	public void validaClientePorId(@PathVariable("idCliente") String id);

	@GetMapping("/{idCliente}")
	public ClienteDTO buscaClientePorId(@PathVariable("idCliente") String id);

}
