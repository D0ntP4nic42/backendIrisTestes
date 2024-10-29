package br.gov.cade.admin_cade_apis_with_client.entities;

import java.util.Set;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Aluno extends User {
	@Nullable
	@ManyToMany(mappedBy = "alunos")
	private Set<Turma> turmas;

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}
}
