package br.com.jsa.infra.feing;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import br.com.jsa.api.client.ClienteClient;
import br.com.jsa.api.dto.ClienteDTO;
import br.com.jsa.infra.exception.NegocioException;
import feign.FeignException;

@Component
public class clientFallbackFactory implements FallbackFactory<ClienteClient> {

	@Override
	public ClienteClient create(Throwable cause) {
		String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";

		return new ClienteClient() {

			@Override
			public void validaClientePorId(String id) {
				throw new NegocioException(httpStatus);
			}

			@Override
			public ClienteDTO buscaClientePorId(String id) {
				throw new NegocioException(httpStatus);
			}
		};
	}
	

}
