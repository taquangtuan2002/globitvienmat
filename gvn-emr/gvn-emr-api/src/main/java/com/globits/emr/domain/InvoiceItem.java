package com.globits.emr.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_invoice_item")
public class InvoiceItem extends BaseObject {
	@ManyToOne
	@JoinColumn(name="service_request_id")
	private ServiceRequest serviceRequest;

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	} 
	
	
}
