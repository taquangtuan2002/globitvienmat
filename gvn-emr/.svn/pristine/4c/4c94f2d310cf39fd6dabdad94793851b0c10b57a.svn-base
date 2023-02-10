package com.globits.emr.domain.concept;

import javax.persistence.*;

import com.globits.emr.domain.BaseObjectMetadata;

import java.util.Set;

@Entity
@Table(name = "tbl_concept")
public class Concept extends BaseObjectMetadata {
    @ManyToOne
    @JoinColumn(name = "concept_type_id")
    private ConceptType conceptType;

    @ManyToOne
    @JoinColumn(name = "concept_datatype_id")
    private ConceptDatatype conceptDataType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Concept parent;

    @OneToMany(mappedBy = "concept", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    private Set<ConceptAnswer> conceptAnswers; // danh sách kết quả concept thuộc concept tương ứng (datatype=coded)

    @OneToMany(mappedBy = "concept", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    private Set<ConceptNumeric> conceptNumerics; // danh sách kết quả concept thuộc concept tương ứng (datatype=numeric)

    @OneToMany(mappedBy = "concept", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    private Set<ConceptAttribute> attributes; //


    public ConceptType getConceptType() {
        return conceptType;
    }

    public void setConceptType(ConceptType conceptType) {
        this.conceptType = conceptType;
    }

    public ConceptDatatype getConceptDataType() {
        return conceptDataType;
    }

    public void setConceptDataType(ConceptDatatype conceptDataType) {
        this.conceptDataType = conceptDataType;
    }

    public Concept getParent() {
        return parent;
    }

    public void setParent(Concept parent) {
        this.parent = parent;
    }

    public Set<ConceptAnswer> getConceptAnswers() {
        return conceptAnswers;
    }

    public void setConceptAnswers(Set<ConceptAnswer> conceptAnswers) {
        this.conceptAnswers = conceptAnswers;
    }

    public Set<ConceptNumeric> getConceptNumerics() {
        return conceptNumerics;
    }

    public void setConceptNumerics(Set<ConceptNumeric> conceptNumerics) {
        this.conceptNumerics = conceptNumerics;
    }

    public Set<ConceptAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<ConceptAttribute> attributes) {
        this.attributes = attributes;
    }
}
