package com.microservicios.app.futfem.competitions.services.dto;

import java.util.List;

import com.microservicios.app.futfem.competitions.models.entity.Competition;

public record CompetitionPageResponse(
		List<Competition> content,
		long totalElements,
		int totalPages,
		int page,
		int size) {
}
