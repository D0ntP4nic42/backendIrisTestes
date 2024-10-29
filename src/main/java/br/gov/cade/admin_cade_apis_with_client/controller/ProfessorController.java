package br.gov.cade.admin_cade_apis_with_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cade.admin_cade_apis_with_client.dto.CadastroRequest;
import br.gov.cade.admin_cade_apis_with_client.entities.Professor;
import br.gov.cade.admin_cade_apis_with_client.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
	private ProfessorService professorService;
	
	@Autowired
	public ProfessorController(ProfessorService pessoaService) {
		this.professorService = pessoaService;
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity cadastrar(@RequestBody CadastroRequest cadastroRequest) {
		return ResponseEntity.ok(professorService.cadastrar(cadastroRequest));
	}
}
