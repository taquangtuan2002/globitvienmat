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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globits.emr.EmrConstants;
import com.globits.emr.domain.ServiceType;
import com.globits.emr.dto.ServiceTypeDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.ServiceTypeService;

@RestController
@RequestMapping("/api/service-type")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestServiceTypeController {
	@Autowired
	private ServiceTypeService serviceTypeService;
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/get-all-dto", method = RequestMethod.GET)
	public List<ServiceTypeDto> getAllDto() {
		List<ServiceTypeDto>lstServiceTypeDtos=new ArrayList<ServiceTypeDto>();
		List<ServiceType> lstServiceTypes=serviceTypeService.getAllDto();
		for (ServiceType serviceType : lstServiceTypes) {
			ServiceTypeDto dto=new ServiceTypeDto(serviceType);
			lstServiceTypeDtos.add(dto);
		}
		return lstServiceTypeDtos;
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ServiceTypeDto getDtoById(@PathVariable("id") UUID id) {
		ServiceType result = serviceTypeService.getDtoById(id);
		if(result == null) {
			return null;
		}
		return new ServiceTypeDto(result);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ServiceTypeDto> saveDto(@RequestBody ServiceType dto) {
		ServiceType result = serviceTypeService.saveOrUpdate(null, dto);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ServiceTypeDto(result), HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ServiceTypeDto> updateDto(@PathVariable("id") UUID id, @RequestBody ServiceType dto) {
		ServiceType result = serviceTypeService.saveOrUpdate(id, dto);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ServiceTypeDto(result), HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteDto(@PathVariable("id") UUID id) {
		Boolean result = serviceTypeService.deleteById(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id, @RequestParam("code") String code) {
		Boolean result = serviceTypeService.checkCode(id, code);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
	public ResponseEntity<Page<ServiceTypeDto>> searchByPage(@RequestBody SearchDto dto) {
		Page<ServiceTypeDto> result = serviceTypeService.searchByPage(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/delete-fake/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteFakeVoided(@PathVariable("id") UUID id) {
		Boolean result = serviceTypeService.deleteFakeVoided(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
