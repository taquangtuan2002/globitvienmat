package com.globits.emr.dto;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.Visit;

public class VisitDto extends BaseObjectDto {
	private Date startDatetime;
	private Date stopDatetime;
	private UUID patientId;
	private UUID encounterId;
	private UUID visitType;
	private UUID indication;
	private UUID uuid;
//	private SpecialistDto specialist; 
	private Set<EncounterDto> encounters;
	private Boolean isNotEncounterType;
	//
	

	public VisitDto() {

	}

	public VisitDto(Visit entity) {
		if(entity != null) {
			this.id = entity.getId();
			this.startDatetime = entity.getStartDatetime();
			this.stopDatetime = entity.getStopDatetime();
			this.encounterId = entity.getId();
			if(entity.getPatient() != null) {
				this.patientId = entity.getPatient().getId();
			}
			if(entity.getVisitType() != null) {
				this.visitType = entity.getVisitType().getId();
			}
		}
	}
	
	public VisitDto(Visit entity, UUID encouterId) {
		this.id = entity.getId();
		this.patientId = entity.getPatient().getId();
		this.startDatetime = entity.getStartDatetime();
		this.encounterId = encouterId;
	}

	public Date getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Date getStopDatetime() {
		return stopDatetime;
	}

	public void setStopDatetime(Date stopDatetime) {
		this.stopDatetime = stopDatetime;
	}

	public UUID getPatientId() {
		return patientId;
	}

	public void setPatientId(UUID patientId) {
		this.patientId = patientId;
	}

	public UUID getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(UUID encounterId) {
		this.encounterId = encounterId;
	}

	public UUID getVisitType() {
		return visitType;
	}

	public void setVisitType(UUID visitType) {
		this.visitType = visitType;
	}

	public UUID getIndication() {
		return indication;
	}

	public void setIndication(UUID indication) {
		this.indication = indication;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Set<EncounterDto> getEncounters() {
		return encounters;
	}

	public void setEncounters(Set<EncounterDto> encounters) {
		this.encounters = encounters;
	}

	public Boolean getIsNotEncounterType() {
		return isNotEncounterType;
	}

	public void setIsNotEncounterType(Boolean isNotEncounterType) {
		this.isNotEncounterType = isNotEncounterType;
	}

	
	
}
