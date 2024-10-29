package br.gov.cade.admin_cade_apis_with_client.controller;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cade.admin_cade_apis_with_client.dto.LoginRequest;
import br.gov.cade.admin_cade_apis_with_client.dto.LoginResponse;
import br.gov.cade.admin_cade_apis_with_client.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoints para autenticação")
public class AuthController {
	private final JwtEncoder jwtEncoder;

	private final UserRepository userRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public AuthController(JwtEncoder jwtEncoder, UserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.jwtEncoder = jwtEncoder;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		var user = userRepository.findByNome(loginRequest.username())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		if (bCryptPasswordEncoder.matches(loginRequest.password(), user.getPassword())) {
			var now = Instant.now();
			var expiresIn = 300L;

			var roles = Arrays.asList(user.getRole());

			var claims = JwtClaimsSet.builder().issuer("admin-apis-cade").subject(user.getUserId().toString())
					.expiresAt(now.plusSeconds(expiresIn)).issuedAt(now).claim("roles", roles).build();

			var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

			return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));

		} else {
			throw new RuntimeException("Senha incorreta");
		}

	}
}
