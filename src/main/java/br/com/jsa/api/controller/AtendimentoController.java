package br.com.jsa.api.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsa.api.dto.AtendimentoDTO;
import br.com.jsa.api.dto.AtendimentoDetalhadoDTO;
import br.com.jsa.api.dto.AtendimentoDiaDTO;
import br.com.jsa.api.dto.BuscaDadosAgendaFuncionarioDTO;
import br.com.jsa.api.form.AtendimentoForm;
import br.com.jsa.api.form.EditaAtendimentoForm;
import br.com.jsa.api.service.AtendimentoService;

@RestController
@RequestMapping("atendimento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AtendimentoController {
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@PostMapping("/valida-atendimento")
	public ResponseEntity<?> validaNovoAtendimento(@RequestBody @Validated AtendimentoForm atendimentoForm){
		var valida = atendimentoService.validaNovoAtendimento(atendimentoForm);
		return ResponseEntity.ok(valida);
	}
	
	@GetMapping("/ano-mes")
	public ResponseEntity<?> listaAtendimentoMesAnoInformado(@QueryParam("mesAno") String anoMes){
		var listaAtendimento = atendimentoService.listaAtendimentoMesAnoInformado(anoMes);
		return  ResponseEntity.ok(listaAtendimento);
	}
	
	@PostMapping("/lista-atendimento-dia")
	public ResponseEntity<?> listaAtendimentoDiaInformado(@RequestBody AtendimentoDiaDTO atendimentoDia){
		var listaAtendimento = atendimentoService
				.listaAtendimentoDiaInformado(atendimentoDia.getAtendimentoDia());
		return  ResponseEntity.ok(listaAtendimento);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> adicionaAtendimento(@RequestBody @Validated AtendimentoForm atendimentoForm){
		atendimentoService.adicionaAtendimento(atendimentoForm);
		return ResponseEntity.ok().build();
	}
	
//	@PostMapping("/calcula-valor-atendimento")
//	public ResponseEntity<?> calculaValorAtendimento(@RequestBody List<String> listaProcedimentos){
//		final Double calculaValorAtendimento = atendimentoService.calculaValorAtendimento(listaProcedimentos);
//		return ResponseEntity.ok(calculaValorAtendimento);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultaDadosAtendimento(@PathVariable("id") String id){
		AtendimentoDetalhadoDTO dto = atendimentoService.consultaDadosAtendimento(id);
		return ResponseEntity.ok(dto);
	}
	 
	@GetMapping
	public ResponseEntity<?> listaAtendimento(){
		List<AtendimentoDTO> dto = atendimentoService.listaAtendimento();
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}/desmarca-atendimento")
	@Transactional
	public ResponseEntity<?> desmarcaAtendimento(@PathVariable("id") String id){
		AtendimentoDTO desmarcado = atendimentoService.desmarcaAtendimento(id);
		return ResponseEntity.ok(desmarcado);
	}
	
	@PutMapping("/{id}/finaliza-atendimento")
	@Transactional
	public ResponseEntity<?> finalizaAtendimento(@PathVariable("id") String id){
		atendimentoService.finalizaAtendimento(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/agenda-dia-funcionario")
	public ResponseEntity<?> buscaAgendaFuncionario(@RequestBody BuscaDadosAgendaFuncionarioDTO buscaAgenda){
		List<AtendimentoDTO> listaAtendimento = 
				atendimentoService.buscaAgendaFuncionarioDia(buscaAgenda);
		return  ResponseEntity.ok(listaAtendimento);
		
	}
	
	@PostMapping("/valida-edicao-atendimento")
	public ResponseEntity<?> validaEdicaoAtendimento(@RequestBody EditaAtendimentoForm form){
		var dto =  atendimentoService.validaEdicaoAtendimento(form);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/edita-atendimento")
	@Transactional
	public ResponseEntity<?> editaAtendimento(@RequestBody EditaAtendimentoForm form){
		atendimentoService.editaAtendimento(form);
		return ResponseEntity.ok().build();
	}
}
