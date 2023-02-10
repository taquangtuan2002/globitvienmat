package com.globits.emr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.globits.core.domain.BaseObject;
@Entity
@Table(name = "tbl_episode_of_care")
public class EpisodeOfCare extends BaseObject {
	private static final long serialVersionUID = 1L;
	@Column(name = "date_started", nullable = false, length = 19)
	private Date startDatetime;
	@Column(name = "date_stopped", length = 19)
	private Date stopDatetime;
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
