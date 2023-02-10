package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.emr.domain.HealthCareRoom;
import com.globits.emr.dto.HealthCareRoomDto;
import com.globits.emr.dto.search.SearchDto;

public interface HealthCareRoomService extends GenericService<HealthCareRoom, UUID>{
	List<HealthCareRoomDto> getAllDto();
	
	HealthCareRoomDto getDtoById(UUID id);
	
	HealthCareRoomDto saveOrUpdate(UUID id, HealthCareRoomDto dto);
	
	Boolean deleteById(UUID id);
	
	void updateVoided(UUID id);
	
	Boolean deleteFakeVoided(UUID id);
	
	Page<HealthCareRoomDto> searchByPage(SearchDto searchDto);
}
