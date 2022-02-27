package com.microservicios.app.futfem.competitions.services;

import org.springframework.stereotype.Service;

import com.microservicios.app.common.services.CommonServiceImpl;
import com.microservicios.app.futfem.competitions.models.entity.Competition;
import com.microservicios.app.futfem.competitions.models.repository.CompetitionRepository;

@Service
public class CompetitionServiceImpl extends CommonServiceImpl<Competition, CompetitionRepository> implements CompetitionService {


}
