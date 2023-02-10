package com.globits.emr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
import com.globits.emr.domain.ServiceFee;
import com.globits.emr.dto.ServiceFeeDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.repository.ServiceFeeRepository;
import com.globits.emr.service.ServiceFeeService;

@Service
@Transactional
public class ServiceFeeServiceImpl extends GenericServiceImpl<ServiceFee, UUID> implements ServiceFeeService {
	
	@Autowired
	private ServiceFeeRepository serviceFeeRepos;
	
	@PersistenceContext
    EntityManager manager;

	@Override
	public List<ServiceFee> getAllDto() {
		return serviceFeeRepos.findAll();
	}

	@Override
	public ServiceFee getDtoById(UUID id) {
		if(id != null) {
			return serviceFeeRepos.getById(id);
		}
		return null;
	}

	@Override
	public ServiceFee saveOrUpdate(UUID id, ServiceFee serviceFee) {
		if(serviceFee != null) {
			ServiceFee entity = null;
			Authentication authencation = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = "Unknown User";
			if(authencation != null) {
				currentUsername = authencation.getName();
			}
			if(serviceFee.getId() != null) {
				Optional<ServiceFee> serviceFeeOptional = serviceFeeRepos.findById(serviceFee.getId());
				if(serviceFeeOptional.isPresent()) {
					entity = serviceFeeOptional.get();
				}
				if(entity != null) {
					entity.setModifyDate(LocalDateTime.now());
					entity.setModifiedBy(currentUsername);
				}
			}
			if(entity == null) {
				entity = new ServiceFee();
				entity.setCreateDate(LocalDateTime.now());
				entity.setModifyDate(LocalDateTime.now());
				entity.setModifiedBy(currentUsername);
			}
			if(serviceFee.getCode() != null && StringUtils.hasText(serviceFee.getCode())) {
				entity.setCode(serviceFee.getCode());
			}
			if(serviceFee.getName() != null) {
				entity.setName(serviceFee.getName());
			}
			if(serviceFee.getDescription() != null) {
				entity.setDescription(serviceFee.getDescription());
			}
			if(serviceFee.getFromDate() != null) {
				entity.setFromDate(serviceFee.getFromDate());
			}
			if(serviceFee.getToDate() != null) {
				entity.setToDate(serviceFee.getToDate());
			}
			if(serviceFee.getIsUsing() != null) {
				entity.setIsUsing(serviceFee.getIsUsing());
			}
			entity = serviceFeeRepos.save(entity);
			return  entity;
		}
		return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if(id != null) {
			ServiceFee entity = null;
			Optional<ServiceFee> serviceFeeOptional = serviceFeeRepos.findById(id);
			if(serviceFeeOptional.isPresent()) {
				entity = serviceFeeOptional.get();
			}
			if(entity != null) {
				serviceFeeRepos.deleteById(id);
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
			ServiceFee entity = null;
			Optional<ServiceFee> serviceTypeOptional = serviceFeeRepos.findById(id);
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
			ServiceFee entity = null;
			Optional<ServiceFee> serviceTypeOptional = serviceFeeRepos.findById(id);
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
	public Page<ServiceFeeDto> searchByPage(SearchDto searchDto) {
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
		String sql = "Select new com.globits.emr.dto.ServiceFeeDto(entity) From ServiceFee entity Where (1=1) ";
		String sqlCount = "Select count(entity.id) From ServiceFee as entity Where (1=1) ";
		
		if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
			whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text)";
		}
		sql += whereClause;
		sqlCount += whereClause;
		
		Query q = manager.createQuery(sql, ServiceFeeDto.class);
		Query qCount = manager.createQuery(sqlCount);
		
		if(searchDto.getKeyword() != null && StringUtils.hasText(searchDto.getKeyword())) {
			q.setParameter("text", '%' + searchDto.getKeyword() + '%');
			qCount.setParameter("text", '%' + searchDto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<ServiceFeeDto> results = q.getResultList();
		if(results == null || results.size() == 0) {
			return null;
		}
		long numberResult = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return new PageImpl<>(results, pageable, numberResult);
	}
}
