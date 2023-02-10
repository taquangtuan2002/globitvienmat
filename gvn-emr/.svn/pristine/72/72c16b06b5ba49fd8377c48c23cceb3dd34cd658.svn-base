package com.globits.emr.dto;

import java.util.UUID;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.PersonAttribute;

public class PersonAttributeDto extends BaseObjectDto {
	private UUID person_attribute_type_Id;
	private UUID person_id;
	private String value;

	public PersonAttributeDto() {
		super();
	}

	public PersonAttributeDto(PersonAttribute entity) {
		this.createDate=entity.getCreateDate();
		this.id=entity.getId();
		this.modifyDate=entity.getModifyDate();
		this.person_attribute_type_Id=entity.getPersonAttributeType().getId();
		this.person_id=entity.getPerson().getId();
	}

	public UUID getPerson_attribute_type_Id() {
		return person_attribute_type_Id;
	}

	public void setPerson_attribute_type_Id(UUID person_attribute_type_Id) {
		this.person_attribute_type_Id = person_attribute_type_Id;
	}

	public UUID getPerson_id() {
		return person_id;
	}

	public void setPerson_id(UUID person_id) {
		this.person_id = person_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
