package com.globits.emr.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import com.globits.core.service.GenericService;
import com.globits.emr.domain.Patient;
import com.globits.emr.dto.PatientDto;
import com.globits.emr.dto.ieh.IehPatientDto;
import com.globits.emr.dto.search.SearchDto;

public interface PatientService extends GenericService<Patient, UUID>{
	    Patient saveOrUpdate(UUID id, PatientDto dto);
	    
	    Patient saveEntity(UUID id, Patient entity);
	    
	    IehPatientDto saveOrUpdateIehPatient(UUID id, IehPatientDto dto);

	    Boolean deleteById(UUID id);

	    Patient getPatient(UUID id);
	    
	    IehPatientDto getIehPatient(UUID id);

	    Page<PatientDto> searchByPage(SearchDto dto);

	    Boolean checkCode(UUID id, String code);

		void updateVoided(UUID id);

		Boolean deleteFakeVoided(UUID id);

		Page<IehPatientDto> searchByPageIeh(SearchDto dto);

}
