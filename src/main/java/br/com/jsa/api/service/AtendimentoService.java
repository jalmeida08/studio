package br.com.jsa.api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsa.api.client.PessoaClient;
import br.com.jsa.api.dto.AtendimentoDTO;
import br.com.jsa.api.dto.AtendimentoDetalhadoDTO;
import br.com.jsa.api.dto.AtendimentoHomeDTO;
import br.com.jsa.api.dto.BuscaDadosAgendaFuncionarioDTO;
import br.com.jsa.api.dto.ClienteDTO;
import br.com.jsa.api.dto.ProcedimentoAtendimentoDTO;
import br.com.jsa.api.dto.ValidaInclusaoAtendimentoDTO;
import br.com.jsa.api.form.AtendimentoForm;
import br.com.jsa.api.form.EditaAtendimentoForm;
import br.com.jsa.dominio.bo.AtendimentoBO;
import br.com.jsa.dominio.model.Atendimento;
import br.com.jsa.dominio.model.EstadoAtendimento;
import br.com.jsa.dominio.model.Funcionario;
import br.com.jsa.dominio.model.Procedimento;
import br.com.jsa.dominio.repository.AtendimentoRepository;
import br.com.jsa.dominio.repository.ProcedimentoRepository;
import br.com.jsa.dominio.validacao.ValidaFuncionarioAtendimento;
import br.com.jsa.dominio.validacao.ValidaIdFuncionario;
import br.com.jsa.infra.exception.NegocioException;
import br.com.jsa.infra.exception.ParametroInvalidaException;
import br.com.jsa.util.DateUtil;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private PessoaClient pessoaClient;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	private Atendimento getAtendimento(String id) {
		return atendimentoRepository
				.findById(id)
				.orElseThrow(() -> new ParametroInvalidaException("Procedimento informado não foi encontrado na base de dados"));
	}
	
	private Procedimento buscaProcedimentoDoAtendimento(String id) {
		return this.procedimentoRepository
			.findById(id)
			.orElseThrow(() -> new ParametroInvalidaException("Procedimento informado não foi encontrado na base de dados"));
	}

	private BigDecimal efetuarCalculoProcedimento(List<Procedimento> listaProcedimentoAtendimento, BigDecimal desconto) {
		return new AtendimentoBO()
				.calculaValorAtendimento(listaProcedimentoAtendimento, desconto);
	}
	
	private List<Procedimento> buscaListaProcedimentos(List<String> procedimentos) {
		var listaProcedimento = new ArrayList<Procedimento>();
		procedimentos
			.forEach( p -> listaProcedimento.add(buscaProcedimentoDoAtendimento(p)));
		return listaProcedimento;
	}

	private List<Atendimento> buscaAtendimentoPeriodoFuncionario(
			LocalDateTime dataHora1, LocalDateTime dataHora2, String estadoAtendimento, String idFuncionario){
		return 
				atendimentoRepository
						.findByDataHoraAtendimentoBetweenAndIdFuncionarioAndEstadoAtendimentoOrderByDataHoraAtendimentoAsc(
								dataHora1, dataHora2, idFuncionario, estadoAtendimento);
	}
	
	public ValidaInclusaoAtendimentoDTO validaNovoAtendimento(AtendimentoForm atendimentoForm) {
			final var listaProcedimentoAtendimento = buscaListaProcedimentos(atendimentoForm.getProcedimentos());
			var dataFimProcedimento =
					new AtendimentoBO()
					.calcularDataFimProcedimento(atendimentoForm.getDataHoraAtendimento(), listaProcedimentoAtendimento);
			
			return new ValidaInclusaoAtendimentoDTO(
					consultaSepossuiAtendimentosConflitantes(atendimentoForm.getIdFuncionario(), atendimentoForm.getDataHoraAtendimento(), dataFimProcedimento)
					.stream()
					.map(AtendimentoDTO::new)
					.collect(Collectors.toList()), atendimentoForm.toAtendimento());
	}
	
	public void adicionaAtendimento(AtendimentoForm atendimentoForm) {
//		Atendimento a = atendimentoForm.toAtendimento();
//		
//		validaidCliente(a.getIdCliente());
//		validaIdFuncionario(a.getIdFuncionario());
//		
//		var listaProcedimentoAtendimento = buscaListaProcedimentos(a.getProcedimentos());
//		
//		ValidaFuncionarioAtendimento
//			.validaSeFuncionarioPodeAtender(listaProcedimentoAtendimento, a.getIdFuncionario());
//		
//		var dataFimProcedimento = new AtendimentoBO()
//				.calcularDataFimProcedimento(a.getDataHoraAtendimento(), listaProcedimentoAtendimento);
//		
//		a.setDataHoraFimAtendimento(dataFimProcedimento);
//		
//		var listaAtendimentoConflitantes = 
//				consultaSepossuiAtendimentosConflitantes(
//						atendimentoForm.getIdFuncionario(), a.getDataHoraAtendimento(), a.getDataHoraFimAtendimento());
//		
//		if(!listaAtendimentoConflitantes.isEmpty())
//			throw new NegocioException("O horário informado colide com outros atendimentos");
//		
//		a.setValor(efetuarCalculoProcedimento(listaProcedimentoAtendimento, a.getDesconto()));
//		atendimentoRepository.save(a);
	}

	private Funcionario validaIdFuncionario(String id) {
		var funcionario = funcionarioService.consultaFuncionarioPorId(id);
		return new ValidaIdFuncionario()
				.identificadorIsValid(id, funcionario);	
	}

//	private ClienteDTO validaidCliente(String id) {
//		var cliente = clienteClient.buscaClientePorId(id);
//		return new ValidaIdCliente()
//				.identificadorIsValid(id, cliente);
//	}

	public ValidaInclusaoAtendimentoDTO validaEdicaoAtendimento(EditaAtendimentoForm form) {
		var a = getAtendimento(form.getId());
		var listaProcedimentosAtendimento = buscaListaProcedimentos(form.getProcedimentos());
		
		var dataFimProcedimento = new AtendimentoBO()
				.calcularDataFimProcedimento(form.getDataHoraAtendimento(), listaProcedimentosAtendimento);
		
		a.setDataHoraFimAtendimento(dataFimProcedimento);
		a.setDataHoraAtendimento(form.getDataHoraAtendimento());
		a.setDesconto(form.getDesconto());
		a.setProcedimentos(form.getProcedimentos());
		a.setValor(efetuarCalculoProcedimento(listaProcedimentosAtendimento, a.getDesconto()));
		
		var listaAtendimentoConflitantes = 
				consultaSepossuiAtendimentosConflitantes(
						a.getIdFuncionario(), form.getDataHoraAtendimento(), a.getDataHoraFimAtendimento());
		
		 
		return new ValidaInclusaoAtendimentoDTO(
				listaAtendimentoConflitantes
				.stream()
				.filter(atendimento -> !atendimento.getId().equals(form.getId()))
				.map(AtendimentoDTO::new)
				.collect(Collectors.toList()), a);
	}
	
	private List<Atendimento> consultaSepossuiAtendimentosConflitantes(
			String idFuncionario,
			LocalDateTime dataHoraAtendimento, LocalDateTime dataHoraFimAtendimento){
		
		var bo = new AtendimentoBO();
		var dataHoraInicioBusca = dataHoraAtendimento.truncatedTo(ChronoUnit.DAYS);
		var dataHoraFimBusca = dataHoraAtendimento.plusMinutes(240);
		var listaAtendimentoPeriodo = buscaAtendimentoPeriodoFuncionario(
								dataHoraInicioBusca, dataHoraFimBusca, EstadoAtendimento.AGENDADO.name(), idFuncionario);
		
		return bo.listarAtendimentosNoMesmoHorario(listaAtendimentoPeriodo, dataHoraAtendimento, dataHoraFimAtendimento);
	}

	public BigDecimal calculaValorAtendimento(List<String> listaProcedimentos, BigDecimal desconto) {
		return efetuarCalculoProcedimento(buscaListaProcedimentos(listaProcedimentos), desconto);
	}

	public ClienteDTO consultarDadosCliente(String id) {
//		return validaidCliente(id);
		return null;
	}

	public List<AtendimentoDTO> listaAtendimento() {
		return atendimentoRepository
				.findAll()
				.stream()
				.map(AtendimentoDTO::new)
				.collect(Collectors.toList());
	}

	public AtendimentoDTO desmarcaAtendimento(String id) {
		Atendimento a = getAtendimento(id);
		a.setEstadoAtendimento(EstadoAtendimento.DESMARCADO);
		atendimentoRepository.save(a);
		return new AtendimentoDTO(a);
	}

	public void finalizaAtendimento(String id) {
		Atendimento a = getAtendimento(id);
		a.setEstadoAtendimento(EstadoAtendimento.FINALIZADO);
		atendimentoRepository.save(a);
	}

	public List<AtendimentoDTO> listaAtendimentoMesAnoInformado(String anoMes) {
		final LocalDateTime dataInicio;
		final LocalDateTime dataFim;
		if(anoMes.isEmpty()) {
			dataInicio = DateUtil.retornaDataInicio("");
			dataFim = DateUtil.retornaDataFim("");
		} else {
			dataInicio = DateUtil.retornaDataInicio(anoMes);
			dataFim = DateUtil.retornaDataFim(anoMes);
		}
		var listaEstadoAtendimento = List.of(EstadoAtendimento.AGENDADO.name(), EstadoAtendimento.FINALIZADO.name());
		return atendimentoRepository
			.findByDataHoraAtendimentoBetweenAndEstadoAtendimentoInOrderByDataHoraAtendimentoAsc(
					dataInicio, dataFim, listaEstadoAtendimento)
			.stream()
			.map(AtendimentoDTO::new)
			.collect(Collectors.toList());
	}
	
	public List<AtendimentoHomeDTO> listaAtendimentoDiaInformado(LocalDate dataInformada) {
//		final var dataInicio = dataInformada.atTime(LocalTime.MIN);
//		final var dataFim = dataInformada.atTime(LocalTime.MAX);
//		var listaAtendimento = new ArrayList<AtendimentoHomeDTO>();
//		var listaEstadoAtendimento = List.of(EstadoAtendimento.AGENDADO.name(), EstadoAtendimento.FINALIZADO.name());
//		atendimentoRepository
//			.findByDataHoraAtendimentoBetweenAndEstadoAtendimentoInOrderByDataHoraAtendimentoAsc(
//					dataInicio, dataFim, listaEstadoAtendimento)
//			.forEach(a -> {
//				var c = validaidCliente(a.getIdCliente());
//				var f = validaIdFuncionario(a.getIdFuncionario());
//				listaAtendimento.add(new AtendimentoHomeDTO(a, c, f));
//			});
//		return listaAtendimento;
			
		return null;
	}

	public List<AtendimentoDTO> buscaAgendaFuncionarioDia(BuscaDadosAgendaFuncionarioDTO buscaAgenda) {
		var dataHoraInicio = LocalDateTime.of(buscaAgenda.getDia(), LocalTime.MIN);
		var dataHoraFim = LocalDateTime.of(buscaAgenda.getDia(), LocalTime.MAX);
		
		return atendimentoRepository
			.findByDataHoraAtendimentoBetweenAndIdFuncionarioAndEstadoAtendimentoOrderByDataHoraAtendimentoAsc(
					dataHoraInicio, dataHoraFim, buscaAgenda.getId(), EstadoAtendimento.AGENDADO.name())
			.stream()
			.map(AtendimentoDTO::new)
			.collect(Collectors.toList());
		
	}

	public AtendimentoDetalhadoDTO consultaDadosAtendimento(String id) {
//		Atendimento a = getAtendimento(id);
//		ClienteDTO c = validaidCliente(a.getIdCliente());
//		Funcionario f = validaIdFuncionario(a.getIdFuncionario());
//		var listaProcedimento = a.getProcedimentos()
//			.stream()
//			.map(p -> new ProcedimentoAtendimentoDTO(procedimentoRepository.findById(p).get()))
//			.collect(Collectors.toList());
//
//			
//		return new AtendimentoDetalhadoDTO(a, c, f, listaProcedimento);
		return null;
	}

	public void editaAtendimento(EditaAtendimentoForm form) {
		var a = getAtendimento(form.getId());
		var listaProcedimentosAtendimento = buscaListaProcedimentos(form.getProcedimentos());
		
		var dataFimProcedimento = new AtendimentoBO()
				.calcularDataFimProcedimento(form.getDataHoraAtendimento(), listaProcedimentosAtendimento);
		
		a.setDataHoraFimAtendimento(dataFimProcedimento);
		a.setDataHoraAtendimento(form.getDataHoraAtendimento());
		a.setDesconto(form.getDesconto());
		a.setProcedimentos(form.getProcedimentos());
		a.setValor(efetuarCalculoProcedimento(listaProcedimentosAtendimento, a.getDesconto()));
		atendimentoRepository.save(a);
		
	}

}
