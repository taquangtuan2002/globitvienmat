package com.globits.emr.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.EpisodeOfCare;


public class EpisodeOfCareDto extends BaseObjectDto{
private Date startDatetime;
private Date stopDatetime;
public EpisodeOfCareDto() {
	
}
public EpisodeOfCareDto(EpisodeOfCare entity) {
	this.startDatetime=entity.getStartDatetime();
	this.stopDatetime=entity.getStopDatetime();
}
public Date getStartDatetime() {
	return startDatetime;
}
public void setStartDatetime(Date startDatetime) {
	this.startDatetime = startDatetime;
}
public Date getStopDatetime() {
	return stopDatetime;
}
public void setStopDatetime(Date stopDatetime) {
	this.stopDatetime = stopDatetime;
}





}
