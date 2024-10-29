package br.gov.cade.admin_cade_apis_with_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cade.admin_cade_apis_with_client.dto.CadastroRequest;
import br.gov.cade.admin_cade_apis_with_client.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	private AlunoService alunoService;
	
	@Autowired
	public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
	}
	
	@PostMapping(value = "/cadastrar")
	public ResponseEntity cadastrarAluno(@RequestBody CadastroRequest cadastroRequest) {
		return alunoService.cadastrar(cadastroRequest);
	}
}
