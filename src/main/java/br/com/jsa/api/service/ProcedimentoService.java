package br.com.jsa.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsa.api.client.FuncionarioClient;
import br.com.jsa.api.dto.AdicionaFuncionarioProcedimentoDTO;
import br.com.jsa.api.dto.DetalheProcedimentoDTO;
import br.com.jsa.api.dto.FuncionarioDTO;
import br.com.jsa.api.dto.ProcedimentoDTO;
import br.com.jsa.api.dto.VerificaIdFuncionarioDTO;
import br.com.jsa.api.form.ProcedimentoForm;
import br.com.jsa.infra.exception.NegocioException;
import br.com.jsa.infra.exception.ParametroInvalidaException;
import br.com.jsa.infra.model.Procedimento;
import br.com.jsa.infra.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private FuncionarioClient funcionarioClient;


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

	private void idFuncionarioIsValid(List<String> idFuncionario) throws NegocioException{
		
		List<VerificaIdFuncionarioDTO> lstFuncionariosValidos =
				this.funcionarioClient.idFuncionarioIsValid(idFuncionario);
		
		lstFuncionariosValidos.forEach(f -> {
			if(!f.isValido()) 
				throw new NegocioException("O funcionario com o id "+ f.getIdFuncionario() + " é inválido");
		});
		
	}

	public List<ProcedimentoDTO> listaProcedimento() {
		return this.procedimentoRepository
				.findAll()
				.stream()
				.map(ProcedimentoDTO::new)
				.collect(Collectors.toList());
	}

	public DetalheProcedimentoDTO consultaProcedimento(String id) {
		Procedimento p = buscaProcedimentoPorId(id);
		List<FuncionarioDTO> lstDadosFunci = new ArrayList<FuncionarioDTO>();
		if(p.getFuncionarios().size() > 0) 
			lstDadosFunci =
					funcionarioClient.consultaDadosListaFuncionario(p.getFuncionarios());
		
		return new DetalheProcedimentoDTO(p, lstDadosFunci);
	}

	public void adicionaFuncionarioProcedimento(AdicionaFuncionarioProcedimentoDTO dto) {
		var count = funcionarioClient
			.idFuncionarioIsValid(List.of(dto.getIdFuncionario()))
			.stream()
			.filter(a-> !a.isValido())
			.count();
		
		if(count > 0)
			throw new NegocioException("Codigo de funcionario inválido");
		
		Procedimento p = buscaProcedimentoPorId(dto.getIdProcedimento());
		p.getFuncionarios().add(dto.getIdFuncionario());
		procedimentoRepository.save(p);
	}


	
}
