package com.globits.emr.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.globits.core.domain.BaseObject;
@Entity
@Table(name = "tbl_diagnosis")
public class Diagnosis extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "freeText", column = @Column(name = "diagnosis_free_text")) })
	@AssociationOverrides({ @AssociationOverride(name = "concept", joinColumns = @JoinColumn(name = "diagnosis_concept_id")) 
	})
	private ConceptOrFreeText diagnosis;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "encounter_id")
	private Encounter encounter;

	public ConceptOrFreeText getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(ConceptOrFreeText diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
}
