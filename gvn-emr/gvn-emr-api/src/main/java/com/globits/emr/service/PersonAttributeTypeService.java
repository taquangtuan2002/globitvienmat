package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import com.globits.emr.domain.PersonAttributeType;
import com.globits.emr.dto.PersonAttributeTypeDto;

public interface PersonAttributeTypeService {
	PersonAttributeTypeDto saveOrUpdate(PersonAttributeTypeDto dto);
	Boolean delete(UUID id);
	List<PersonAttributeTypeDto> getAllPersonAttributeTypeDtos();
	PersonAttributeTypeDto deleteVoided(UUID id);
	Boolean checkCode(UUID id,String code);
	PersonAttributeType saveEntity(PersonAttributeType personAT);
	PersonAttributeType deleteVoidedEntity(UUID id);
	List<PersonAttributeType> getAllPersonAttributeType();
}
