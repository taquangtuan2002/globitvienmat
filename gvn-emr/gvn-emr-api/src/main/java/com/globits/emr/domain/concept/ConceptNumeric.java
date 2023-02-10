package com.globits.emr.domain.concept;

import com.globits.core.domain.BaseObject;

import javax.persistence.*;

@Entity
@Table(name = "tbl_concept_numeric")
public class ConceptNumeric extends BaseObject {
    private static final long serialVersionUID = -902288385568906216L;

    @ManyToOne
    @JoinColumn(name = "concept_id")
    private Concept concept;
    @Column(name = "hi_absolute")
    private Double hiAbsolute;

    @Column(name = "hi_critical")
    private Double hiCritical;

    @Column(name = "hi_normal")
    private Double hiNormal;

    @Column(name = "low_absolute")
    private Double lowAbsolute;

    @Column(name = "low_critical")
    private Double lowCritical;

    @Column(name = "low_normal")
    private Double lowNormal;

    @Column(name = "units")
    private String units;

    @Column(name = "display_precision")
    private Integer displayPrecision;

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Double getHiAbsolute() {
        return hiAbsolute;
    }

    public void setHiAbsolute(Double hiAbsolute) {
        this.hiAbsolute = hiAbsolute;
    }

    public Double getHiCritical() {
        return hiCritical;
    }

    public void setHiCritical(Double hiCritical) {
        this.hiCritical = hiCritical;
    }

    public Double getHiNormal() {
        return hiNormal;
    }

    public void setHiNormal(Double hiNormal) {
        this.hiNormal = hiNormal;
    }

    public Double getLowAbsolute() {
        return lowAbsolute;
    }

    public void setLowAbsolute(Double lowAbsolute) {
        this.lowAbsolute = lowAbsolute;
    }

    public Double getLowCritical() {
        return lowCritical;
    }

    public void setLowCritical(Double lowCritical) {
        this.lowCritical = lowCritical;
    }

    public Double getLowNormal() {
        return lowNormal;
    }

    public void setLowNormal(Double lowNormal) {
        this.lowNormal = lowNormal;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getDisplayPrecision() {
        return displayPrecision;
    }

    public void setDisplayPrecision(Integer displayPrecision) {
        this.displayPrecision = displayPrecision;
    }
}
