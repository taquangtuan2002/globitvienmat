package com.globits.emr.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.core.dto.ProfessionDto;
import com.globits.emr.EmrConstants;
import com.globits.emr.dto.VisitTypeDto;
import com.globits.emr.service.EmrProfessionService;

@RestController
@RequestMapping("/api/profession")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestEmrProfessionController {
@Autowired
EmrProfessionService professionService;
@Secured({ EmrConstants.ROLE_ADMIN })
@PostMapping("/save-or-update")
public ResponseEntity<?> saveOrUpdate(@RequestBody ProfessionDto dto) {

	return new ResponseEntity<>(professionService.saveOrUpdate(dto), HttpStatus.OK);
}

@Secured({ EmrConstants.ROLE_ADMIN })
@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
	Boolean result = professionService.deleteByid(id);
	return new ResponseEntity<>(result, HttpStatus.OK);
}

@Secured({ EmrConstants.ROLE_ADMIN })
@RequestMapping(value = "delete-voided/{id}", method = RequestMethod.PUT)
public ResponseEntity<Boolean> deleteVoided(@PathVariable UUID id) {
	boolean result;
	ProfessionDto dto = professionService.deleteVoided(id);
	if (dto != null) {
		result = true;
		return new ResponseEntity<>(result, HttpStatus.OK);
	} else {
		result = false;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}

@Secured({ EmrConstants.ROLE_ADMIN })
@GetMapping("/get-by-id/{id}")
public ProfessionDto getById(@PathVariable UUID id) {
	return professionService.getById(id);
}
}
