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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.domain.concept.ConceptAttributeType;
import com.globits.emr.domain.concept.ConceptDatatype;
import com.globits.emr.dto.concept.ConceptAttributeTypeDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.repository.ConceptAttributeTypeRepository;
import com.globits.emr.service.ConceptAttributeTypeService;

@Service
@Transactional
public class ConceptAttributeTypeServiceImpl extends GenericServiceImpl<ConceptAttributeType, UUID> implements ConceptAttributeTypeService{
	@PersistenceContext
    EntityManager manager;
	@Autowired 
	ConceptAttributeTypeRepository repos;

	@Override
	public ConceptAttributeTypeDto saveOrUpdate(UUID id, ConceptAttributeTypeDto dto) {
		if (dto != null && dto.getCode() != null && StringUtils.hasText(dto.getCode())) {
			ConceptAttributeType entity = null;
            if (dto.getId() != null) {
                if (dto.getId() != null && !dto.getId().equals(id)) {
                    return null;
                }
                Optional<ConceptAttributeType> ConceptAttributeTypeOptional = repos.findById(id);
                if (ConceptAttributeTypeOptional.isPresent()) {
                    entity = ConceptAttributeTypeOptional.get();
                }
                if (entity != null)
                    entity.setModifyDate(LocalDateTime.now());
            }
            if (entity == null) {
                entity = new ConceptAttributeType();
                entity.setCreateDate(LocalDateTime.now());
                entity.setModifyDate(LocalDateTime.now());
            }
            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity = repos.save(entity);
            return new ConceptAttributeTypeDto(entity);
        }
        return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
			ConceptAttributeType entity = null;
			Optional<ConceptAttributeType> conceptATOptional = repos.findById(id);
            if (conceptATOptional.isPresent()) {
            	entity = conceptATOptional.get();
            }
            if (entity != null) {
            repos.deleteById(id);
            return true;}
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
			ConceptAttributeType entity = null;
			Optional<ConceptAttributeType> conceptAttributeTypeOptional = repos.findById(id);
			if(conceptAttributeTypeOptional.isPresent()) {
				entity = conceptAttributeTypeOptional.get();
			}
			if(entity != null) {
				entity.setModifyDate(LocalDateTime.now());
				entity.setModifiedBy(currentUsername);
			}
			entity.setVoided(false);
		}
	}
	
	@Override
	public Boolean deleteFakeVoided(UUID id) {
		if(id != null) {
			ConceptAttributeType entity = null;
			Optional<ConceptAttributeType> conceptAttributeTypeOptional = repos.findById(id);
			if(conceptAttributeTypeOptional.isPresent()) {
				entity = conceptAttributeTypeOptional.get();
			}
			if(entity != null) {
				this.updateVoided(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public ConceptAttributeTypeDto getConceptAttributeType(UUID id) {
		ConceptAttributeType entity = null;
        Optional<ConceptAttributeType> ConceptAttributeTypeOptional = repos.findById(id);
        if (ConceptAttributeTypeOptional.isPresent()) {
            entity = ConceptAttributeTypeOptional.get();
        }
        if (entity != null) {
            return new ConceptAttributeTypeDto(entity);
        }
        return null;
	}

	@Override
	public Page<ConceptAttributeTypeDto> searchByPage(SearchDto dto) {
		if (dto == null) {
            return null;
        }

        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }

        String whereClause = "";

        String orderBy = " ORDER BY entity.createDate DESC";

        String sqlCount = "select count(entity.id) from ConceptAttributeType as entity where (1=1)   ";
        String sql = "select new com.globits.emr.dto.concept.ConceptAttributeTypeDto(entity) from ConceptAttributeType as entity where (1=1)  ";

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
        }

        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, ConceptAttributeTypeDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            q.setParameter("text", '%' + dto.getKeyword() + '%');
            qCount.setParameter("text", '%' + dto.getKeyword() + '%');
        }
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<ConceptAttributeTypeDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(entities, pageable, count);
	}

	@Override
	public Boolean checkCode(UUID id, String code) {
		if (StringUtils.hasText(code)) {
            Long count = repos.checkCode(code, id);
            return count != 0L;
        }
        return null;
	}

}
