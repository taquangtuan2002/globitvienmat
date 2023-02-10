package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.ServiceType;
import com.globits.emr.dto.ServiceTypeDto;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, UUID>{
	@Query("Select new com.globits.emr.dto.ServiceTypeDto(entity) From ServiceType entity")
	List<ServiceTypeDto> getAllDto();
	
	@Query("Select new com.globits.emr.dto.ServiceTypeDto(entity) From ServiceType entity Where entity.id = ?1")
	ServiceTypeDto getDtoById(UUID id);
	
	@Query("Select count(entity.id) From ServiceType entity Where (entity.id <> ?1 OR ?1 is NULL) And entity.code = ?2")
	Long checkCode(UUID id, String code);
}
