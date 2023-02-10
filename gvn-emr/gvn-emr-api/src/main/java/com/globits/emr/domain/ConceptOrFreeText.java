package com.globits.emr.domain;

import com.globits.emr.domain.concept.Concept;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ConceptOrFreeText {
	
	@ManyToOne
	@JoinColumn(name="concept_id")
	private Concept concept;
	
	@Column(name="free_text")
	private String freeText;

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}
	
	
}
