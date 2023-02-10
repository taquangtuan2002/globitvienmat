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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.domain.concept.ConceptAttribute;
import com.globits.emr.dto.concept.ConceptAttributeDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.repository.ConceptAttributeRepository;
import com.globits.emr.service.ConceptAttributeService;

@Service
@Transactional
public class ConceptAttributeServiceImpl extends GenericServiceImpl<ConceptAttribute, UUID> implements ConceptAttributeService{
	@PersistenceContext
    EntityManager manager;
	@Autowired 
	ConceptAttributeRepository repos;

	@Override
	public ConceptAttributeDto saveOrUpdate(UUID id, ConceptAttributeDto dto) {
		if (dto != null ) {
			ConceptAttribute entity = null;
            if (dto.getId() != null) {
                if (dto.getId() != null && !dto.getId().equals(id)) {
                    return null;
                }
                Optional<ConceptAttribute> ConceptAttributeOptional = repos.findById(id);
                if (ConceptAttributeOptional.isPresent()) {
                    entity = ConceptAttributeOptional.get();
                }
                if (entity != null)
                    entity.setModifyDate(LocalDateTime.now());
            }
            if (entity == null) {
                entity = new ConceptAttribute();
                entity.setCreateDate(LocalDateTime.now());
                entity.setModifyDate(LocalDateTime.now());
            }
            entity.setValue(dto.getValue());
            
            entity = repos.save(entity);
            return new ConceptAttributeDto(entity);
        }
        return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
            repos.deleteById(id);
            return true;
        }
        return false;
	}

	@Override
	public ConceptAttributeDto getConceptAttribute(UUID id) {
		ConceptAttribute entity = null;
        Optional<ConceptAttribute> ConceptAttributeOptional = repos.findById(id);
        if (ConceptAttributeOptional.isPresent()) {
            entity = ConceptAttributeOptional.get();
        }
        if (entity != null) {
            return new ConceptAttributeDto(entity);
        }
        return null;
	}

	@Override
	public Page<ConceptAttributeDto> searchByPage(SearchDto dto) {
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

        String sqlCount = "select count(entity.id) from ConceptAttribute as entity where (1=1)   ";
        String sql = "select new com.globits.emr.dto.concept.ConceptAttributeDto(entity) from ConceptAttribute as entity where (1=1)  ";

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
        }

        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, ConceptAttributeDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            q.setParameter("text", '%' + dto.getKeyword() + '%');
            qCount.setParameter("text", '%' + dto.getKeyword() + '%');
        }
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<ConceptAttributeDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(entities, pageable, count);
	}


}
