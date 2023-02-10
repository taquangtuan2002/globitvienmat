package com.globits.emr.service;

import java.util.List;
import java.util.UUID;

import com.globits.emr.domain.EpisodeOfCare;
import com.globits.emr.dto.EpisodeOfCareDto;

public interface EpisodeOfCareService {
	List<EpisodeOfCare> getAll();
	EpisodeOfCare save(EpisodeOfCareDto dto);
	Boolean delete(UUID id);
	EpisodeOfCare deletevoided(UUID id);
	EpisodeOfCare getById(UUID id);
	EpisodeOfCare saveEntity(EpisodeOfCare eoc);
}
