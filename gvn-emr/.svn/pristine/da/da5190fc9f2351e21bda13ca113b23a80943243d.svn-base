package com.globits.emr.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.domain.Country;
import com.globits.core.dto.CountryDto;
import com.globits.core.service.GenericService;
import com.globits.emr.dto.search.SearchDto;

public interface EmrCountryService extends GenericService<Country, UUID>{

	CountryDto getDtoById(UUID id);
	
	CountryDto saveOrUpdate(UUID id, CountryDto dto);
	
	Boolean deleteById(UUID id);
	
	void updateVoided(UUID id);
	
	Boolean deleteFakeVoided(UUID id);
	
	Page<CountryDto> searchByPage(SearchDto searchDto);
}
