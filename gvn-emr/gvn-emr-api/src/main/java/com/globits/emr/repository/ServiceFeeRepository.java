package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.ServiceFee;
import com.globits.emr.dto.ServiceFeeDto;

@Repository
public interface ServiceFeeRepository extends JpaRepository<ServiceFee, UUID>{
	@Query("Select new com.globits.emr.dto.ServiceFeeDto(entity) From ServiceFee entity")
	List<ServiceFeeDto> getAllDto();
	
	@Query("Select new com.globits.emr.dto.ServiceFeeDto(entity) From ServiceFee entity Where entity.id = ?1")
	ServiceFeeDto getDtoById(UUID id);
	
	@Query("Select new com.globits.emr.dto.ServiceFeeDto(entity) From ServiceFee entity")
	Page<ServiceFeeDto> getListPage( Pageable pageable);
}
