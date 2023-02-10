package com.globits.emr.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.domain.Ethnics;
import com.globits.core.dto.EthnicsDto;
import com.globits.core.service.GenericService;
import com.globits.emr.dto.search.SearchDto;

public interface EmrEthnicsService extends GenericService<Ethnics, UUID>{	
	
	EthnicsDto getDtoById(UUID id);
	
	EthnicsDto saveOrUpdate(UUID id, EthnicsDto dto);
	
	Boolean deleteById(UUID id);
	
	void updateVoided(UUID id);
	
	Boolean deleteFakeVoided(UUID id);
	
	Page<EthnicsDto> searchByPage(SearchDto searchDto);
}
