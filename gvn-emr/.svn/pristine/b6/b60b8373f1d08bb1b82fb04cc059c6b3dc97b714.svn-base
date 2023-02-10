package com.globits.emr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Query;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.domain.Encounter;
import com.globits.emr.domain.Patient;
import com.globits.emr.dto.EncounterDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.repository.EncounterRepository;
import com.globits.emr.repository.EncounterTypeRepository;
import com.globits.emr.repository.PatientRepository;
import com.globits.emr.repository.VisitRepository;
import com.globits.emr.service.EncounterService;

@Service
public class EncounterServiceImpl extends GenericServiceImpl<Encounter, UUID> implements EncounterService{

	@Autowired
	private EncounterRepository encounterRepos;
	
	@Override
	public List<Encounter> getAll() {
		return encounterRepos.findAll();
	}

	@Override
	public Encounter getDtoById(UUID id) {
		if(id != null) {
			Encounter entity = null;
			Optional<Encounter> encounterOptional = encounterRepos.findById(id);
			if(encounterOptional.isPresent()) {
				entity = encounterOptional.get();
				if(entity != null) {
					return entity;
				}
			}
		}
		return null;
	}
	
	
	@Override
	public Encounter saveEncounter(Encounter encounter) {
		boolean isNewEncounter = true;
		if(encounter.getId() != null) {
			isNewEncounter = false;
		}
		encounter = encounterRepos.save(encounter);
		return encounter;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if(id != null) {
			Encounter entity = null;
			Optional<Encounter> encounterOptional = encounterRepos.findById(id);
			if(encounterOptional.isPresent()) {
				entity = encounterOptional.get();
			}
			if(entity != null) {
				encounterRepos.deleteById(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateVoided(UUID id) {
		if(id != null) {
			Authentication authencation = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = "Unknown User";
			if(authencation != null) {
				currentUsername = authencation.getName();
			}
			Encounter entity = null;
			Optional<Encounter> encounterOptional = encounterRepos.findById(id);
			if(encounterOptional.isPresent()) {
				entity = encounterOptional.get();
			}
			if(entity != null) {
				entity.setModifyDate(LocalDateTime.now());
				entity.setModifiedBy(currentUsername);
				entity.setVoided(true);
			}
		}
		
	}

	@Override
	public Boolean deleteFakeVoided(UUID id) {
		if(id != null) {
			Encounter entity = null;
			Optional<Encounter> encounterOptional = encounterRepos.findById(id);
			if(encounterOptional.isPresent()) {
				entity = encounterOptional.get();
			}
			if(entity != null) {
				this.updateVoided(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<EncounterDto> searchByPage(SearchDto searchDto) {
		if(searchDto == null) {
			return null;
		}
		
		int pageIndex = searchDto.getPageIndex();
		int pageSize = searchDto.getPageSize();
		if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }
		
		String whereClause = "";
		String sql = "Select new com.globits.emr.dto.EncounterDto(entity) From Encounter entity Where (1=1) ";
		String sqlCount = "Select count(entity.id) From Encounter as entity Where (1=1) ";
		
		if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
			whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text)";
		}
		sql += whereClause;
		sqlCount += whereClause;
		
		Query q = manager.createQuery(sql, EncounterDto.class);
		Query qCount = manager.createQuery(sqlCount);
		
		if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
			q.setParameter("text", '%' + searchDto.getKeyword() + '%');
			qCount.setParameter("text", '%' + searchDto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<EncounterDto> results = q.getResultList();
		if(results == null || results.size() == 0) {
			return null;
		}
		long numberResult = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return new PageImpl<>(results, pageable, numberResult);
	}
}
