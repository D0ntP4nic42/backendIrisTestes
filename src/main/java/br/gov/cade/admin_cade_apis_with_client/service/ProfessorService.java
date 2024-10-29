package br.gov.cade.admin_cade_apis_with_client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.cade.admin_cade_apis_with_client.dto.CadastroRequest;
import br.gov.cade.admin_cade_apis_with_client.entities.Professor;
import br.gov.cade.admin_cade_apis_with_client.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	private ProfessorRepository professorRepository;
	
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
	@Autowired
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	public ResponseEntity cadastrar(CadastroRequest cadastroRequest) {
		try {
			Professor professor = new Professor();
			professor.setNome(cadastroRequest.nome());
			professor.setCpf(cadastroRequest.cpf());
			professor.setPassword(PASSWORD_ENCODER.encode(cadastroRequest.senha()));
			professor.setRole("ROLE_PROFESSOR");
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Erro ao cadastrar professor!");
		}
		
		return ResponseEntity.ok().body("Professor cadastrado com sucesso!");
	}

}
