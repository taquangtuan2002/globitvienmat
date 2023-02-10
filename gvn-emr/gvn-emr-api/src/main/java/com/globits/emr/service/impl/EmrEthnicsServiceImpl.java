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

import com.globits.core.domain.Ethnics;
import com.globits.core.dto.EthnicsDto;
import com.globits.core.repository.EthnicsRepository;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.EmrEthnicsService;

@Service
public class EmrEthnicsServiceImpl extends GenericServiceImpl<Ethnics, UUID> implements EmrEthnicsService{
	@Autowired
	private EthnicsRepository ethnicsRepos;
	
	@PersistenceContext
    EntityManager manager;
	
	@Override
	public EthnicsDto getDtoById(UUID id) {
		if(id != null) {
			Ethnics entity = null;
	        Optional<Ethnics> ethnicsOptional = ethnicsRepos.findById(id);
	        if (ethnicsOptional.isPresent()) {
	            entity = ethnicsOptional.get();
	            if(entity != null) {
	            	return new EthnicsDto(entity);
	            }
	        }
		}
        return null;
	}

	@Override
	public EthnicsDto saveOrUpdate(UUID id, EthnicsDto dto) {
		if(dto != null) {
			Ethnics entity = null;
			Authentication authencation = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = "Unknown User";
			if(authencation != null) {
				currentUsername = authencation.getName();
			}
			if(dto.getId() != null) {
				Optional<Ethnics> ethnicsOptional = ethnicsRepos.findById(id);
				if(ethnicsOptional.isPresent()) {
					entity = ethnicsOptional.get();
				}
				if(entity != null) {
					entity.setModifyDate(LocalDateTime.now());
					entity.setModifiedBy(currentUsername);
				}
			}
			if(entity == null) {
				entity = new Ethnics();
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
			entity = ethnicsRepos.save(entity);
			return new EthnicsDto(entity);
		}
		return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if(id != null) {
			Ethnics entity = null;
			Optional<Ethnics> ethnicsOptional = ethnicsRepos.findById(id);
			if(ethnicsOptional.isPresent()) {
				entity = ethnicsOptional.get();
			}
			if(entity != null) {
				ethnicsRepos.deleteById(id);
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
			Ethnics entity = null;
			Optional<Ethnics> ethnicsOptional = ethnicsRepos.findById(id);
			if(ethnicsOptional.isPresent()) {
				entity = ethnicsOptional.get();
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
			Ethnics entity = null;
			Optional<Ethnics> ethnicsOptional = ethnicsRepos.findById(id);
			if(ethnicsOptional.isPresent()) {
				entity = ethnicsOptional.get();
			}
			if(entity != null) {
				this.updateVoided(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<EthnicsDto> searchByPage(SearchDto searchDto) {
		if(searchDto != null) {
			int pageIndex = searchDto.getPageIndex();
			int pageSize = searchDto.getPageSize();
			
			if(pageIndex > 0) {
				pageIndex = pageIndex - 1;
			} else {
				pageIndex = 0;
			}
			
			String whereClause = "";
			String sql = "Select new com.globits.core.dto.EthnicsDto(entity) From Ethnics entity Where (1=1) ";
			String sqlCount = "Select count(entity.id) From Ethnics as entity Where (1=1) ";
			
			if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
				whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text)";
			}
			sql += whereClause;
			sqlCount += whereClause;
			
			Query q = manager.createQuery(sql, EthnicsDto.class);
			Query qCount = manager.createQuery(sqlCount);
			
			if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
				q.setParameter("text", '%' + searchDto.getKeyword() + '%');
				qCount.setParameter("text", '%' + searchDto.getKeyword() + '%');
			}
			int startPosition = pageIndex * pageSize;
			q.setFirstResult(startPosition);
			q.setMaxResults(pageSize);
			List<EthnicsDto> results = q.getResultList();
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
