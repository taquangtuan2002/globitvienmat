package com.globits.emr.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.HealthCareService;

public class HealthCareServiceDto extends BaseObjectDto {
	private ServiceTypeDto serviceType;

	public HealthCareServiceDto() {

	}

	public HealthCareServiceDto(HealthCareService entity) {
		if(entity != null) {
			this.id = entity.getId();
			if(entity.getServiceType() != null) {
				this.serviceType = new ServiceTypeDto(entity.getServiceType(), true);
			}
		}
	}
	
	public HealthCareServiceDto(HealthCareService entity, boolean arc) {
		if(entity != null) {
			this.id = entity.getId();
			if(entity.getServiceType() != null) {
				this.serviceType = new ServiceTypeDto(entity.getServiceType(), true);
			}
		}
	}

	public ServiceTypeDto getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceTypeDto serviceType) {
		this.serviceType = serviceType;
	}

	
	
	
}
