package com.globits.emr.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import com.globits.core.service.GenericService;
import com.globits.emr.domain.concept.ConceptDatatype;
import com.globits.emr.dto.concept.ConceptDatatypeDto;
import com.globits.emr.dto.search.SearchDto;

public interface ConceptDatatypeService extends GenericService<ConceptDatatype, UUID>{
	    ConceptDatatypeDto saveOrUpdate(UUID id, ConceptDatatypeDto dto);

	    Boolean deleteById(UUID id);

	    ConceptDatatypeDto getConceptDatatype(UUID id);

		ConceptDatatype getConceptDatatypeByCode(String code);

	    Page<ConceptDatatypeDto> searchByPage(SearchDto dto);

	    Boolean checkCode(UUID id, String code);

		void updateVoided(UUID id);

		Boolean deleteFakeVoided(UUID id);

}
