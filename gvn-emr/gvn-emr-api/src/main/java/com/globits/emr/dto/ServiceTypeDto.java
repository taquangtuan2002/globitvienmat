package com.globits.emr.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.ServiceType;

public class ServiceTypeDto extends BaseObjectDto{
	private String code;
	private String name;
	private String description;
	
	public ServiceTypeDto() {
		
	}
	
	public ServiceTypeDto (ServiceType entity) {
		if(entity != null) {
			this.id = entity.getId();
			this.code = entity.getCode();
			this.name = entity.getName();
			this.description = entity.getDescription();
		}
	}
	
	public ServiceTypeDto(ServiceType entity, boolean arc) {
		if(entity != null) {
			this.id = entity.getId();
			this.code = entity.getCode();
			this.name = entity.getName();
			this.description = entity.getDescription();
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
	
	

}
