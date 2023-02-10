package com.globits.emr.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.domain.Encounter;
import com.globits.emr.domain.Observation;
import com.globits.emr.domain.Patient;
import com.globits.emr.domain.concept.Concept;
import com.globits.emr.dto.ObservationDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.repository.ConceptRepository;
import com.globits.emr.repository.EncounterRepository;
import com.globits.emr.repository.ObservationRepository;
import com.globits.emr.repository.PatientRepository;
import com.globits.emr.service.ObservationService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ObservationServiceImpl extends GenericServiceImpl<Observation, UUID> implements ObservationService {
	@Autowired
	private ObservationRepository observationRepos;

	@Autowired
	private ConceptRepository conceptRepos;

	@Autowired
	private EncounterRepository encounterRepos;

	@Autowired
	private PatientRepository patientRepos;

	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Observation> getAll() {
		return observationRepos.findAll();
	}

	@Override
	public Observation saveOrUpdate(Observation entity, UUID id) {
		if (entity == null) {
			return null;
		}
		Observation observation = null;
		if (id != null) {
			Optional<Observation> optional = observationRepos.findById(id);
			if (optional.isPresent()) {
				observation = optional.get();
			}

		}
		if (observation == null && entity.getId() != null) {
			Optional<Observation> observationOptional = observationRepos.findById(entity.getId());
			if (observationOptional.isPresent()) {
				observation = observationOptional.get();
				observation.setModifyDate(LocalDateTime.now());
			}
		}
		if (observation == null) {
			observation = new Observation();
			observation.setCreateDate(LocalDateTime.now());
			observation.setModifyDate(LocalDateTime.now());
		}
		// Concept
		if (entity.getConcept().getCode() != null) {
			Concept concept = conceptRepos.getByCode(entity.getConcept().getCode());
			if (concept != null) {
				observation.setConcept(concept);
			}
		}
		// Encounter
		if (entity.getEncounter() != null && entity.getEncounter().getId() != null) {
			Encounter encounter = null;
			Optional<Encounter> encounterOptional = encounterRepos.findById(entity.getEncounter().getId());
			if (encounterOptional.isPresent()) {
				encounter = encounterOptional.get();
				observation.setEncounter(encounter);
			}
		}
		// Patient
		if (entity.getPatient() != null && entity.getPatient().getId() != null) {
			Patient patient = null;
			Optional<Patient> patientOptional = patientRepos.findById(entity.getPatient().getId());
			if (patientOptional.isPresent()) {
				patient = patientOptional.get();
				observation.setPatient(patient);
			}
		}
		if (entity.getValueNumeric() != null) {
			observation.setValueNumeric(entity.getValueNumeric());
		}
		if (entity.getValueText() != null) {
			observation.setValueText(entity.getValueText());
		}
		if (entity.getValueDate() != null) {
			observation.setValueDate(entity.getValueDate());
		}
		if (entity.getValueBoolean() != null) {
			observation.setValueBoolean(entity.getValueBoolean());
		}
		// Value coded
		if (entity.getValueCoded()!= null) {
			if (entity.getValueCoded().getCode() != null) {
				Concept valueCoded = conceptRepos.getByCode(entity.getValueCoded().getCode());
				if (valueCoded != null) {
					observation.setValueCoded(valueCoded);
				}
			}
		}
		if (entity.getComments() != null) {
			observation.setComments(entity.getComments());
		}
		observation = observationRepos.save(observation);
		return observation;

	}

	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
			Observation observation = null;
			Optional<Observation> observationOptional = observationRepos.findById(id);
			if (observationOptional.isPresent()) {
				observationRepos.deleteById(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public Observation getDtoById(UUID id) {
		if (id != null) {
			return observationRepos.getOne(id);
		}
		return null;
	}

	@Override
	public void updateVoided(UUID id) {
		if (id != null) {
			Authentication authencation = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = "Unknown User";
			if (authencation != null) {
				currentUsername = authencation.getName();
			}
			Observation observation = null;
			Optional<Observation> observationOptional = observationRepos.findById(id);
			if (observationOptional.isPresent()) {
				observation = observationOptional.get();
				observation.setModifyDate(LocalDateTime.now());
				observation.setModifiedBy(currentUsername);
				observation.setVoided(true);
			}
		}
	}

	@Override
	public Boolean deleteFakeVoided(UUID id) {
		if (id != null) {
			Observation observation = null;
			Optional<Observation> observationOptional = observationRepos.findById(id);
			if (observationOptional.isPresent()) {
				this.updateVoided(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<ObservationDto> searchByPage(SearchDto searchDto) {
		if (searchDto != null) {
			int pageIndex = searchDto.getPageIndex();
			int pageSize = searchDto.getPageSize();
			if (pageIndex > 0) {
				pageIndex = pageIndex - 1;
			} else {
				pageIndex = 0;
			}
			String whereClause = "";
			String sql = "Select new com.globits.emr.dto.ObservationDto(entity) From Observation entity Where (1=1) ";
			String sqlCount = "Select count(entity.id) From HealthCareRoom as entity Where (1=1) ";
			String orderBy = "Order by entity.dateCreate DESC";

			sql += whereClause;
			sqlCount += whereClause;
			// Where clause
			Query q = manager.createQuery(sql, ObservationDto.class);
			Query qCount = manager.createQuery(sqlCount);

			int startPosition = pageIndex * pageSize;
			q.setFirstResult(startPosition);
			q.setMaxResults(pageSize);
			List<ObservationDto> results = q.getResultList();
			if (results == null || results.size() == 0) {
				return null;
			}
			long numberResult = (long) qCount.getSingleResult();
			Pageable pageable = PageRequest.of(pageIndex, pageSize);
			return new PageImpl<>(results, pageable, numberResult);
		}
		return null;
	}
}
