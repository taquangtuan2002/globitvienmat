package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.HealthCareService;
import com.globits.emr.dto.HealthCareServiceDto;
import com.globits.emr.dto.ServiceFeeDto;

@Repository
public interface HealthCareServiceRepository extends JpaRepository<HealthCareService, UUID> {
	@Query("Select new com.globits.emr.dto.HealthCareServiceDto(entity) From HealthCareService entity")
	List<HealthCareServiceDto> getAllDto();
	
	@Query("Select new com.globits.emr.dto.HealthCareServiceDto(entity) From HealthCareService entity Where entity.id = ?1")
	HealthCareServiceDto getDtoById(UUID id);
	
	@Query("Select new com.globits.emr.dto.HealthCareServiceDto(entity) From HealthCareService entity")
	Page<ServiceFeeDto> getListPage( Pageable pageable);
}
