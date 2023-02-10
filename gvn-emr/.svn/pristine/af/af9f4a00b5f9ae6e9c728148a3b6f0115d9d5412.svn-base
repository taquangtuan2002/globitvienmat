package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.Visit;
import com.globits.emr.dto.VisitDto;

@Repository
public interface VisitRepository extends JpaRepository<Visit, UUID> {
	@Query("Select new com.globits.emr.dto.VisitDto(entity) From Visit entity")
	List<VisitDto> geAllDto();

	@Query("Select new com.globits.emr.dto.VisitDto(entity) From Visit entity Where entity.id = ?1")
	VisitDto getDtoById(UUID id);
	
	@Query("Select entity From Visit entity Where entity.id = ?1")
	Visit getEntityById(UUID id);
}
