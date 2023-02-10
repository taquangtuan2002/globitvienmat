package com.globits.emr.rest;

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
import com.globits.emr.dto.HealthCareRoomDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.HealthCareRoomService;

@RestController
@RequestMapping("/api/health-care-room")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestHealthCareRoomController {
	@Autowired
	private HealthCareRoomService healthCareRoomService;
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/get-all-dto", method = RequestMethod.GET)
	public ResponseEntity<List<HealthCareRoomDto>> getAllDto() {
		return new ResponseEntity<>(healthCareRoomService.getAllDto(), HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HealthCareRoomDto> getDtoById(@PathVariable("id") UUID id) {
		HealthCareRoomDto result = healthCareRoomService.getDtoById(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<HealthCareRoomDto> saveDto(@RequestBody HealthCareRoomDto dto) {
		HealthCareRoomDto result = healthCareRoomService.saveOrUpdate(null, dto);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<HealthCareRoomDto> updateDto(@PathVariable("id") UUID id, @RequestBody HealthCareRoomDto dto) {
		HealthCareRoomDto result = healthCareRoomService.saveOrUpdate(id, dto);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteDto(@PathVariable("id") UUID id) {
		Boolean result = healthCareRoomService.deleteById(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
	public ResponseEntity<Page<HealthCareRoomDto>> searchByPage(@RequestBody SearchDto dto) {
		Page<HealthCareRoomDto> result = healthCareRoomService.searchByPage(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/delete-fake/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteFakeVoided(@PathVariable("id") UUID id) {
		Boolean result = healthCareRoomService.deleteFakeVoided(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
