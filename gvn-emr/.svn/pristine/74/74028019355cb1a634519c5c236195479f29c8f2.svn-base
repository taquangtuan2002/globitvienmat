package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.emr.domain.PersonAttribute;
import com.globits.emr.domain.PersonAttributeType;
import com.globits.emr.dto.PersonAttributeTypeDto;

@Repository
public interface PersonAttributeTypeRepository extends JpaRepository<PersonAttributeType, UUID> {
	@Query(value = "SELECT * FROM tbl_person_attribute_type WHERE id =:id", nativeQuery = true)
	PersonAttributeType getById(UUID id);
	@Query(value = "SELECT * FROM tbl_person_attribute_type WHERE code =:code", nativeQuery = true)
	PersonAttributeType findByCode(String code);
	@Query("select new com.globits.emr.dto.PersonAttributeTypeDto(p) from PersonAttributeType p ")
	List<PersonAttributeTypeDto> getAll();
	@Query("select p from PersonAttributeType p ")
	List<PersonAttributeType> getAllEntity();
	@Query("select count(entity.id) from PersonAttributeType entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);

}
