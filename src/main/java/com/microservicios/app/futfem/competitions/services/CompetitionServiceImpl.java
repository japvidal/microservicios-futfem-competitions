package com.microservicios.app.futfem.competitions.services;

import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservicios.app.common.services.CommonServiceImpl;
import com.microservicios.app.futfem.competitions.models.entity.Competition;
import com.microservicios.app.futfem.competitions.models.repository.CompetitionRepository;
import com.microservicios.app.futfem.competitions.services.dto.CompetitionPageResponse;
import com.microservicios.app.futfem.competitions.services.dto.CompetitionSearchRequest;

@Service
public class CompetitionServiceImpl extends CommonServiceImpl<Competition, CompetitionRepository> implements CompetitionService {

	private static final int DEFAULT_PAGE = 0;
	private static final int DEFAULT_SIZE = 25;

	@Override
	public CompetitionPageResponse searchCompetitions(CompetitionSearchRequest request) {
		CompetitionSearchRequest safeRequest = request == null ? new CompetitionSearchRequest() : request;
		int page = safeRequest.getPage() == null || safeRequest.getPage() < 0 ? DEFAULT_PAGE : safeRequest.getPage();
		int size = safeRequest.getSize() == null || safeRequest.getSize() <= 0 ? DEFAULT_SIZE : safeRequest.getSize();

		Pageable pageable = PageRequest.of(page, size,
				Sort.by("name").ascending().and(Sort.by("association").ascending()));
		Page<Competition> result = repository.searchCompetitions(
				normalize(safeRequest.getSearch()),
				normalizeCountry(safeRequest.getCountry()),
				pageable);

		return new CompetitionPageResponse(result.getContent(), result.getTotalElements(), result.getTotalPages(),
				result.getNumber(), result.getSize());
	}

	private String normalize(String value) {
		if (value == null) {
			return null;
		}
		String normalized = value.trim().replaceAll("\\s+", " ");
		return normalized.isEmpty() ? null : normalized;
	}

	private String normalizeCountry(String value) {
		String normalized = normalize(value);
		return normalized == null ? null : normalized.toUpperCase(Locale.ROOT);
	}

}
