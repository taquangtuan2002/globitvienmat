package com.globits.emr.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_service_request")
public class ServiceRequest extends BaseObject{

	private static final long serialVersionUID = 1L;
	@ManyToOne(optional = true)
	@JoinColumn(name = "service_id")
	private HealthCareService service;//Which service is used
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "encounter_id")
	private Encounter encounter;

	/*
	 * RequestType : Imaging Diagnostic, Lab Test Order
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "request_type_id")
	private ServiceRequestType requestType;

	
	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public HealthCareService getService() {
		return service;
	}

	public void setService(HealthCareService service) {
		this.service = service;
	}
	
	
}
