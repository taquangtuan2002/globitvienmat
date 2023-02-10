package com.globits.emr.service;

import com.globits.core.service.GenericService;
import com.globits.emr.domain.Observation;
import com.globits.emr.dto.ObservationDto;
import com.globits.emr.dto.search.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ObservationService extends GenericService<Observation, UUID> {
    List<Observation> getAll();

    Observation saveOrUpdate(Observation entity,UUID id);

    Boolean deleteById(UUID id);

    Observation getDtoById(UUID id);

    void updateVoided(UUID id);

    Boolean deleteFakeVoided(UUID id);

    Page<ObservationDto> searchByPage(SearchDto searchDto);
}