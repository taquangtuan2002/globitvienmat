package com.globits.emr.service;

import java.util.UUID;

import com.globits.emr.domain.PersonAttribute;
import com.globits.emr.dto.PersonAttributeDto;
import com.globits.emr.dto.PersonAttributeTypeDto;

public interface PersonAttributeService {
	PersonAttributeDto saveOrUpdate(PersonAttributeDto dto);
	Boolean delete( UUID id);
	PersonAttributeDto deleteVoided(UUID id);
	PersonAttributeDto getById(UUID id);
	PersonAttribute saveEntity(PersonAttribute peronAttr);
	PersonAttribute getByIdEntity(UUID id);
	PersonAttribute deleteVoidedEntity(UUID id);
}
