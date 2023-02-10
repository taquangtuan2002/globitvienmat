package com.globits.emr.visit;

import com.globits.emr.dto.ObservationDto;

import java.util.ArrayList;
import java.util.List;

public class ObservationGroupDto {
    private String conceptCode;//concept cua question(001)
    private List<ObservationDto> items;//answer

    public ObservationGroupDto(){

    }


    public String getConceptCode() {
        return conceptCode;
    }

    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
    }

    public List<ObservationDto> getItems() {
        return items;
    }

    public void setItems(List<ObservationDto> items) {
        this.items = items;
    }
}
