package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.concept.ConceptAttributeType;
import com.globits.emr.dto.concept.ConceptAttributeTypeDto;

@Repository
public interface ConceptAttributeTypeRepository extends JpaRepository<ConceptAttributeType, UUID>{
	@Query("select count(entity.id) from ConceptAttributeType entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);

    @Query("select new com.globits.emr.dto.concept.ConceptAttributeTypeDto(ct) from ConceptAttributeType ct")
    Page<ConceptAttributeTypeDto> getListPage(Pageable pageable);

    @Query("select entity from ConceptAttributeType entity where entity.name =?1")
    List<ConceptAttributeType> findByName(String name);

    @Query("select new com.globits.emr.dto.concept.ConceptAttributeTypeDto(entity) from ConceptAttributeType entity where entity.code =?1")
    ConceptAttributeTypeDto findByCode(String code);
}
