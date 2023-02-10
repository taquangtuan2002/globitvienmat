package com.globits.emr.dto;

import java.util.Date;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.ServiceFee;

public class ServiceFeeDto extends BaseObjectDto{
	private String code;
	private String name;
	private String description;
	private Date fromDate;
	private Date toDate;
	private Boolean isUsing;

	public ServiceFeeDto() {

	}

	public ServiceFeeDto(ServiceFee entity) {
		if(entity != null) {
			this.id = entity.getId();
			this.code = entity.getCode();
			this.name = entity.getName();
			this.description = entity.getDescription();
			this.fromDate = entity.getFromDate();
			this.toDate = entity.getToDate();
			this.isUsing = entity.getIsUsing();
		}
	}
	
	public ServiceFeeDto(ServiceFee entity, boolean arc) {
		if(entity != null) {
			this.id = entity.getId();
			this.code = entity.getCode();
			this.name = entity.getName();
			this.description = entity.getDescription();
			this.name = entity.getName();
			this.description = entity.getDescription();
			this.fromDate = entity.getFromDate();
			this.toDate = entity.getToDate();
			this.isUsing = entity.getIsUsing();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
	
	
}
