package br.gov.cade.admin_cade_apis_with_client.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cade.admin_cade_apis_with_client.entities.User;
import br.gov.cade.admin_cade_apis_with_client.repositories.UserRepository;

@RestController
public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		var users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}
}
