package com.globits.emr.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globits.core.domain.Person;
import com.globits.core.repository.PersonRepository;
import com.globits.emr.domain.PersonAttribute;
import com.globits.emr.domain.PersonAttributeType;
import com.globits.emr.domain.VisitType;
import com.globits.emr.dto.PersonAttributeDto;
import com.globits.emr.dto.VisitTypeDto;
import com.globits.emr.repository.PersonAttributeRepository;
import com.globits.emr.repository.PersonAttributeTypeRepository;
import com.globits.emr.service.PersonAttributeService;
@Service
public class PersonAttributeServiceImpl implements PersonAttributeService {
@Autowired 
PersonAttributeRepository personAttributeRepository;
@Autowired
PersonAttributeTypeRepository personAttributeTypeRepository;
@Autowired
PersonRepository personRepository;
	@Override
	public PersonAttributeDto saveOrUpdate(PersonAttributeDto dto) {
		PersonAttribute entity=null;
		if(dto.getId()!=null) {
			entity=personAttributeRepository.getOne(dto.getId());
		}
		if(entity==null) {
			entity=new PersonAttribute();
			entity.setCreateDate(LocalDateTime.now());
			entity.setModifyDate(LocalDateTime.now());
		}
		if(dto.getPerson_attribute_type_Id()!=null) {
			PersonAttributeType personAttributeType=personAttributeTypeRepository.getOne(dto.getId());
			entity.setPersonAttributeType(personAttributeType);
		}
		if(dto.getPerson_id()!=null) {
			Person person=personRepository.getOne(dto.getPerson_id());
			entity.setPerson(person);
		}
		if(dto.getValue()!=null) {
			entity.setValue(dto.getValue());
		}
		entity =personAttributeRepository.save(entity);
		return new PersonAttributeDto(entity);
	}

	@Override
	public Boolean delete(UUID id) {
		if (id != null) {
			PersonAttribute entity;
			Optional<PersonAttribute> optional = personAttributeRepository.findById(id);
			if (optional.isPresent()) {
				entity = optional.get();
				personAttributeRepository.delete(entity);
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	@Override
	public PersonAttributeDto deleteVoided(UUID id) {
	PersonAttribute entity = new PersonAttribute();
		if (id != null) {

			entity = personAttributeRepository.getOne(id);

		}
		entity.setVoided(false);
		personAttributeRepository.save(entity);
		return new PersonAttributeDto(entity);
	}

	@Override
	public PersonAttributeDto getById(UUID id) {
		PersonAttribute entity=personAttributeRepository.getOne(id);
		return new PersonAttributeDto(entity) ;
	}
	
	@Override
	public PersonAttribute saveEntity(PersonAttribute peronAttr) {
		PersonAttribute entity=null;
		if(peronAttr.getId()!=null) {
			entity=personAttributeRepository.getOne(peronAttr.getId());
		}
		if(entity==null) {
			entity=new PersonAttribute();
			entity.setCreateDate(LocalDateTime.now());
			entity.setModifyDate(LocalDateTime.now());
		}
		if(peronAttr.getPersonAttributeType()!=null) {
			PersonAttributeType personAttributeType=personAttributeTypeRepository.getOne(peronAttr.getPersonAttributeType().getId());
			entity.setPersonAttributeType(personAttributeType);
		}
		if(peronAttr.getPerson()!=null) {
			if(peronAttr.getPerson().getId()!=null) {
				Person person=personRepository.getOne(peronAttr.getPerson().getId());
				entity.setPerson(person);
			}
		}
		if(peronAttr.getValue()!=null) {
			entity.setValue(peronAttr.getValue());
		}
		entity =personAttributeRepository.save(entity);
		return entity;
	}
	@Override
	public PersonAttribute deleteVoidedEntity(UUID id) {
	PersonAttribute entity = new PersonAttribute();
		if (id != null) {
			entity = personAttributeRepository.getOne(id);
		}
		entity.setVoided(false);
		personAttributeRepository.save(entity);
		return entity;
	}

	@Override
	public PersonAttribute getByIdEntity(UUID id) {
		PersonAttribute entity=personAttributeRepository.getOne(id);
		return entity ;
	}

}
