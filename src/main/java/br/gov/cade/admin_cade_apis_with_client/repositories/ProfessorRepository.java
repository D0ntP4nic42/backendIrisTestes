package br.gov.cade.admin_cade_apis_with_client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.cade.admin_cade_apis_with_client.entities.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
