package br.com.jsa.api.client;

import java.util.Optional;

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
	
	@GetMapping("/{idCliente}")
	public Optional<ClienteDTO> buscaClientePorId(@PathVariable("idCliente") String id);

}
