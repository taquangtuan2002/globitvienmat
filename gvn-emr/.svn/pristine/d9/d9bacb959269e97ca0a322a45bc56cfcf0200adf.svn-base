package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import com.globits.core.domain.Ethnics;
import com.globits.core.domain.Profession;
import com.globits.core.dto.ProfessionDto;
import com.globits.core.service.GenericService;
import com.globits.emr.dto.VisitTypeDto;

public interface EmrProfessionService extends GenericService<Profession, UUID> {
	ProfessionDto saveOrUpdate(ProfessionDto dto);
	ProfessionDto getById(UUID id);
	ProfessionDto deleteVoided(UUID id);
	Boolean deleteByid(UUID id);
}
