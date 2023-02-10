package com.globits.emr.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globits.emr.domain.EpisodeOfCare;
import com.globits.emr.domain.PersonAttributeType;
import com.globits.emr.dto.EpisodeOfCareDto;
import com.globits.emr.dto.PersonAttributeTypeDto;
import com.globits.emr.repository.EpisodeOfCareRepository;
import com.globits.emr.service.EpisodeOfCareService;

@Service
public class EpisodeOfCareServiceImpl implements EpisodeOfCareService {
	@Autowired
	EpisodeOfCareRepository episodeOfCareRepository;

	@Override
	public EpisodeOfCare save(EpisodeOfCareDto dto) {
		EpisodeOfCare entity = null;

		if (dto.getId() != null) {
			entity = episodeOfCareRepository.getOne(dto.getId());
		}
		if (entity == null) {
			entity = new EpisodeOfCare();
			entity.setCreateDate(LocalDateTime.now());
			entity.setModifyDate(LocalDateTime.now());
		}

		if (dto.getStartDatetime() != null) {

			entity.setStartDatetime(dto.getStartDatetime());

		}
		if (dto.getStopDatetime() != null) {

			entity.setStopDatetime(dto.getStopDatetime());

		}
		episodeOfCareRepository.save(entity);
		return entity;
	}
	@Override
	public EpisodeOfCare saveEntity(EpisodeOfCare eoc) {
		EpisodeOfCare entity = null;

		if (eoc!= null) {
			if (eoc.getId() != null) {
				entity = episodeOfCareRepository.getOne(eoc.getId());
			}
		}
		if (entity == null) {
			entity = new EpisodeOfCare();
			entity.setCreateDate(LocalDateTime.now());
			entity.setModifyDate(LocalDateTime.now());
		}

		if (eoc.getStartDatetime() != null) {
			entity.setStartDatetime(eoc.getStartDatetime());
		}
		if (eoc.getStopDatetime() != null) {
			entity.setStopDatetime(eoc.getStopDatetime());
		}
		episodeOfCareRepository.save(entity);
		return entity;
	}

	@Override
	public Boolean delete(UUID id) {
		if (id != null) {
			EpisodeOfCare entity;
			Optional<EpisodeOfCare> optional = episodeOfCareRepository.findById(id);
			if (optional.isPresent()) {
				entity = optional.get();
				episodeOfCareRepository.delete(entity);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public EpisodeOfCare deletevoided(UUID id) {
		EpisodeOfCare entity = new EpisodeOfCare();
		if (id != null) {
			entity = episodeOfCareRepository.getOne(id);
		}
		entity.setVoided(false);
		episodeOfCareRepository.save(entity);
		return entity;
	}

	@Override
	public EpisodeOfCare getById(UUID id) {

		return episodeOfCareRepository.getOne(id);
	}

	@Override
	public List<EpisodeOfCare> getAll() {
		return episodeOfCareRepository.getAll();
	}

}