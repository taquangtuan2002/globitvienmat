package com.globits.emr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.domain.Profession;
import com.globits.core.dto.ProfessionDto;
import com.globits.core.repository.ProfessionRepository;
import com.globits.emr.domain.VisitType;
import com.globits.emr.dto.VisitTypeDto;
import com.globits.emr.service.EmrProfessionService;
@Service
public class EmrProfesssionServiceImpl implements EmrProfessionService {
@Autowired
ProfessionRepository professionRepository;

	@Override
	public ProfessionDto saveOrUpdate(ProfessionDto dto) {
Profession entity=null;
		if (dto.getId() != null) {
			entity = professionRepository.getById(dto.getId());
		}
		if (entity == null) {
			entity = new Profession();
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
		entity = professionRepository.save(entity);
		return new ProfessionDto(entity);
		
	}

	@Override
	public Boolean deleteByid(UUID id) {
		if (id != null) {
			Profession entity;
			Optional<Profession> optional = professionRepository.findById(id);
			if (optional.isPresent()) {
				entity = optional.get();
				professionRepository.delete(entity);
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	@Override
	public ProfessionDto getById(UUID id) {
		return new ProfessionDto(professionRepository.getOne(id));
	}

	@Override
	public ProfessionDto deleteVoided(UUID id) {
		Profession entity = new Profession();
		if (id != null) {

			entity = professionRepository.getOne(id);

		}
		entity.setVoided(false);
professionRepository.save(entity);
		return new ProfessionDto(entity);

		
	}

	

	@Override
	public Profession findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Profession> getList(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profession save(Profession t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profession delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
