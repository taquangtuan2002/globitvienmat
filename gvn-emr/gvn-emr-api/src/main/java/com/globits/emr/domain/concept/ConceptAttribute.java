package com.globits.emr.domain.concept;

import com.globits.core.domain.BaseObject;
import com.globits.core.domain.Person;
import com.globits.emr.domain.PersonAttributeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_concept_attribute")
public class ConceptAttribute extends BaseObject {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "concept_attribute_type_id")
    private ConceptAttributeType conceptAttributeType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "concept_id")
    private Concept concept;

    private String value;

    public ConceptAttributeType getConceptAttributeType() {
        return conceptAttributeType;
    }

    public void setConceptAttributeType(ConceptAttributeType conceptAttributeType) {
        this.conceptAttributeType = conceptAttributeType;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
