package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.concept.Concept;
import com.globits.emr.dto.ObservationDto;
import com.globits.emr.dto.concept.ConceptDto;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, UUID>{
	@Query("select count(entity.id) from Concept entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);

    @Query("select new com.globits.emr.dto.concept.ConceptDto(ct) from Concept ct")
    Page<ConceptDto> getListPage(Pageable pageable);

    @Query("Select entity From Concept entity")
    List<Concept> getAllEntity();

    @Query("Select count(entity.id) From Concept entity")
    Long countEntity();

    @Query("Select entity From Concept entity Where entity.code =?1")
    Concept getByCode(String code);
}
