package com.globits.emr.domain;

import javax.persistence.*;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.domain.Person;
@Entity
@Table(name = "tbl_patient")
public class Patient extends Person {
	/*
	 * Contain the list of property of the patient (but not person)
	 * Which the doctor need to know (we will define it later) 
	 * For example : The list of allergy
	 */
	
	@Column(name="code")
	private String code;//Patient Code

	@ManyToOne
	@JoinColumn(name = "current_residence_id")
	private AdministrativeUnit currentResidence; // địa chỉ hiện tại

	@Column(name = "current_detail_residence")
	private String currentDetailResidence;

	@ManyToOne
	@JoinColumn(name = "permanent_residence_id")
	private AdministrativeUnit permanentResidence; // địa chỉ thường trú

	@Column(name = "permanent_detail_residence")
	private String permanentDetailResidence;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AdministrativeUnit getCurrentResidence() {
		return currentResidence;
	}

	public void setCurrentResidence(AdministrativeUnit currentResidence) {
		this.currentResidence = currentResidence;
	}

	public String getCurrentDetailResidence() {
		return currentDetailResidence;
	}

	public void setCurrentDetailResidence(String currentDetailResidence) {
		this.currentDetailResidence = currentDetailResidence;
	}

	public AdministrativeUnit getPermanentResidence() {
		return permanentResidence;
	}

	public void setPermanentResidence(AdministrativeUnit permanentResidence) {
		this.permanentResidence = permanentResidence;
	}

	public String getPermanentDetailResidence() {
		return permanentDetailResidence;
	}

	public void setPermanentDetailResidence(String permanentDetailResidence) {
		this.permanentDetailResidence = permanentDetailResidence;
	}
}
