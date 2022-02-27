package com.microservicios.app.futfem.competitions.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.app.common.controllers.CommonController;
import com.microservicios.app.futfem.competitions.models.entity.Competition;
import com.microservicios.app.futfem.competitions.services.CompetitionService;

@RestController
public class CompetitionController extends CommonController<Competition, CompetitionService> {
	

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Competition competition, @PathVariable Long id){
		Optional<Competition> o = service.findById(id);
		
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Competition competitionDb = o.get();
		competitionDb.setName(competition.getName());
		competitionDb.setAssociation(competition.getAssociation());
		competitionDb.setCountry(competition.getCountry());
		competitionDb.setUrlpic(competition.getUrlpic());
		competitionDb.setEstablished(competition.getEstablished());
		
		// service.save(competitionDb) permite persistir el equipo con los datos editados
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(competitionDb));  // HTTPStatus 201
	}

}
