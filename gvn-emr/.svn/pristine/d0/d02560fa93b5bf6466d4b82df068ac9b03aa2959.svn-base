package com.globits.emr.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import com.globits.core.service.GenericService;
import com.globits.emr.domain.concept.ConceptAttribute;
import com.globits.emr.dto.concept.ConceptAttributeDto;
import com.globits.emr.dto.search.SearchDto;

public interface ConceptAttributeService extends GenericService<ConceptAttribute, UUID>{
	    ConceptAttributeDto saveOrUpdate(UUID id, ConceptAttributeDto dto);

	    Boolean deleteById(UUID id);

	    ConceptAttributeDto getConceptAttribute(UUID id);

	    Page<ConceptAttributeDto> searchByPage(SearchDto dto);

}
