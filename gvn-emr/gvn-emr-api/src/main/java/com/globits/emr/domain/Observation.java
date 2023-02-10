package com.globits.emr.domain;

import com.globits.core.domain.BaseObject;
import com.globits.emr.domain.concept.Concept;
import com.globits.emr.domain.concept.ConceptType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_observation")
public class Observation extends BaseObject {

    @ManyToOne
    @JoinColumn(name = "concept_id")
    private Concept concept;

    @ManyToOne
    @JoinColumn(name="encounter_id")
    private Encounter encounter;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @Column(name="value_numeric")
    private Double valueNumeric;

    @Column(name="value_text")
    private String valueText;

    @Column(name="value_date")
    private Date valueDate;

    @Column(name="value_boolean")
    private Boolean valueBoolean;

    @ManyToOne
    @JoinColumn(name="value_coded")
    private Concept valueCoded;

    @Column(name="comments")
    private String comments;

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Double getValueNumeric() {
        return valueNumeric;
    }

    public void setValueNumeric(Double valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Boolean getValueBoolean() {
        return valueBoolean;
    }

    public void setValueBoolean(Boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
    }

    public Concept getValueCoded() {
        return valueCoded;
    }

    public void setValueCoded(Concept valueCoded) {
        this.valueCoded = valueCoded;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
