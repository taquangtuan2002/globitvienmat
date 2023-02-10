package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import com.globits.emr.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.VisitType;
import com.globits.emr.dto.EncounterTypeDto;
import com.globits.emr.dto.VisitTypeDto;
@Repository
public interface VisitTypeRepository extends JpaRepository<VisitType, UUID>{
	
	@Query("select new com.globits.emr.dto.VisitTypeDto(e) from VisitType e ")
	List<VisitTypeDto> getAll();
	@Query("select count(entity.id) from VisitType entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	  Long checkCode(String code, UUID id);
	
	@Query("Select visitType.id From VisitType visitType Where visitType.code = ?1")
	UUID getVisitTypeByCode(String code);

	@Query("Select visitType From VisitType visitType Where visitType.code = ?1")
	VisitType getVisitType(String code);


}
