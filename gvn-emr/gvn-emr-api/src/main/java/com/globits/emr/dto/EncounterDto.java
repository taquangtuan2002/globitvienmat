package com.globits.emr.dto;

import java.util.Date;
import java.util.UUID;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.Encounter;

public class EncounterDto extends BaseObjectDto {
	private Date encounterDatetime;
	private UUID patient;
	private UUID encounterType;
	private UUID visit;

	public EncounterDto() {

	}

	public EncounterDto(Encounter entity) {
		if(entity != null) {
			this.id = entity.getId();
			this.encounterDatetime = entity.getEncounterDatetime();
			if(entity.getPatient() != null) {
				this.patient = entity.getPatient().getId();
			}
			if(entity.getEncounterType() != null) {
				this.encounterType = entity.getEncounterType().getId();
			}
			if(entity.getVisit() != null) {
				this.visit = entity.getVisit().getId();
			}
		}
	}
	
	

	public Date getEncounterDatetime() {
		return encounterDatetime;
	}

	public void setEncounterDatetime(Date encounterDatetime) {
		this.encounterDatetime = encounterDatetime;
	}

	public UUID getPatient() {
		return patient;
	}

	public void setPatient(UUID patient) {
		this.patient = patient;
	}

	public UUID getEncounterType() {
		return encounterType;
	}

	public void setEncounterType(UUID encounterType) {
		this.encounterType = encounterType;
	}

	public UUID getVisit() {
		return visit;
	}

	public void setVisit(UUID visit) {
		this.visit = visit;
	}
	
}
