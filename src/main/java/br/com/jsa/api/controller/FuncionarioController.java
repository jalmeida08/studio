package br.com.jsa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsa.api.form.FuncionarioForm;
import br.com.jsa.api.service.FuncionarioService;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> salva(@RequestBody FuncionarioForm form) {
		this.funcionarioService.salvaFuncionario(form);
		return ResponseEntity.ok().build();
	}
}
