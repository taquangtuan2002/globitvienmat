package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.HealthCareRoom;
import com.globits.emr.dto.HealthCareRoomDto;

@Repository
public interface HealthCareRoomRepository extends JpaRepository<HealthCareRoom, UUID>{
	@Query("Select new com.globits.emr.dto.HealthCareRoomDto(entity) From HealthCareRoom entity")
	List<HealthCareRoomDto> getAllDto();
	
	@Query("Select new com.globits.emr.dto.HealthCareRoomDto(entity) From HealthCareRoom entity Where entity.id = ?1")
	HealthCareRoomDto getDtoById(UUID id);
}
