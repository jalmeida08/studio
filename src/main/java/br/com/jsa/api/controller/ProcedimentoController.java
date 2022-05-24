package br.com.jsa.api.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsa.api.dto.AdicionaFuncionarioProcedimentoDTO;
import br.com.jsa.api.dto.DetalheProcedimentoDTO;
import br.com.jsa.api.dto.ProcedimentoDTO;
import br.com.jsa.api.form.ProcedimentoForm;
import br.com.jsa.api.service.ProcedimentoService;

@RestController
@RequestMapping("procedimento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProcedimentoController {

	@Autowired
	private ProcedimentoService procedimentoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> salvaProcedimento(@RequestBody ProcedimentoForm procedimentoForm){
		this.procedimentoService.salvaProcedimento(procedimentoForm);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<?> listaProcedimento(){
		List<ProcedimentoDTO> listaProcedimento = this.procedimentoService.listaProcedimento();
		return ResponseEntity.ok(listaProcedimento);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultaProcedimento(@PathVariable("id") String id) {
		DetalheProcedimentoDTO detalheProcedimento = this.procedimentoService.consultaProcedimento(id);
		return ResponseEntity.ok(detalheProcedimento);
	}
	
	@PutMapping("/adiciona-funcionario")
	@Transactional
	public ResponseEntity<?> adicionaFuncionarioProcedimento(@RequestBody AdicionaFuncionarioProcedimentoDTO dto) {
		procedimentoService.adicionaFuncionarioProcedimento(dto);
		return ResponseEntity.ok().build();
	}
}
