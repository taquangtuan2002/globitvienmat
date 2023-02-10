package com.globits.emr.dto.concept;


import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.concept.ConceptAttribute;

public class ConceptAttributeDto extends BaseObjectDto {
	
	private ConceptAttributeTypeDto conceptAttributeType;
    private ConceptDto concept;
    private String value;

    

    public ConceptAttributeTypeDto getConceptAttributeType() {
		return conceptAttributeType;
	}

	public void setConceptAttributeType(ConceptAttributeTypeDto conceptAttributeType) {
		this.conceptAttributeType = conceptAttributeType;
	}

	public ConceptDto getConcept() {
		return concept;
	}

	public void setConcept(ConceptDto concept) {
		this.concept = concept;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
	public ConceptAttributeDto() {
	}
	public ConceptAttributeDto(ConceptAttribute entity) {
		if (entity != null) {
            setId(entity.getId());
            if(entity.getConcept() != null) {
            	this.concept = new ConceptDto();
            	this.concept.setId(entity.getConcept().getId());
            }
            if(entity.getConceptAttributeType() != null) {
            	this.conceptAttributeType = new ConceptAttributeTypeDto();
            	this.conceptAttributeType.setId(entity.getConceptAttributeType().getId());
            }
		}
	}
	
	
}
