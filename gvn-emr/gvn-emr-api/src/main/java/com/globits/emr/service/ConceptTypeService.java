package com.globits.emr.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import com.globits.core.service.GenericService;
import com.globits.emr.domain.concept.ConceptType;
import com.globits.emr.dto.concept.ConceptTypeDto;
import com.globits.emr.dto.search.SearchDto;

public interface ConceptTypeService extends GenericService<ConceptType, UUID>{
	    ConceptTypeDto saveOrUpdate(UUID id, ConceptTypeDto dto);

	    Boolean deleteById(UUID id);

	    ConceptTypeDto getConceptType(UUID id);

		ConceptType getConceptTypeByCode(String code);

	    Page<ConceptTypeDto> searchByPage(SearchDto dto);

	    Boolean checkCode(UUID id, String code);

		Boolean deleteFakeVoided(UUID id);

		void updateVoided(UUID id);

}
