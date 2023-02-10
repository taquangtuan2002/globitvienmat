package com.globits.emr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.emr.domain.EncounterType;
import com.globits.emr.domain.PersonAttributeType;
import com.globits.emr.domain.Visit;
import com.globits.emr.domain.VisitType;
import com.globits.emr.dto.PersonAttributeTypeDto;
import com.globits.emr.dto.VisitTypeDto;
import com.globits.emr.repository.VisitTypeRepository;
import com.globits.emr.service.VisitTypeService;

@Service
public class VisitTypeServiceImpl implements VisitTypeService {
	@Autowired
	VisitTypeRepository visitTypeRepository;

	@Override
	public VisitType saveOrUpdate(VisitTypeDto dto) {
		VisitType entity = null;
		if (dto.getId() != null) {
			entity = visitTypeRepository.getById(dto.getId());
		}
		if (entity == null) {
			entity = new VisitType();
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
		entity = visitTypeRepository.save(entity);
		return entity;
	}

	@Override
	public Boolean delete(UUID id) {
		if (id != null) {
			VisitType entity;
			Optional<VisitType> optional = visitTypeRepository.findById(id);
			if (optional.isPresent()) {
				entity = optional.get();
				visitTypeRepository.delete(entity);
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	@Override
	public List<VisitType> getAllVisitTypes() {

		return visitTypeRepository.findAll();
	}

	@Override
	public VisitTypeDto deleteVoided(UUID id) {
		VisitType entity = new VisitType();
		if (id != null) {

			entity = visitTypeRepository.getOne(id);

		}
		entity.setVoided(false);
		visitTypeRepository.save(entity);
		return new VisitTypeDto(entity);

	}

	@Override
	public Boolean checkCode(UUID id, String code) {
		if (StringUtils.hasText(code)) {
            Long count = visitTypeRepository.checkCode(code, id);
            return count != 0L;
        }
		return null;
	}

	

}
