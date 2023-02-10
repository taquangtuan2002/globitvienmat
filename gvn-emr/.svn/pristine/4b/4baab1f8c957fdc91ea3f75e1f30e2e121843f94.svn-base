package com.globits.emr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.core.domain.BaseObject;
@Entity
@Table(name = "tbl_service_fee")
public class ServiceFee extends BaseObjectMetadata {
	private static final long serialVersionUID = 1L;

	@Column(name="from_date")
	private Date fromDate;
	@Column(name="to_date")
	private Date toDate;
	@Column(name="is_using")
	private Boolean isUsing;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private HealthCareService service;
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Boolean getIsUsing() {
		return isUsing;
	}
	public void setIsUsing(Boolean isUsing) {
		this.isUsing = isUsing;
	}
	public HealthCareService getService() {
		return service;
	}
	public void setService(HealthCareService service) {
		this.service = service;
	}
	
	
}
