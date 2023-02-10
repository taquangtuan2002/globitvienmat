package com.globits.emr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globits.emr.domain.PersonAttribute;

public interface PersonAttributeRepository extends JpaRepository<PersonAttribute, UUID> {
	@Query(value = "SELECT entity FROM PersonAttribute entity WHERE entity.person.id =?1 and entity.personAttributeType.id=?2")
	PersonAttribute findByIDPersonANDPersonAttributeType(UUID id, UUID idType);
	@Query(value = "SELECT entity FROM PersonAttribute entity WHERE entity.person.id =?1")
	List<PersonAttribute> findByIDPerson(UUID id);

}
