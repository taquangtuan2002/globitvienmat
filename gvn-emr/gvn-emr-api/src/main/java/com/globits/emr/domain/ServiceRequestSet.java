package com.globits.emr.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_service_request_set")
public class ServiceRequestSet extends BaseObjectMetadata{
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "serviceSet")
	private Set<ServiceRequestSetMember> members;

	public Set<ServiceRequestSetMember> getMembers() {
		return members;
	}
	public void setMembers(Set<ServiceRequestSetMember> members) {
		this.members = members;
	}
}
