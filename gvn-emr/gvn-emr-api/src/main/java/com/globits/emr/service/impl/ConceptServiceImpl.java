package com.globits.emr.service.impl;

import java.util.HashSet;
import java.util.Iterator;
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
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.domain.concept.Concept;
import com.globits.emr.domain.concept.ConceptAnswer;
import com.globits.emr.domain.concept.ConceptAttribute;
import com.globits.emr.domain.concept.ConceptAttributeType;
import com.globits.emr.domain.concept.ConceptDatatype;
import com.globits.emr.domain.concept.ConceptNumeric;
import com.globits.emr.domain.concept.ConceptType;
import com.globits.emr.dto.concept.ConceptAnswerDto;
import com.globits.emr.dto.concept.ConceptAttributeDto;
import com.globits.emr.dto.concept.ConceptAttributeTypeDto;
import com.globits.emr.dto.concept.ConceptDto;
import com.globits.emr.dto.concept.ConceptNumericsDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.repository.ConceptAnswersRepository;
import com.globits.emr.repository.ConceptAttributeRepository;
import com.globits.emr.repository.ConceptAttributeTypeRepository;
import com.globits.emr.repository.ConceptDatatypeRepository;
import com.globits.emr.repository.ConceptNumericRepository;
import com.globits.emr.repository.ConceptRepository;
import com.globits.emr.repository.ConceptTypeRepository;
import com.globits.emr.service.ConceptService;
import com.globits.emr.utils.ConceptUtil;

@Service
@Transactional
public class ConceptServiceImpl extends GenericServiceImpl<Concept, UUID> implements ConceptService{
	@PersistenceContext
    EntityManager manager;
	@Autowired 
	ConceptRepository conceptRepos;
	@Autowired
	ConceptTypeRepository conceptTypeRepos;
	@Autowired
	ConceptNumericRepository conceptNumericRepos;
	@Autowired
	ConceptAnswersRepository conceptAnswersRepos;
	@Autowired
	ConceptDatatypeRepository conceptDataTypeRepos;
	@Autowired
	ConceptAttributeRepository conceptAttributeRepos;
	@Autowired
	ConceptAttributeTypeRepository conceptAttributeTypeRepos;

	@Override
	public List<Concept> getAll() {
		return conceptRepos.getAllEntity();
	}

	@Override
	public Long countEntity() {
		return conceptRepos.countEntity();
	}

	@Override
	public Concept saveConcept(UUID id, Concept concept) {
		Concept entity = null;
		if(id != null) {
			Optional<Concept> conceptOptional = conceptRepos.findById(id);
			if(conceptOptional.isPresent()) {
				entity = conceptOptional.get();
			}
		}
		if(concept.getId() != null) {
			Optional<Concept> conceptOptional = conceptRepos.findById(concept.getId());
			if(conceptOptional.isPresent()) {
				entity = conceptOptional.get();
			}
		}
		if(entity == null) {
			if(this.checkCode(null, concept.getCode())){
				return null;
			}
			entity = new Concept();
			entity.setCreateDate(LocalDateTime.now());
			entity.setModifyDate(LocalDateTime.now());
		}
		if(concept.getCode() != null && StringUtils.hasText(concept.getCode())) {
			entity.setCode(concept.getCode());
		}
		if(concept.getName() != null) {
			entity.setName(concept.getName());
		}
		if(concept.getDescription() != null) {
			entity.setDescription(concept.getDescription());
		}
		//Concept Type
		if(concept.getConceptType() != null && concept.getConceptType().getId() != null) {
			ConceptType conceptType = null;
			Optional<ConceptType> conceptTypeOptional = conceptTypeRepos.findById(concept.getConceptType().getId());
			if(conceptTypeOptional.isPresent()) {
				conceptType = conceptTypeOptional.get();
				entity.setConceptType(conceptType);
			}
		}
		// Concept data type
		if(concept.getConceptDataType() != null && concept.getConceptDataType().getId() != null) {
			ConceptDatatype conceptDatatype = null;
			Optional<ConceptDatatype> conceptDatatypeOptional = conceptDataTypeRepos.findById(concept.getConceptDataType().getId());
			if(conceptDatatypeOptional.isPresent()) {
				conceptDatatype = conceptDatatypeOptional.get();
				entity.setConceptDataType(conceptDatatype);

				//Concept Answers
				if(conceptDatatype.getCode().equals(ConceptUtil.CONCEPT_DATATYPE_CODED)) {
					if(concept.getConceptAnswers() != null && concept.getConceptAnswers().size() > 0) {
						Iterator<ConceptAnswer> iterConceptAnswers = concept.getConceptAnswers().iterator();
						HashSet<ConceptAnswer> conceptAnswers = new HashSet<ConceptAnswer>();
						while (iterConceptAnswers.hasNext()) {
							ConceptAnswer conceptAnswerDto = iterConceptAnswers.next();
							ConceptAnswer conceptAnswer = null;
							if(conceptAnswerDto.getId() != null) {
								Optional<ConceptAnswer> conceptAnswerOptional = conceptAnswersRepos.findById(conceptAnswerDto.getId());
								if(conceptAnswerOptional.isPresent()) {
									conceptAnswer = conceptAnswerOptional.get();
								}
							}
							if(conceptAnswer == null) {
								conceptAnswer = new ConceptAnswer();
								conceptAnswer.setCreateDate(LocalDateTime.now());
								conceptAnswer.setModifyDate(LocalDateTime.now());
							}
							if(conceptAnswerDto.getViewIndex() != null) {
								conceptAnswer.setViewIndex(conceptAnswerDto.getViewIndex());
							}
							if (conceptAnswerDto.getAnswerConcept() != null && conceptAnswerDto.getAnswerConcept().getId() != null) {
								Concept answerConcept = null;
								Optional<Concept> answerConceptOptional = conceptRepos.findById(conceptAnswerDto.getAnswerConcept().getId());
								if (answerConceptOptional.isPresent()) {
									answerConcept = answerConceptOptional.get();
									conceptAnswer.setAnswerConcept(answerConcept);
								}
							}
							if(entity != null) {
								conceptAnswer.setConcept(entity);
							}
							conceptAnswers.add(conceptAnswer);
						}
						if(entity.getConceptAnswers() != null) {
							entity.getConceptAnswers().clear();
							entity.getConceptAnswers().addAll(conceptAnswers);
						} else {
							entity.setConceptAnswers(conceptAnswers);
						}
					}
				}

				// Concept numeric
				if(conceptDatatype.getCode().equals(ConceptUtil.CONCEPT_DATATYPE_NUMERIC)) {
					if(concept.getConceptNumerics() != null && concept.getConceptNumerics().size() > 0) {
						Iterator<ConceptNumeric> iterConceptNumerics = concept.getConceptNumerics().iterator();
						HashSet<ConceptNumeric> conceptNumerics = new HashSet<ConceptNumeric>();
						while (iterConceptNumerics.hasNext()) {
							ConceptNumeric conceptNumericsDto = iterConceptNumerics.next();
							ConceptNumeric conceptNumeric = null;
							if(conceptNumericsDto.getId() != null) {
								Optional<ConceptNumeric> conceptNumericOptional = conceptNumericRepos.findById(conceptNumericsDto.getId());
								if(conceptNumericOptional.isPresent()) {
									conceptNumeric = conceptNumericOptional.get();
									conceptNumeric.setModifyDate(LocalDateTime.now());
								}
							}
							if(conceptNumeric == null) {
								conceptNumeric = new ConceptNumeric();
								conceptNumeric.setCreateDate(LocalDateTime.now());
								conceptNumeric.setModifyDate(LocalDateTime.now());
							}
							if(conceptNumericsDto.getHiAbsolute() != null) {
								conceptNumeric.setHiAbsolute(conceptNumericsDto.getHiAbsolute());
							}
							if(conceptNumericsDto.getHiCritical() != null) {
								conceptNumeric.setHiCritical(conceptNumericsDto.getHiCritical());
							}
							if(conceptNumericsDto.getHiNormal() != null) {
								conceptNumeric.setHiNormal(conceptNumericsDto.getHiNormal());
							}
							if(conceptNumericsDto.getLowAbsolute() != null) {
								conceptNumeric.setLowAbsolute(conceptNumericsDto.getLowAbsolute());
							}
							if(conceptNumericsDto.getLowCritical() != null) {
								conceptNumeric.setLowCritical(conceptNumericsDto.getLowCritical());
							}
							if(conceptNumericsDto.getLowNormal() != null) {
								conceptNumeric.setLowNormal(conceptNumericsDto.getLowNormal());
							}
							if(conceptNumericsDto.getUnits() != null) {
								conceptNumeric.setUnits(conceptNumericsDto.getUnits());
							}
							if(conceptNumericsDto.getDisplayPrecision() != null) {
								conceptNumeric.setDisplayPrecision(conceptNumericsDto.getDisplayPrecision());
							}
							if(entity != null) {
								conceptNumeric.setConcept(entity);
							}
							conceptNumerics.add(conceptNumeric);
						}
						if(entity.getConceptNumerics() != null) {
							entity.getConceptNumerics().clear();
							entity.getConceptNumerics().addAll(conceptNumerics);
						} else {
							entity.setConceptNumerics(conceptNumerics);
						}
					}
				}
			}
		}
		// Parent
		if(concept.getParent() != null && concept.getParent().getId() != null) {
			Concept parent = null;
			Optional<Concept> parentOptional = conceptRepos.findById(concept.getParent().getId());
			if(parentOptional.isPresent()) {
				parent = parentOptional.get();
				entity.setParent(parent);
			}
		}
		// ConceptAttributes
		if(concept.getAttributes() != null && concept.getAttributes().size() > 0) {
			Iterator<ConceptAttribute> iterConceptAttributes = concept.getAttributes().iterator();
			HashSet<ConceptAttribute> conceptAttributes = new HashSet<ConceptAttribute>();
			while (iterConceptAttributes.hasNext()) {
				ConceptAttribute conceptAttributeDto = iterConceptAttributes.next();
				ConceptAttribute conceptAttribute = null;
				if(conceptAttributeDto.getId() != null) {
					Optional<ConceptAttribute> conceptAttributeOptional = conceptAttributeRepos.findById(conceptAttributeDto.getId());
					if(conceptAttributeOptional.isPresent()) {
						conceptAttribute = conceptAttributeOptional.get();
					}
				}
				if(conceptAttribute == null) {
					conceptAttribute = new ConceptAttribute();
					conceptAttribute.setCreateDate(LocalDateTime.now());
					conceptAttribute.setModifyDate(LocalDateTime.now());
				}
				if(conceptAttributeDto.getValue() != null) {
					conceptAttribute.setValue(conceptAttributeDto.getValue());
				}
				if(conceptAttributeDto.getConceptAttributeType() != null && conceptAttributeDto.getConceptAttributeType().getId() != null) {
					ConceptAttributeType conceptAttributeType = null;
					Optional<ConceptAttributeType> conceptAttributeTypeOptional =
							conceptAttributeTypeRepos.findById(conceptAttributeDto.getConceptAttributeType().getId());
					if(conceptAttributeTypeOptional.isPresent()) {
						conceptAttributeType = conceptAttributeTypeOptional.get();
						conceptAttribute.setConceptAttributeType(conceptAttributeType);
					}
				}
				if(entity != null) {
					conceptAttribute.setConcept(entity);
				}
				conceptAttributes.add(conceptAttribute);
			}
			if(entity.getAttributes() != null) {
				entity.getAttributes().clear();
				entity.getAttributes().addAll(conceptAttributes);
			} else {
				entity.setAttributes(conceptAttributes);
			}
		}
		entity = this.save(entity);
		return entity;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
			Concept entity = null;
			Optional<Concept> conceptOptional = conceptRepos.findById(id);
            if (conceptOptional.isPresent()) {
            	entity = conceptOptional.get();
            }
            if (entity != null) {
            conceptRepos.deleteById(id);
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
			Concept entity = null;
			Optional<Concept> conceptOptional = conceptRepos.findById(id);
			if(conceptOptional.isPresent()) {
				entity = conceptOptional.get();
			}
			if(entity != null) {
				entity.setModifyDate(LocalDateTime.now());
				entity.setModifiedBy(currentUsername);
				entity.setVoided(false);
			}
		}
		
	}
	
	@Override
	public Boolean deleteFakeVoided(UUID id) {
		if(id != null) {
			Concept entity = null;
			Optional<Concept> conceptOptional = conceptRepos.findById(id);
			if(conceptOptional.isPresent()) {
				entity = conceptOptional.get();
			}
			if(entity != null) {
				this.updateVoided(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public Concept getConceptById(UUID id) {
		if(id != null) {
			Optional<Concept> conceptOptional = conceptRepos.findById(id);
			if(conceptOptional.isPresent()) {
				Concept concept = conceptOptional.get();
				return concept;
			}
		}
		return null;
	}

	@Override
	public Concept getConceptByCode(String code) {
		if(code != null) {
			Concept concept = conceptRepos.getByCode(code);
			if(concept != null) {
				return concept;
			}
		}
		return null;
	}

	@Override
	public Page<ConceptDto> searchByPage(SearchDto dto) {
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

        String sqlCount = "select count(entity.id) from Concept as entity where (1=1)   ";
        String sql = "select new com.globits.emr.dto.concept.ConceptDto(entity) from Concept as entity where (1=1)  ";

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
        }

        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, ConceptDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            q.setParameter("text", '%' + dto.getKeyword() + '%');
            qCount.setParameter("text", '%' + dto.getKeyword() + '%');
        }
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<ConceptDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(entities, pageable, count);
	}

	@Override
	public Boolean checkCode(UUID id, String code) {
		if (StringUtils.hasText(code)) {
            Long count = conceptRepos.checkCode(code, id);
            return count != 0L;
        }
        return null;
	}

}
