package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.Encounter;
import com.globits.emr.dto.EncounterDto;

@Repository
public interface EncounterRepository extends JpaRepository<Encounter, UUID> {
	@Query("Select new com.globits.emr.dto.EncounterDto(entity) From Encounter entity")
	List<EncounterDto> getAllDto();
	
	@Query("Select new com.globits.emr.dto.EncounterDto(entity) From Encounter entity Where entity.id = ?1")
	EncounterDto getDtoById(UUID id);

	@Query("Select entity From Encounter entity Where entity.visit.id = ?1 and entity.encounterType.id = ?2")
	List<Encounter> getEncounterByVisitId(UUID visitId, UUID encounterType);
}
