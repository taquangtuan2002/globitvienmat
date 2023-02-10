package com.globits.emr.dto.concept;


import java.util.UUID;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.concept.Concept;
import com.globits.emr.domain.concept.ConceptAnswer;

public class ConceptAnswerDto extends BaseObjectDto{
	private UUID id;
	private ConceptDto concept;
    private ConceptDto answerConcept;
    private Integer viewIndex;// thứ tự hiển thị (thứ tự cao là kết quả cao)

    public ConceptAnswerDto() {
	}
	
	public ConceptAnswerDto(ConceptAnswer entity) {
		if (entity != null) {
			this.id =entity.getId();
    		this.viewIndex = entity.getViewIndex();
    		if(entity.getAnswerConcept() != null) {
            	this.answerConcept = new ConceptDto();
            	this.answerConcept.setId(entity.getAnswerConcept().getId());
            }
    		if(entity.getConcept() != null) {
            	this.concept = new ConceptDto();
            	this.concept.setId(entity.getConcept().getId());
            }
		}
	}

    public ConceptDto getConcept() {
		return concept;
	}

	public void setConcept(ConceptDto concept) {
		this.concept = concept;
	}

	public ConceptDto getAnswerConcept() {
		return answerConcept;
	}

	public void setAnswerConcept(ConceptDto answerConcept) {
		this.answerConcept = answerConcept;
	}

	public Integer getViewIndex() {
        return viewIndex;
    }

    public void setViewIndex(Integer viewIndex) {
        this.viewIndex = viewIndex;
    }
    
    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	
}
