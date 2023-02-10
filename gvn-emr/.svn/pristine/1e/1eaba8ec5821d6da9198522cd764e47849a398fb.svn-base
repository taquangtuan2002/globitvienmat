package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.EncounterType;
import com.globits.emr.dto.EncounterTypeDto;


@Repository
public interface EncounterTypeRepository extends JpaRepository<EncounterType, UUID>{
	@Query("select new com.globits.emr.dto.EncounterTypeDto(e) from EncounterType e ")
	List<EncounterTypeDto> getAll();
	@Query("select count(entity.id) from EncounterType entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	  Long checkCode(String code, UUID id);
	
	@Query("Select entity From EncounterType entity Where entity.code = ?1")
	EncounterType getEncounterByCode(String code);

	@Query("Select entity.id From EncounterType entity Where entity.code = ?1")
	UUID getIdEncounterType(String code);
}
