package br.gov.cade.admin_cade_apis_with_client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.cade.admin_cade_apis_with_client.dto.CadastroRequest;
import br.gov.cade.admin_cade_apis_with_client.entities.Aluno;
import br.gov.cade.admin_cade_apis_with_client.repositories.AlunoRepository;

@Service
public class AlunoService {
	private AlunoRepository alunoRepository;
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
	@Autowired
	public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
	}

	public ResponseEntity cadastrar( CadastroRequest cadastroRequest) {
		
		try {
			Aluno aluno = new Aluno();
			aluno.setNome(cadastroRequest.nome());
			aluno.setCpf(cadastroRequest.cpf());
			aluno.setRole("ROLE_ALUNO");
			aluno.setPassword(PASSWORD_ENCODER.encode(cadastroRequest.senha()));
			alunoRepository.save(aluno);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Erro ao cadastrar aluno!");
		}

		return ResponseEntity.ok("Aluno cadastrado com sucesso!");
	}

}
