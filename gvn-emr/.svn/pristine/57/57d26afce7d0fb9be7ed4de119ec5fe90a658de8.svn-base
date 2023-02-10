package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.emr.domain.HealthCareService;
import com.globits.emr.dto.HealthCareServiceDto;

public interface HealthCareServiceService extends GenericService<HealthCareService, UUID>{
	List<HealthCareServiceDto> getAllDto();
	
	HealthCareServiceDto getDtoById(UUID id);
	
	HealthCareServiceDto saveOrUpdate(UUID id, HealthCareServiceDto dto);
	
	Boolean deleteById(UUID id);
	
	void updateVoided(UUID id);
	
	Boolean deleteFakeVoided(UUID id);
	
	Page<HealthCareServiceDto> getListPage();
}
