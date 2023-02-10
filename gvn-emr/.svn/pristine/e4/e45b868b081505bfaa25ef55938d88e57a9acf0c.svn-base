package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.concept.ConceptType;
import com.globits.emr.dto.concept.ConceptTypeDto;

@Repository
public interface ConceptTypeRepository extends JpaRepository<ConceptType, UUID>{
	@Query("select count(entity.id) from ConceptType entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);

    @Query("select new com.globits.emr.dto.concept.ConceptTypeDto(ct) from ConceptType ct")
    Page<ConceptTypeDto> getListPage(Pageable pageable);

    @Query("select entity from ConceptType entity where entity.name =?1")
    List<ConceptType> findByName(String name);

    @Query("Select entity From ConceptType entity where entity.code =?1")
    ConceptType getByCode(String code);
}
