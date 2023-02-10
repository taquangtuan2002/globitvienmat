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

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.domain.HealthCareRoom;
import com.globits.emr.dto.HealthCareRoomDto;
import com.globits.emr.dto.ServiceFeeDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.repository.HealthCareRoomRepository;
import com.globits.emr.service.HealthCareRoomService;

@Service
public class HealthCareRoomServiceImpl extends GenericServiceImpl<HealthCareRoom, UUID> implements HealthCareRoomService{
	@Autowired
	private HealthCareRoomRepository healthCareRoomRepos;

	@PersistenceContext
    EntityManager manager;

	
	@Override
	public List<HealthCareRoomDto> getAllDto() {
		return healthCareRoomRepos.getAllDto();
	}

	@Override
	public HealthCareRoomDto getDtoById(UUID id) {
		if(id != null) {
			return healthCareRoomRepos.getDtoById(id);
		}
		return null;
	}

	@Override
	public HealthCareRoomDto saveOrUpdate(UUID id, HealthCareRoomDto dto) {
		if(dto != null) {
			HealthCareRoom entity = null;
			Authentication authencation = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = "Unknown User";
			if(authencation != null) {
				currentUsername = authencation.getName();
			}
			if(dto.getId() != null) {
				Optional<HealthCareRoom> healthCareRoomOptional = healthCareRoomRepos.findById(dto.getId());
				if(healthCareRoomOptional.isPresent()) {
					entity = healthCareRoomOptional.get();
				}
				if(entity != null) {
					entity.setModifyDate(LocalDateTime.now());
					entity.setModifiedBy(currentUsername);
				}
			}
			if(entity == null) {
				entity = new HealthCareRoom();
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
			entity = healthCareRoomRepos.save(entity);
			return new HealthCareRoomDto(entity);
		}
		return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if(id != null) {
			HealthCareRoom entity = null;
			Optional<HealthCareRoom> serviceTypeOptional = healthCareRoomRepos.findById(id);
			if(serviceTypeOptional.isPresent()) {
				entity = serviceTypeOptional.get();
			}
			if(entity != null) {
				healthCareRoomRepos.deleteById(id);
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
			HealthCareRoom entity = null;
			Optional<HealthCareRoom> serviceTypeOptional = healthCareRoomRepos.findById(id);
			if(serviceTypeOptional.isPresent()) {
				entity = serviceTypeOptional.get();
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
			HealthCareRoom entity = null;
			Optional<HealthCareRoom> serviceTypeOptional = healthCareRoomRepos.findById(id);
			if(serviceTypeOptional.isPresent()) {
				entity = serviceTypeOptional.get();
			}
			if(entity != null) {
				this.updateVoided(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<HealthCareRoomDto> searchByPage(SearchDto searchDto) {
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
		String sql = "Select new com.globits.emr.dto.HealthCareRoomDto(entity) From HealthCareRoom entity Where (1=1) ";
		String sqlCount = "Select count(entity.id) From HealthCareRoom as entity Where (1=1) ";
		
		if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
			whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text)";
		}
		sql += whereClause;
		sqlCount += whereClause;
		
		Query q = manager.createQuery(sql, HealthCareRoomDto.class);
		Query qCount = manager.createQuery(sqlCount);
		
		if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
			q.setParameter("text", '%' + searchDto.getKeyword() + '%');
			qCount.setParameter("text", '%' + searchDto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<HealthCareRoomDto> results = q.getResultList();
		if(results == null || results.size() == 0) {
			return null;
		}
		long numberResult = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return new PageImpl<>(results, pageable, numberResult);
	}
}
