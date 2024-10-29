package br.gov.cade.admin_cade_apis_with_client.dto;

public record LoginResponse(String jwt, Long expiresIn) {

}
