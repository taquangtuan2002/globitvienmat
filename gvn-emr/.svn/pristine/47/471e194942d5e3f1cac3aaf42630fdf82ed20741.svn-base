package com.globits.emr.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.globits.core.domain.BaseObject;
/*
 * Represent the meeting or interaction of the patient with health-care worker 
 * One encounter may have many orders or obs
 */
@Entity
@Table(name = "tbl_encounter")
public class Encounter extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "encounter_datetime", nullable = false, length = 19)
	private Date encounterDatetime;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "encounter_type_id")
	private EncounterType encounterType;
	
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "episode_of_care_id")
	private EpisodeOfCare episodeOfCare;
	
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "visit_id")
	private Visit visit;
	
	@OneToMany(mappedBy = "encounter")
	private Set<Diagnosis> diagnoses;//Diagnosis for the Patient recorded by health worker
	
	@OneToMany(mappedBy = "encounter")
	private Set<Condition> conditions;//Condition of the patient when interact with health worker
	
	@OneToMany(mappedBy = "encounter")
	private Set<ServiceRequest> serviceRequest;//Request for Lab test or image analysation
	
	public Date getEncounterDatetime() {
		return encounterDatetime;
	}

	public void setEncounterDatetime(Date encounterDatetime) {
		this.encounterDatetime = encounterDatetime;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public EpisodeOfCare getEpisodeOfCare() {
		return episodeOfCare;
	}

	public void setEpisodeOfCare(EpisodeOfCare episodeOfCare) {
		this.episodeOfCare = episodeOfCare;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Set<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(Set<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public Set<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}

	public EncounterType getEncounterType() {
		return encounterType;
	}

	public void setEncounterType(EncounterType encounterType) {
		this.encounterType = encounterType;
	}

	public Set<ServiceRequest> getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(Set<ServiceRequest> serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	
	
}
