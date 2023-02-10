package com.globits.emr.domain;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.core.domain.BaseObject;
@Entity
@Table(name = "tbl_condition")
public class Condition extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "freeText", column = @Column(name = "diagnosis_free_text")) })
	@AssociationOverrides({ @AssociationOverride(name = "concept", joinColumns = @JoinColumn(name = "diagnosis_concept_id")) 
	})
	private ConceptOrFreeText condition;
	
	@Column(name = "onset_date")
	private Date onsetDate;
	
	@Column(name = "end_date")
	private Date endDate;

	
	@ManyToOne(optional = false)
	@JoinColumn(name = "encounter_id")
	private Encounter encounter;
	
	public ConceptOrFreeText getCondition() {
		return condition;
	}

	public void setCondition(ConceptOrFreeText condition) {
		this.condition = condition;
	}

	public Date getOnsetDate() {
		return onsetDate;
	}

	public void setOnsetDate(Date onsetDate) {
		this.onsetDate = onsetDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	
	
}
