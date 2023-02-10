package com.globits.emr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.emr.domain.PersonAttributeType;
import com.globits.emr.dto.PersonAttributeTypeDto;
import com.globits.emr.repository.PersonAttributeTypeRepository;
import com.globits.emr.service.PersonAttributeTypeService;

@Service
public class PersonAttributeTypeServiceImpl implements PersonAttributeTypeService {
	@Autowired
	PersonAttributeTypeRepository personAttributeTypeRepository;

	@Override
	public PersonAttributeTypeDto saveOrUpdate(PersonAttributeTypeDto dto) {
		PersonAttributeType entity = null;
		if (dto.getId() != null) {
			entity = personAttributeTypeRepository.getById(dto.getId());
		}
		if (entity == null) {
			entity = new PersonAttributeType();
			entity.setCreateDate(LocalDateTime.now());
			entity.setModifyDate(LocalDateTime.now());
		}
		if (dto.getCode() != null) {
			entity.setCode(dto.getCode());
		}
		if (dto.getName() != null) {
			entity.setName(dto.getName());
		}
		if (dto.getDescription() != null) {
			entity.setDescription(dto.getDescription());
		}
		entity = personAttributeTypeRepository.save(entity);
		return new PersonAttributeTypeDto(entity);
	}

	@Override
	public Boolean delete(UUID id) {
		if (id != null) {
			PersonAttributeType entity;
			Optional<PersonAttributeType> optional = personAttributeTypeRepository.findById(id);
			if (optional.isPresent()) {
				entity = optional.get();
				personAttributeTypeRepository.delete(entity);
				return true;
			}else {
				return false;
			}

		}
		return false;
	}

	@Override
	public List<PersonAttributeTypeDto> getAllPersonAttributeTypeDtos() {
		return personAttributeTypeRepository.getAll();
	}
	
	@Override
	public List<PersonAttributeType> getAllPersonAttributeType() {
		return personAttributeTypeRepository.getAllEntity();
	}

	@Override
	public PersonAttributeTypeDto deleteVoided(UUID id) {
		PersonAttributeType entity=new PersonAttributeType();
		if (id != null) {
			entity=personAttributeTypeRepository.getOne(id);
		}
		entity.setVoided(false);
		personAttributeTypeRepository.save(entity);
		  return new PersonAttributeTypeDto(entity);
		 
		 
	}

	@Override
	public Boolean checkCode(UUID id, String code) {
		if (StringUtils.hasText(code)) {
            Long count = personAttributeTypeRepository.checkCode(code, id);
            return count != 0L;
        }
		return null;
	}

	@Override
	public PersonAttributeType saveEntity(PersonAttributeType personAT) {
		PersonAttributeType entity = null;
		if (personAT.getId() != null) {
			entity = personAttributeTypeRepository.getById(personAT.getId());
		}
		if (entity == null) {
			entity = new PersonAttributeType();
			entity.setCreateDate(LocalDateTime.now());
			entity.setModifyDate(LocalDateTime.now());
		}
		if (personAT.getCode() != null) {
			entity.setCode(personAT.getCode());
		}
		if (personAT.getName() != null) {
			entity.setName(personAT.getName());
		}
		if (personAT.getDescription() != null) {
			entity.setDescription(personAT.getDescription());
		}
		entity = personAttributeTypeRepository.save(entity);
		return entity;
	}
	@Override
	public PersonAttributeType deleteVoidedEntity(UUID id) {
		PersonAttributeType entity=new PersonAttributeType();
		if (id != null) {
			entity=personAttributeTypeRepository.getOne(id);
		}
		entity.setVoided(false);
		personAttributeTypeRepository.save(entity);
		  return entity;
		 
		 
	}
}
