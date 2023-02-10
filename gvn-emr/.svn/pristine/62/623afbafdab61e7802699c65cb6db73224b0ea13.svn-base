package com.globits.emr.service.impl;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.globits.emr.repository.*;
import com.globits.emr.visit.*;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.domain.Encounter;
import com.globits.emr.domain.EncounterType;
import com.globits.emr.domain.Observation;
import com.globits.emr.domain.Patient;
import com.globits.emr.domain.Visit;
import com.globits.emr.domain.VisitType;
import com.globits.emr.domain.concept.Concept;
import com.globits.emr.domain.concept.ConceptDatatype;
import com.globits.emr.dto.ObservationDto;
import com.globits.emr.dto.VisitDto;
import com.globits.emr.dto.concept.ConceptDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.EncounterService;
import com.globits.emr.service.ObservationService;
import com.globits.emr.service.VisitService;
import com.globits.emr.utils.ConceptUtil;
import com.globits.emr.utils.EncounterTypeUtil;
import com.globits.emr.utils.VisitTypeUtil;

@Service
public class VisitServiceImpl extends GenericServiceImpl<Visit, UUID> implements VisitService {
	@Autowired
	private VisitRepository visitRepos;

	@Autowired
	private PatientRepository patientRepos;

	@Autowired
	private VisitTypeRepository visitTypeRepos;

	@Autowired
	private ConceptRepository conceptRepos;

	@PersistenceContext
	EntityManager manager;
	@Autowired
	private ObservationService observationService;
	@Autowired
	private EncounterService encounterService;

	@Autowired
	private EncounterTypeRepository encounterTypeRepos;

	@Autowired
	private ConceptDatatypeRepository conceptDataTypeRepos;

	@Autowired
	private EncounterRepository encounterRepos;

	@Autowired
	private ObservationRepository observationRepos;

	@Override
	public Visit startVisit(UUID patientId) {
		Date today = new Date();
		// Patient
		Patient patient = null;
		if (patientId != null) {
			Optional<Patient> patientOptional = patientRepos.findById(patientId);
			if (patientOptional.isPresent()) {
				patient = patientOptional.get();
			}
		}
		if (patient == null) {
			return null;
		}
		VisitDto visitDto = new VisitDto();
		visitDto.setPatientId(patientId);
		visitDto.setStartDatetime(today);
		UUID visitTypeByCode = visitTypeRepos.getVisitTypeByCode(VisitTypeUtil.FIRST_EXAMINATION);
		visitDto.setVisitType(visitTypeByCode);
		// VisitType
		VisitType visitType = null;
		if (visitDto.getVisitType() != null) {
			Optional<VisitType> visitTypeOptional = visitTypeRepos.findById(visitDto.getVisitType());
			if (visitTypeOptional.isPresent()) {
				visitType = visitTypeOptional.get();
			}
		}

		Visit visit = new Visit();
		visit.setPatient(patient);
		visit.setVisitType(visitType);
//		visit.setIndication(indication);
		visit.setStartDatetime(today);
		visit = this.saveVisit(visit);
		visitDto = this.getDtoById(visit.getId());
		visitDto.setId(visit.getId());
		return visit;
	}

	public VisitDto getDtoById(UUID id) {
		if (id != null) {
			Visit visit = visitRepos.getEntityById(id);
			return new VisitDto(visit);
		}
		return null;
	}

	@Override
	public IehVisitDto startIehVisit(UUID patientId) {
		IehVisitDto result = new IehVisitDto();
		result.setSymptom(this.getSymptom());
		result.setAnamnesis(this.getAnamnesis());
		result.setVitalSign(this.getVitalSign());
		/// save visit
		Date today = new Date();
		Patient patient = null;
		if (patientId != null) {
			Optional<Patient> patientOptional = patientRepos.findById(patientId);
			if (patientOptional.isPresent()) {
				patient = patientOptional.get();
			}
		}
		VisitType visitTypeByCode = visitTypeRepos.getVisitType(VisitTypeUtil.FIRST_EXAMINATION);
		Visit visit = new Visit();
		if (patient != null) {
			visit.setPatient(patient);
		}
		visit.setVisitType(visitTypeByCode);
		visit.setStartDatetime(today);
		this.saveVisit(visit);
		result.setId(visit.getId());
		result.setStartDatetime(visit.getStartDatetime());
		result.setPatientId(patientId);
		result.setVisitType(visit.getVisitType().getId());
		return result;
	}

	@Override
	public IehVisitDto getIehVisit(UUID visitId) {
		if (visitId != null) {
			Visit visit = this.findById(visitId);
			if (visit == null || (visit.getVoided() != null && visit.getVoided())) {
				return null;
			}
			IehVisitDto result = new IehVisitDto();
			UUID idEncounterType = encounterTypeRepos.getIdEncounterType(EncounterTypeUtil.VISIT);
			List<Encounter> encounters = encounterRepos.getEncounterByVisitId(visitId, idEncounterType);
			Encounter encounter = new Encounter();
			if (encounters != null && encounters.size() > 0) {
				encounter = encounters.get(0);
				result.setStartDatetime(encounter.getEncounterDatetime());
			} else {
				result.setStartDatetime(visit.getStartDatetime());
			}
			result.setId(visitId);
			result.setVisitType(visit.getVisitType().getId());
			result.setPatientId(visit.getPatient().getId());
			if (encounter != null) {
				SymptomDto symptomDto = this.getSymptom();
				AnamnesisDto anamnesisDto = this.getAnamnesis();
				VitalSignsDto vitalSignsDto = this.getVitalSign();
				for (ObservationDto observationDto : observationRepos.getAllByEncounterId(encounter.getId())) {
					if (observationDto.getConceptCode().equals(ConceptUtil.CONCEPT_SYMTOM)) {
						this.setDaTaSymtom(observationDto, symptomDto);
					} else if (observationDto.getConceptCode().equals(ConceptUtil.CONCEPT_ANAMNESIS)) {
						this.setDaTaAnamnesis(observationDto, anamnesisDto);
					} else if (observationDto.getConceptCode().equals(ConceptUtil.CONCEPT_DHST_TL_NA)) {
						this.setDataVitalSign(observationDto, vitalSignsDto);
					}
				}
				symptomDto.setConceptCode(ConceptUtil.CONCEPT_SYMTOM);
				anamnesisDto.setConceptCode(ConceptUtil.CONCEPT_ANAMNESIS);
				vitalSignsDto.setConceptCode(ConceptUtil.CONCEPT_DHST_TL_NA);
				result.setSymptom(symptomDto);
				result.setAnamnesis(anamnesisDto);
				result.setVitalSign(vitalSignsDto);
			}
			return result;
		}
		return null;
	}

	private void setDataVitalSign(ObservationDto observationDto, VitalSignsDto vitalSignsDto) {
		for(ObservationDto obsDto : vitalSignsDto.getItems()) {
			if(obsDto.getValueCoded().equals(observationDto.getValueCoded())) {
				obsDto.setId(observationDto.getId());
				obsDto.setComments(observationDto.getComments());
				obsDto.setValueBoolean(observationDto.getValueBoolean());
				obsDto.setValueDate(observationDto.getValueDate());
				obsDto.setValueNumeric(observationDto.getValueNumeric());
				obsDto.setValueText(observationDto.getValueText());
				obsDto.setChecked(true);
			}
		}
	}

	private void setDaTaAnamnesis(ObservationDto observationDto, AnamnesisDto anamnesisDto) {
		for (ObservationDto obsDto : anamnesisDto.getItems()) {
			if (obsDto.getValueCoded() == observationDto.getValueCoded()) {
				obsDto.setChecked(true);
				obsDto.setComments(observationDto.getComments());
			}
		}
	}

	private void setDaTaSymtom(ObservationDto observationDto, SymptomDto symptomDto) {
		for (ObservationDto obsDto : symptomDto.getItems()) {
			if (obsDto.getValueCoded() == observationDto.getValueCoded()) {
				obsDto.setChecked(true);
				obsDto.setComments(observationDto.getComments());
			}
		}
	}

	private SymptomDto getSymptom() {
		String sql = "Select new com.globits.emr.dto.ObservationDto(entity,'"+ConceptUtil.CONCEPT_SYMTOM+"') "
				+ " From Concept entity Where entity.code Like :code";
		Query q = manager.createQuery(sql, ObservationDto.class);
		q.setParameter("code", ConceptUtil.CONCEPT_SYMTOM + '%');
		List<ObservationDto> results = q.getResultList();
		SymptomDto dto = new SymptomDto();
		dto.setConceptCode(ConceptUtil.CONCEPT_SYMTOM);
		dto.setItems(results);
		return dto;
	}

	private AnamnesisDto getAnamnesis() {
		String sql = "Select new com.globits.emr.dto.ObservationDto(entity,'"+ConceptUtil.CONCEPT_ANAMNESIS+"')"
				+ " From Concept entity Where entity.code Like :code";
		Query q = manager.createQuery(sql, ObservationDto.class);
		q.setParameter("code", ConceptUtil.CONCEPT_ANAMNESIS + '%');
		List<ObservationDto> results = q.getResultList();
		AnamnesisDto dto = new AnamnesisDto();
		dto.setConceptCode(ConceptUtil.CONCEPT_ANAMNESIS);
		dto.setItems(results);
		return dto;
	}

	private VitalSignsDto getVitalSign() {
		String sql = "Select new com.globits.emr.dto.ObservationDto(entity,'"+ConceptUtil.CONCEPT_DHST_TL_NA+"') "
				+ " From Concept entity Where entity.code Like :code";
		Query q = manager.createQuery(sql, ObservationDto.class);
		q.setParameter("code", ConceptUtil.CONCEPT_DHST_TL_NA + '%');
		List<ObservationDto> results = q.getResultList();
		VitalSignsDto dto = new VitalSignsDto();
		dto.setConceptCode(ConceptUtil.CONCEPT_DHST_TL_NA);
		dto.setItems(results);
		return dto;
	}

	@Override
	public List<Visit> getAll() {
		return visitRepos.findAll();
	}

	@Override
	public Visit getById(UUID id) {
		if (id != null) {
			Visit visit = visitRepos.getEntityById(id);
			return visit;
		}
		return null;
	}

	@Override
	public Visit saveVisit(Visit visit) {
		boolean isNewVisit = false;
		if (visit.getId() == null) {
			isNewVisit = true;
		}
		return visitRepos.save(visit);
	}

	@Override
	public Visit saveOrUpdate(VisitDto dto) {
		if (dto != null) {
			Date today = new Date();
			Visit visit = null;
			if (dto.getId() != null) {
				visit = visitRepos.getEntityById(dto.getId());
			}
			if (visit == null) {
				visit = new Visit();
			}
			VisitType visitType = null;
			visit.setStartDatetime(dto.getStartDatetime());
			if (dto.getVisitType() != null) {
				Optional<VisitType> visitTypeOptional = visitTypeRepos.findById(dto.getVisitType());
				if (visitTypeOptional.isPresent()) {
					visitType = visitTypeOptional.get();
					visit.setVisitType(visitType);
				}
			}
			Concept indication = null;
			if (dto.getIndication() != null) {
				Optional<Concept> indicationOptional = conceptRepos.findById(dto.getIndication());
				if (indicationOptional.isPresent()) {
					indication = indicationOptional.get();
					visit.setIndication(indication);
				}
			}
			Patient patient = null;
			if (dto.getPatientId() != null) {
				Optional<Patient> patientOptional = patientRepos.findById(dto.getPatientId());
				if (patientOptional.isPresent()) {
					patient = patientOptional.get();
					visit.setPatient(patient);
				}
			}
			Encounter encounter = null;
			Set<Encounter> encounters = visit.getEncounters();
			if (encounters != null && encounters.size() > 0) {
				for (Encounter e : encounters) {
					if (e.getVoided() != null && e.getVoided()) {
						continue;
					}

					if (e.getEncounterType().getCode() == EncounterTypeUtil.VISIT) {
						encounter = e;

					}
				}
			}
			if (dto.getIsNotEncounterType() == null || !dto.getIsNotEncounterType()) {
				if (encounter == null) {
					encounter = new Encounter();
					encounter.setCreateDate(LocalDateTime.now());
				}
				encounter.setEncounterDatetime(visit.getStartDatetime());
				if (patient != null) {
					encounter.setPatient(patient);
				}
				encounter.setVisit(visit);
				EncounterType encounterType = encounterTypeRepos.getEncounterByCode(EncounterTypeUtil.VISIT);
				encounter.setEncounterType(encounterType);
				encounterService.saveEncounter(encounter);
			}
			visit.setStopDatetime(today);
			visit = this.saveVisit(visit);
			return visit;
		}
		return null;
	}

	@Override
	public Visit saveIehVisit(IehVisitDto dto) {
		if (dto != null) {
			Date today = new Date();
			Visit visit = null;
			if (dto.getId() != null) {
				visit = visitRepos.getEntityById(dto.getId());
			}
			if (visit == null) {
				visit = new Visit();
			}
			VisitType visitType = null;
			visit.setStartDatetime(dto.getStartDatetime());
			if (dto.getVisitType() != null) {
				Optional<VisitType> visitTypeOptional = visitTypeRepos.findById(dto.getVisitType());
				if (visitTypeOptional.isPresent()) {
					visitType = visitTypeOptional.get();
					visit.setVisitType(visitType);
				}
			}
			Concept indication = null;
			if (dto.getIndication() != null) {
				Optional<Concept> indicationOptional = conceptRepos.findById(dto.getIndication());
				if (indicationOptional.isPresent()) {
					indication = indicationOptional.get();
					visit.setIndication(indication);
				}
			}
			Patient patient = null;
			if (dto.getPatientId() != null) {
				Optional<Patient> patientOptional = patientRepos.findById(dto.getPatientId());
				if (patientOptional.isPresent()) {
					patient = patientOptional.get();
					visit.setPatient(patient);
				}
			}
			Encounter encounter = null;
			Set<Encounter> encounters = visit.getEncounters();
			if (encounters != null && encounters.size() > 0) {
				for (Encounter e : encounters) {
					if (e.getVoided() != null && e.getVoided()) {
						continue;
					}
					if (e.getEncounterType().getCode().equals(EncounterTypeUtil.VISIT)) {
						encounter = e;
					}
				}
			}
			if (encounter == null) {
				encounter = new Encounter();
				encounter.setCreateDate(LocalDateTime.now());
			}
			encounter.setEncounterDatetime(visit.getStartDatetime());
			if (patient != null) {
				encounter.setPatient(patient);
			}
			encounter.setVisit(visit);
			EncounterType encounterType = encounterTypeRepos.getEncounterByCode(EncounterTypeUtil.VISIT);
			encounter.setEncounterType(encounterType);
			encounter = encounterService.saveEncounter(encounter);

			this.getDataSymptom(dto, encounter, patient);
			this.getDataAnamnesis(dto, encounter, patient);
			this.getDataVitalSign(dto, encounter, patient);
			visit.setStopDatetime(today);
			visit = this.saveVisit(visit);
			return visit;
		}

		return null;
	}

	private SymptomDto getDataSymptom(IehVisitDto dto, Encounter encounter, Patient patient) {
		// Tuấn
		Observation obs = new Observation();
		if (dto != null && dto.getSymptom() != null && dto.getSymptom().getItems() != null) {
			for (ObservationDto obsDto : dto.getSymptom().getItems()) {
				if (obsDto.getChecked() == null) {
					continue;
				}
				if (obsDto.getChecked() == true) {
					Concept concept = conceptRepos.getByCode(obsDto.getConceptCode());
					if (obsDto.getId() != null) {
						obs = observationService.findById(obsDto.getId());
					}
					if (obs == null) {
						obs = new Observation();
					}

					obs.setConcept(concept);
					obs.setEncounter(encounter);
					obs.setPatient(patient);
					Concept valueCoded = conceptRepos.getByCode(obsDto.getValueCoded());
					obs.setValueCoded(valueCoded);
					if (obsDto.getComments() != null) {
						obs.setComments(obsDto.getComments());
					}

					observationService.saveOrUpdate(obs, obs.getId());
				}
				if (obsDto.getChecked() == false) {
					continue;
				}
			}
		}
		return null;
	}

	private AnamnesisDto getDataAnamnesis(IehVisitDto dto, Encounter encounter, Patient patient) {
		// Hiệu
		if (dto != null && dto.getAnamnesis() != null && dto.getAnamnesis().getItems() != null) {
			for (ObservationDto obsDto : dto.getAnamnesis().getItems()) {
				Observation obs = null;
				if (obsDto.getId() != null) {
					obs = observationService.findById(obsDto.getId());
				}
				if (obs == null) {
					obs = new Observation();
				}
				if (obsDto.getChecked() == null && obsDto.getValueText() == null) {
					continue;
				} else if (obsDto.getChecked() != null && obsDto.getChecked() == true) {
					obs.setEncounter(encounter);
					obs.setPatient(patient);
					Concept concept = conceptRepos.getByCode(ConceptUtil.CONCEPT_ANAMNESIS);
					obs.setConcept(concept);
					Concept valueCoded = conceptRepos.getByCode(obsDto.getValueCoded());
					obs.setValueCoded(valueCoded);
					obs.setComments(obsDto.getComments());
					observationService.saveOrUpdate(obs, null);
				}
				if (obsDto.getChecked() == null && obsDto.getValueText() != null) {
					obs.setEncounter(encounter);
					obs.setPatient(patient);
					Concept concept = conceptRepos.getByCode(ConceptUtil.CONCEPT_ANAMNESIS);
					obs.setConcept(concept);
					obs.setValueText(obsDto.getValueText());
					obs.setValueCoded(null);
					observationService.saveOrUpdate(obs, null);
				}
				if (obsDto.getChecked() == null) {
					continue;
				}
				if (obsDto.getChecked() == false) {
					continue;
				}
			}
		}
		return null;
	}

	private void getDataVitalSign(IehVisitDto dto, Encounter encounter, Patient patient) {
		// Đức
		if (dto != null && dto.getVitalSign() != null && dto.getVitalSign().getItems() != null
				&& dto.getVitalSign().getItems().size() > 0) {
			for (ObservationDto obsDto : dto.getVitalSign().getItems()) {
				if (obsDto.getChecked() == null || obsDto.getChecked() == false) {
					continue;
				} else if (obsDto.getChecked() == true) {
					Observation obs = null;
					if (obsDto.getId() != null) {
						obs = observationService.findById(obsDto.getId());
					}
					if (obs == null) {
						obs = new Observation();
					}
					obs.setEncounter(encounter);
					obs.setPatient(patient);
					Concept concept = conceptRepos.getByCode(ConceptUtil.CONCEPT_DHST_TL_NA);
					obs.setConcept(concept);
					Concept valueCoded = conceptRepos.getByCode(obsDto.getValueCoded());
					obs.setValueCoded(valueCoded);
					if (obsDto.getValueText() != null) {
						obs.setValueText(obsDto.getValueText());
					}
					if (obsDto.getValueNumeric() != null) {
						obs.setValueNumeric(obsDto.getValueNumeric());
					}
					if (obsDto.getValueBoolean() != null) {
						obs.setValueBoolean(obsDto.getValueBoolean());
					}
					if (obsDto.getComments() != null) {
						obs.setComments(obsDto.getComments());
					}
					if (obsDto.getValueDate() != null) {
						obs.setValueDate(obsDto.getValueDate());
					}
					observationService.save(obs);
				}
			}
		}
	}

	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
			Visit entity = null;
			Optional<Visit> visitOptional = visitRepos.findById(id);
			if (visitOptional.isPresent()) {
				entity = visitOptional.get();
				if (entity.getEncounters() != null && entity.getEncounters().size() > 0) {
					return false;
				}
				visitRepos.deleteById(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateVoided(UUID id) {
		if (id != null) {
			Authentication authencation = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = "Unknown User";
			if (authencation != null) {
				currentUsername = authencation.getName();
			}
			Visit entity = null;
			Optional<Visit> visitOptional = visitRepos.findById(id);
			if (visitOptional.isPresent()) {
				entity = visitOptional.get();
			}
			if (entity != null) {
				entity.setModifyDate(LocalDateTime.now());
				entity.setModifiedBy(currentUsername);
				entity.setVoided(true);
			}
		}

	}

	@Override
	public Boolean deleteFakeVoided(UUID id) {
		if (id != null) {
			Visit entity = null;
			Optional<Visit> visitOptional = visitRepos.findById(id);
			if (visitOptional.isPresent()) {
				entity = visitOptional.get();
			}
			if (entity != null) {
				this.updateVoided(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<VisitDto> searchByPage(SearchDto searchDto) {
		if (searchDto == null) {
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
		String sql = "Select new com.globits.emr.dto.VisitDto(entity) From Visit entity Where (1=1) ";
		String sqlCount = "Select count(entity.id) From Visit as entity Where (1=1) ";

		sql += whereClause;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, VisitDto.class);
		Query qCount = manager.createQuery(sqlCount);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<VisitDto> results = q.getResultList();
		if (results == null || results.size() == 0) {
			return null;
		}
		long numberResult = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return new PageImpl<>(results, pageable, numberResult);
	}

}
