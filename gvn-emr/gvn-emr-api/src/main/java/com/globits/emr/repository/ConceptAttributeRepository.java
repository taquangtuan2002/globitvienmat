package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.concept.ConceptAttribute;
import com.globits.emr.dto.concept.ConceptAttributeDto;

@Repository
public interface ConceptAttributeRepository extends JpaRepository<ConceptAttribute, UUID>{

    @Query("select new com.globits.emr.dto.concept.ConceptAttributeDto(ct) from ConceptAttribute ct")
    Page<ConceptAttributeDto> getListPage(Pageable pageable);

    @Query("select entity from ConceptAttribute entity where entity.value =?1")
    List<ConceptAttribute> findByValue(String value);

}
