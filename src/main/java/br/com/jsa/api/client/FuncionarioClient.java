package br.com.jsa.api.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.jsa.api.dto.FuncionarioDTO;
import br.com.jsa.api.dto.VerificaIdFuncionarioDTO;

@FeignClient(value = "cadastro-basico", path = "/funcionario", contextId = "cadastroBasicoFuncionario")
public interface FuncionarioClient {

	@PostMapping("/valida-funcionarios")
	public List<VerificaIdFuncionarioDTO> idFuncionarioIsValid(List<String> listaIdFuncionario);
	
	@PostMapping("/consulta-lista")
	public List<FuncionarioDTO> consultaDadosListaFuncionario(List<String> funcionarios);
	
	@GetMapping("/{idFuncionario}")
	public Optional<FuncionarioDTO> consultaFuncionarioPorId(@PathVariable("idFuncionario") String id);
}
