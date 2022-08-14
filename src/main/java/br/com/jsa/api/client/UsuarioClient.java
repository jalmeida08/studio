package br.com.jsa.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.jsa.api.dto.ConsultaEmailDTO;

@FeignClient(value = "usuario", path = "/usuario", contextId = "usuarioUsuario")
public interface UsuarioClient {
	
	@PostMapping("/publico/consulta-email-cadastrado")
	public boolean emailJaPossuiCadastro(ConsultaEmailDTO dto);
}
