package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import com.globits.emr.visit.IehVisitDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.globits.core.service.GenericService;
import com.globits.emr.domain.Visit;
import com.globits.emr.dto.VisitDto;
import com.globits.emr.dto.search.SearchDto;

public interface VisitService extends GenericService<Visit, UUID>{
	Visit startVisit(UUID patientId);

	IehVisitDto startIehVisit(UUID patientId);

	IehVisitDto getIehVisit(UUID visitId);
	
	List<Visit> getAll();
	
	Visit getById(UUID id);
	
	Visit saveOrUpdate(VisitDto dto);

	Visit saveIehVisit(IehVisitDto dto);
	
	Visit saveVisit(Visit visit);
	
	Boolean deleteById(UUID id);
	
	void updateVoided(UUID id);
	
	Boolean deleteFakeVoided(UUID id);
	
	Page<VisitDto> searchByPage(SearchDto searchDto);
}
