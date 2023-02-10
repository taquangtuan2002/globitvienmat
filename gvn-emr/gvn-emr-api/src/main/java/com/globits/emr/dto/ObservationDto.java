package com.globits.emr.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.Observation;
import com.globits.emr.domain.concept.Concept;
import com.globits.emr.dto.concept.ConceptDto;
import com.globits.emr.utils.ConceptUtil;

import java.util.Date;

public class ObservationDto extends BaseObjectDto {
    private String conceptCode;

    private EncounterDto encounter;

    private PatientDto patient;

    private Double valueNumeric;

    private String valueText;

    private Date valueDate;

    private Boolean valueBoolean;
    private String valueCoded;// giá trị của obs thuộc 1 loại concept nào đó
    private Boolean checked;
    private String comments;// có thể dùng cho trường hợp lưu giá trị chi tiết khác

    public ObservationDto() {
    }

    public ObservationDto(Observation obs) {
        if (obs != null) {
            this.id = obs.getId();
            this.comments = obs.getComments();
            if(obs.getConcept()!=null){
                this.conceptCode = obs.getConcept().getCode();
            }
            this.valueNumeric = obs.getValueNumeric();
            this.valueText = obs.getValueText();
            this.valueDate = obs.getValueDate();
            this.comments=obs.getComments();
            if(obs.getValueCoded()!=null){
                this.valueCoded = obs.getValueCoded().getCode();
            }

        }
    }
	public ObservationDto(Concept entity, String code) {
		if(entity!=null) {
			this.conceptCode = code;
			this.valueCoded = entity.getCode();
		}
	}
    public String getConceptCode() {
        return conceptCode;
    }

    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
    }

    public EncounterDto getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterDto encounter) {
        this.encounter = encounter;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
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

    public String getValueCoded() {
        return valueCoded;
    }

    public void setValueCoded(String valueCoded) {
        this.valueCoded = valueCoded;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
