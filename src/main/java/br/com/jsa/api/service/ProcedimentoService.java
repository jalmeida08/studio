package br.com.jsa.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsa.api.dto.AdicionaFuncionarioProcedimentoDTO;
import br.com.jsa.api.dto.DetalheProcedimentoDTO;
import br.com.jsa.api.dto.FuncionarioDTO;
import br.com.jsa.api.dto.ProcedimentoDTO;
import br.com.jsa.api.form.ProcedimentoForm;
import br.com.jsa.dominio.model.Procedimento;
import br.com.jsa.dominio.repository.ProcedimentoRepository;
import br.com.jsa.infra.exception.NegocioException;
import br.com.jsa.infra.exception.ParametroInvalidaException;

@Service
public class ProcedimentoService {
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	@Autowired
	private FuncionarioService funcionarioService;

	private Procedimento buscaProcedimentoPorId(String id) {
		return procedimentoRepository
				.findById(id)
				.orElseThrow(() -> new ParametroInvalidaException("Não foi possível localizar o id informado"));
	}
	
	public void salvaProcedimento(ProcedimentoForm procedimentoForm) {
		try {
			if(procedimentoForm.getIdFuncionario().size() > 0)
				this.idFuncionarioIsValid(procedimentoForm.getIdFuncionario());
			
			this.procedimentoRepository.save(procedimentoForm.toProcedimento());		
		}catch(NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	private void idFuncionarioIsValid(List<String> listaIdFuncionario) throws NegocioException{
		
		Map<String, Boolean> lstFuncionariosValidos =
				this.funcionarioService.idFuncionarioIsValid(listaIdFuncionario);
		
		lstFuncionariosValidos.forEach((id, isValid) -> {
			if(!isValid) 
				throw new NegocioException("O funcionario com a chave informada "+ id + " é inválido");
		});
		
	}

	public List<ProcedimentoDTO> listaProcedimento() {
		return this.procedimentoRepository
				.findAll()
				.stream()
				.map(ProcedimentoDTO::new)
				.collect(Collectors.toUnmodifiableList());
	}

	public DetalheProcedimentoDTO consultaProcedimento(String id) {
		Procedimento p = buscaProcedimentoPorId(id);
		List<FuncionarioDTO> lstDadosFunci = new ArrayList<FuncionarioDTO>();
		if(p.getFuncionarios().size() > 0) 
			lstDadosFunci =
					funcionarioService.consultaDadosListaFuncionario(p.getFuncionarios());
		
		return new DetalheProcedimentoDTO(p, lstDadosFunci);
	}

	public void adicionaFuncionarioProcedimento(AdicionaFuncionarioProcedimentoDTO dto) {
		final var isValid = funcionarioService.idIsValid(dto.getIdFuncionario());
		
		if(!isValid)
			new NegocioException("Codigo de funcionario inválido");
		
		var p = buscaProcedimentoPorId(dto.getIdProcedimento());
		p.getFuncionarios().add(dto.getIdFuncionario());
		procedimentoRepository.save(p);
	}


	
}
