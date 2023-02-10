package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import com.globits.emr.dto.EncounterTypeDto;
import com.globits.emr.dto.PersonAttributeTypeDto;

public interface EncounterTypeService {
	EncounterTypeDto saveOrUpdate(EncounterTypeDto dto);
	Boolean delete(UUID id);
	List<EncounterTypeDto> getAllEncounterTypeDtos();
	EncounterTypeDto deleteVoided(UUID id);
	Boolean checkCode(UUID id,String code);
}
