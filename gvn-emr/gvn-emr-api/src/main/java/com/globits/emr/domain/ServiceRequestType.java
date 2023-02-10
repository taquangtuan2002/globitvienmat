package com.globits.emr.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
/*
 * Present of the service request type which maybe:
 ***** Lab Test Order
 ***** Imaging Diagnotic
 ***** Clinical Examination 
 */
@Entity
@Table(name = "tbl_service_request_type")
public class ServiceRequestType extends BaseObjectMetadata{
	private static final long serialVersionUID = 1L;
	
}
