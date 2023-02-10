package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.concept.ConceptDatatype;
import com.globits.emr.dto.concept.ConceptDatatypeDto;

@Repository
public interface ConceptDatatypeRepository extends JpaRepository<ConceptDatatype, UUID>{
	@Query("select count(entity.id) from ConceptDatatype entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);

    @Query("select new com.globits.emr.dto.concept.ConceptDatatypeDto(ct) from ConceptDatatype ct")
    Page<ConceptDatatypeDto> getListPage(Pageable pageable);

    @Query("select entity from ConceptDatatype entity where entity.name =?1")
    List<ConceptDatatype> findByName(String name);

    @Query("select entity From ConceptDatatype entity where entity.code =?1")
    ConceptDatatype getByCode(String code);
}
