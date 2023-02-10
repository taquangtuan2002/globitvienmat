package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.emr.domain.Encounter;
import com.globits.emr.dto.EncounterDto;
import com.globits.emr.dto.search.SearchDto;

public interface EncounterService extends GenericService<Encounter, UUID>{
	List<Encounter> getAll();
	
	Encounter getDtoById(UUID id);
	
	Encounter saveEncounter(Encounter encounter);
	
	Boolean deleteById(UUID id);
	
	void updateVoided(UUID id);
	
	Boolean deleteFakeVoided(UUID id);
	
	Page<EncounterDto> searchByPage(SearchDto searchDto);
}
