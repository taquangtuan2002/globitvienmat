package com.globits.emr.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.core.domain.BaseObject;
@Entity
@Table(name = "tbl_service_request_set_member")
public class ServiceRequestSetMember extends BaseObject{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="service_id")
	private HealthCareService service;
	
	@ManyToOne
	@JoinColumn(name="set_id")	
	private ServiceRequestSet serviceSet;

	public HealthCareService getService() {
		return service;
	}

	public void setService(HealthCareService service) {
		this.service = service;
	}

	public ServiceRequestSet getServiceSet() {
		return serviceSet;
	}

	public void setServiceSet(ServiceRequestSet serviceSet) {
		this.serviceSet = serviceSet;
	}
	
}
