package com.globits.emr.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import com.globits.core.service.GenericService;
import com.globits.emr.domain.concept.ConceptAttributeType;
import com.globits.emr.dto.concept.ConceptAttributeTypeDto;
import com.globits.emr.dto.search.SearchDto;

public interface ConceptAttributeTypeService extends GenericService<ConceptAttributeType, UUID>{
	    ConceptAttributeTypeDto saveOrUpdate(UUID id, ConceptAttributeTypeDto dto);

	    Boolean deleteById(UUID id);

	    ConceptAttributeTypeDto getConceptAttributeType(UUID id);

	    Page<ConceptAttributeTypeDto> searchByPage(SearchDto dto);

	    Boolean checkCode(UUID id, String code);

		Boolean deleteFakeVoided(UUID id);

		void updateVoided(UUID id);

}
