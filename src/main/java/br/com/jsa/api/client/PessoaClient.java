package br.com.jsa.api.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.jsa.api.dto.PessoaDTO;
import br.com.jsa.api.form.PessoaForm;

@FeignClient(value = "cadastro-basico", path = "/pessoa", contextId = "cadastroBasicoPessoa")
public interface PessoaClient {

	@PostMapping("/consulta-lista")
	public List<PessoaDTO> consultaDadosListaPessoa(List<String> listaPessoa);
	
	@GetMapping("/{idPessoa}")
	public Optional<PessoaDTO> consultaPessoaPorId(@PathVariable("idPessoa") String id);

	@PostMapping
	public PessoaDTO salvaDadosPessoa(PessoaForm p);
}
