package com.globits.emr.dto.concept;


import java.util.UUID;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.concept.Concept;
import com.globits.emr.domain.concept.ConceptNumeric;

public class ConceptNumericsDto extends BaseObjectDto{
	private UUID id;
	private ConceptDto concept;
    private Double hiAbsolute;
    private Double hiCritical;
    private Double hiNormal;
    private Double lowAbsolute;
    private Double lowCritical;
    private Double lowNormal;
    private String units;
    private Integer displayPrecision;

    public ConceptDto getConcept() {
		return concept;
	}

	public void setConcept(ConceptDto concept) {
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
    
    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ConceptNumericsDto() {
	}

	public ConceptNumericsDto(ConceptNumeric entity) {
		if (entity != null) {
            setId(entity.getId());
            if(entity.getConcept() != null) {
            	this.concept = new ConceptDto();
            	this.concept.setId(entity.getConcept().getId());
            }
    		this.hiAbsolute = entity.getHiAbsolute();
    		this.hiCritical = entity.getHiCritical();
    		this.hiNormal = entity.getHiNormal();
    		this.lowAbsolute = entity.getLowAbsolute();
    		this.lowCritical = entity.getLowCritical();
    		this.lowNormal = entity.getLowNormal();
    		this.units = entity.getUnits();
    		this.displayPrecision = entity.getDisplayPrecision();
		}
	}
}
