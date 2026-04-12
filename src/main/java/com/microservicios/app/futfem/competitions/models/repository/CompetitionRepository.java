package com.microservicios.app.futfem.competitions.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservicios.app.futfem.competitions.models.entity.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

	@Query("""
			select c from Competition c
			where (:search is null
					or lower(coalesce(c.name, '')) like lower(concat('%', :search, '%'))
					or lower(coalesce(c.association, '')) like lower(concat('%', :search, '%')))
			  and (:country is null or upper(coalesce(c.country, '')) = upper(:country))
			""")
	Page<Competition> searchCompetitions(@Param("search") String search, @Param("country") String country,
			Pageable pageable);
}
