package br.com.jsa.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsa.api.client.PessoaClient;
import br.com.jsa.api.client.UsuarioClient;
import br.com.jsa.api.dto.ConsultaEmailDTO;
import br.com.jsa.api.dto.FuncionarioDTO;
import br.com.jsa.api.dto.PessoaDTO;
import br.com.jsa.api.form.FuncionarioForm;
import br.com.jsa.api.form.UsuarioForm;
import br.com.jsa.api.kafka.produce.pessoa.FuncionarioProducer;
import br.com.jsa.dominio.model.Funcionario;
import br.com.jsa.dominio.repository.FuncionarioRepository;
import br.com.jsa.infra.exception.EmailCadastradoRuntimeException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private PessoaClient pessoaClient;
	
	@Autowired
	private UsuarioClient usuarioClient;
	
	@Autowired
	private FuncionarioProducer funcionarioProducer;
	
	public boolean idIsValid(String id) {
		return funcionarioRepository.existsById(id);
	}

	public void salvaFuncionario(FuncionarioForm form) {
		var f = form.toFuncionario();
		var p = form.getPessoaForm();
		
		var isCadastrado = this.usuarioClient.emailJaPossuiCadastro(new ConsultaEmailDTO(form.getEmail()));
		
		if(isCadastrado)
			throw new EmailCadastradoRuntimeException("E-mail já possui cadastro, tente recuperar a senha");
		
		var pDto = this.pessoaClient.salvaDadosPessoa(p);
		
		f.setPessoaId(pDto.getPessoaId());
		var u = new UsuarioForm(form.getEmail(), pDto.getPessoaId());

		this.funcionarioRepository.save(f);
		
		funcionarioProducer.enviaNovaPessoa(u);
	}

	public Optional<Funcionario> consultaFuncionarioPorId(String id) {
		var f = this.funcionarioRepository.findById(id);
		return f;
	}

	public Map<String, Boolean> idFuncionarioIsValid(List<String> listaIdFuncionario) {
		Map<String, Boolean> mapFunci = new HashMap<>();
		listaIdFuncionario
			.forEach(i-> mapFunci.put(i, false));
		
		listaIdFuncionario
			.stream()
			.map(i -> this.funcionarioRepository.findById(i))
			.forEach(i -> i.ifPresent(j-> mapFunci.put(j.getId(), true)));
		
		return mapFunci;
			
	}

	public List<FuncionarioDTO> consultaDadosListaFuncionario(List<String> funcionarios) {
		Map<String, FuncionarioDTO> mapFuncionariosDto = funcionarios
				.stream()
				.map(i -> this.funcionarioRepository.findById(i).get())
				.map(FuncionarioDTO::new)
				.collect(Collectors.toMap(i -> i.getPessoaId(), j -> j));
		
		List<String> listaIdPessoas = mapFuncionariosDto
				.keySet()
				.stream()
				.collect(Collectors.toList());
		
		var listaDadosPessoa = buscaDadosListaPessoa(listaIdPessoas);
		
		listaDadosPessoa
			.forEach((i) -> {
				FuncionarioDTO funcionarioDTO = mapFuncionariosDto.get(i.getPessoaId());
				funcionarioDTO.setNome(i.getNome());
				funcionarioDTO.setDataNascimento(i.getDataNascimento());
			});
		return mapFuncionariosDto
				.values()
				.stream()
				.collect(Collectors.toList());
	}

	private List<PessoaDTO> buscaDadosListaPessoa(List<String> listaIdPessoas) {
		return this.pessoaClient.consultaDadosListaPessoa(listaIdPessoas);
	}

}
