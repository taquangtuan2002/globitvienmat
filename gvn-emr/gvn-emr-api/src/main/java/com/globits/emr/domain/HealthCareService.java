package com.globits.emr.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.core.domain.BaseObject;
@Entity
@Table(name = "tbl_health_care_service")
public class HealthCareService extends BaseObject {
	@ManyToOne(optional = true)
	@JoinColumn(name = "service_type_id")
	private ServiceType serviceType;

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	
	
}
