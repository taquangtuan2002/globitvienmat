package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.emr.domain.ServiceFee;
import com.globits.emr.dto.ServiceFeeDto;
import com.globits.emr.dto.search.SearchDto;

public interface ServiceFeeService extends GenericService<ServiceFee, UUID>{
	List<ServiceFee> getAllDto();
	
	ServiceFee getDtoById(UUID id);
	
	ServiceFee saveOrUpdate(UUID id, ServiceFee entity);
	
	Boolean deleteById(UUID id);
	
	void updateVoided(UUID id);
	
	Boolean deleteFakeVoided(UUID id);
	
	Page<ServiceFeeDto> searchByPage(SearchDto searchDto);
}
