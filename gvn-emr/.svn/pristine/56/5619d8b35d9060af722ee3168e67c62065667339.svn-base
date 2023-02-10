package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.Patient;
import com.globits.emr.dto.PatientDto;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID>{
	@Query("select count(entity.id) from Patient entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);

    @Query("select new com.globits.emr.dto.PatientDto(ct) from Patient ct")
    Page<PatientDto> getListPage(Pageable pageable);


    @Query("select entity from Patient entity where entity.code =?1")
    Patient findByCode(String code);
}
