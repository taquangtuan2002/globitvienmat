package com.globits.emr.domain.concept;

import com.globits.emr.domain.BaseObjectMetadata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "tbl_concept_attribute_type")
public class ConceptAttributeType extends BaseObjectMetadata {
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
    private String name;
	@Column(name = "code")
    private String code;
    @Column(name = "description")
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
}
