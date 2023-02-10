package com.globits.emr.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.EncounterType;

public class EncounterTypeDto extends BaseObjectDto {
	private String name;
	private String code;
	private String description;

	public EncounterTypeDto() {
	}

	public EncounterTypeDto(EncounterType entity) {
		this.code = entity.getCode();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.createDate = entity.getCreateDate();
		this.modifyDate = entity.getModifyDate();
		this.id = entity.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
