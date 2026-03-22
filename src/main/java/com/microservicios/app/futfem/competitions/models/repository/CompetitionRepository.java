package com.microservicios.app.futfem.competitions.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservicios.app.futfem.competitions.models.entity.Competition;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

	
	
}
