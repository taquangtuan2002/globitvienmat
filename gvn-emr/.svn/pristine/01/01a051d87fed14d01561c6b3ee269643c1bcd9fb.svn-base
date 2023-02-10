package com.globits.emr.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.emr.EmrConstants;
import com.globits.emr.domain.ServiceFee;
import com.globits.emr.dto.ServiceFeeDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.ServiceFeeService;

@RestController
@RequestMapping("/api/service-fee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestServiceFeeController {
	@Autowired
	private ServiceFeeService serviceFeeService;

	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/get-all-dto", method = RequestMethod.GET)
	public ResponseEntity<List<ServiceFeeDto>> getAllDto() {
		List<ServiceFeeDto>lstServiceFeeDtos=new ArrayList<ServiceFeeDto>();
		List<ServiceFee>lstServiceFees=serviceFeeService.getAllDto();
		for (ServiceFee serviceFee : lstServiceFees) {
			ServiceFeeDto dto=new ServiceFeeDto(serviceFee);
			lstServiceFeeDtos.add(dto);
		}
		return new ResponseEntity<>(lstServiceFeeDtos, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ServiceFeeDto> getDtoById(@PathVariable("id") UUID id) {
		ServiceFee result = serviceFeeService.getDtoById(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ServiceFeeDto(result), HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ServiceFeeDto> saveDto(@RequestBody ServiceFee dto) {
		ServiceFee result = serviceFeeService.saveOrUpdate(null, dto);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ServiceFeeDto(result), HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ServiceFeeDto> updateDto(@PathVariable("id") UUID id, @RequestBody ServiceFee dto) {
		ServiceFee result = serviceFeeService.saveOrUpdate(id, dto);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ServiceFeeDto(result), HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteDto(@PathVariable("id") UUID id) {
		Boolean result = serviceFeeService.deleteById(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
	public ResponseEntity<Page<ServiceFeeDto>> searchByPage(@RequestBody SearchDto dto) {
		Page<ServiceFeeDto> result = serviceFeeService.searchByPage(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/delete-fake/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteFakeVoided(@PathVariable("id") UUID id) {
		Boolean result = serviceFeeService.deleteFakeVoided(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
