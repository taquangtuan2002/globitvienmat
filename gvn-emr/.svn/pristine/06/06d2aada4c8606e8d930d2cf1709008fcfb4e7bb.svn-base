package com.globits.emr.dto.concept;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.concept.ConceptAttributeType;

public class ConceptAttributeTypeDto extends BaseObjectDto {
	
    private String name;
    private String code;
    private String description;
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
	public ConceptAttributeTypeDto() {
	}
	public ConceptAttributeTypeDto(ConceptAttributeType entity) {
		if (entity != null) {
            setId(entity.getId());
		this.name = entity.getName();
		this.code = entity.getCode();
		this.description = entity.getDescription();
		}
	}
	
	
}
