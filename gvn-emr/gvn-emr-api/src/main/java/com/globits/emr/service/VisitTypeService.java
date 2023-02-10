package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import com.globits.emr.domain.VisitType;
import com.globits.emr.dto.PersonAttributeTypeDto;
import com.globits.emr.dto.VisitTypeDto;

public interface VisitTypeService {
	VisitType saveOrUpdate(VisitTypeDto dto);
	Boolean delete(UUID id);
	List<VisitType> getAllVisitTypes();
	VisitTypeDto deleteVoided(UUID id);
	Boolean checkCode(UUID id,String code);

}
