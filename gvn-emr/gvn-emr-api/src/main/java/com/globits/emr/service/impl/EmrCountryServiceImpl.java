package com.globits.emr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import com.globits.core.domain.Country;
import com.globits.core.dto.CountryDto;
import com.globits.core.repository.CountryRepository;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.EmrCountryService;

@Service
public class EmrCountryServiceImpl extends GenericServiceImpl<Country, UUID> implements EmrCountryService{

	@Autowired
	private CountryRepository countryRepos;
	
	@PersistenceContext
    EntityManager manager;

	@Override
	public CountryDto getDtoById(UUID id) {
		if(id != null) {
			Country entity = null;
			Optional<Country> countryOptional = countryRepos.findById(id);
			if(countryOptional.isPresent()) {
				entity = countryOptional.get();
				if(entity != null) {
					return new CountryDto(entity);
				}
			}
		}
		return null;
	}

	@Override
	public CountryDto saveOrUpdate(UUID id, CountryDto dto) {
		if(dto != null) {
			Country entity = null;
			Authentication authencation = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = "Unknown User";
			if(authencation != null) {
				currentUsername = authencation.getName();
			}
			if(dto.getId() != null) {
				Optional<Country> countryOptional = countryRepos.findById(dto.getId());
				if(countryOptional.isPresent()) {
					entity = countryOptional.get();
				}
				if(entity != null) {
					entity.setModifyDate(LocalDateTime.now());
					entity.setModifiedBy(currentUsername);
				}
			}
			if(entity == null) {
				entity = new Country();
				entity.setCreateDate(LocalDateTime.now());
				entity.setModifyDate(LocalDateTime.now());
				entity.setModifiedBy(currentUsername);
			}
			if(dto.getCode() != null && StringUtils.hasText(dto.getCode())) {
				entity.setCode(dto.getCode());
			}
			if(dto.getName() != null) {
				entity.setName(dto.getName());
			}
			if(dto.getDescription() != null) {
				entity.setDescription(dto.getDescription());
			}
			entity = countryRepos.save(entity);
			return new CountryDto(entity);
		}
		return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if(id != null) {
			Country entity = null;
			Optional<Country> countryOptional = countryRepos.findById(id);
			if(countryOptional.isPresent()) {
				entity = countryOptional.get();
			}
			if(entity != null) {
				countryRepos.deleteById(id);
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
			Country entity = null;
			Optional<Country> countryOptional = countryRepos.findById(id);
			if(countryOptional.isPresent()) {
				entity = countryOptional.get();
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
			Country entity = null;
			Optional<Country> countryOptional = countryRepos.findById(id);
			if(countryOptional.isPresent()) {
				entity = countryOptional.get();
			}
			if(entity != null) {
				this.updateVoided(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<CountryDto> searchByPage(SearchDto searchDto) {
		if(searchDto != null) {
			int pageIndex = searchDto.getPageIndex();
			int pageSize = searchDto.getPageSize();
			
			if(pageIndex > 0) {
				pageIndex = pageIndex - 1;
			} else {
				pageIndex = 0;
			}
			
			String whereClause = "";
			String sql = "Select new com.globits.core.dto.CountryDto(entity) From Country entity Where (1=1) ";
			String sqlCount = "Select count(entity.id) From Country as entity Where (1=1) ";
			
			if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
				whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text)";
			}
			sql += whereClause;
			sqlCount += whereClause;
			
			Query q = manager.createQuery(sql, CountryDto.class);
			Query qCount = manager.createQuery(sqlCount);
			
			if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
				q.setParameter("text", '%' + searchDto.getKeyword() + '%');
				qCount.setParameter("text", '%' + searchDto.getKeyword() + '%');
			}
			int startPosition = pageIndex * pageSize;
			q.setFirstResult(startPosition);
			q.setMaxResults(pageSize);
			List<CountryDto> results = q.getResultList();
			if(results == null || results.size() == 0) {
				return null;
			}
			long numberResult = (long) qCount.getSingleResult();
			Pageable pageable = PageRequest.of(pageIndex, pageSize);
			return new PageImpl<>(results, pageable, numberResult);
		}
		return null;
	}
	
	
	
}
