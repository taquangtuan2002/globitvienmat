package com.globits.emr.rest;

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

import com.globits.core.dto.CountryDto;
import com.globits.emr.EmrConstants;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.EmrCountryService;

@RestController
@RequestMapping("/api/emr-country")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestEmrCountryController {
	@Autowired
	private EmrCountryService emrCountryService;

	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CountryDto> getDtoById(@PathVariable("id") UUID id) {
		CountryDto result = emrCountryService.getDtoById(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<CountryDto> saveDto(@RequestBody CountryDto dto) {
		CountryDto result = emrCountryService.saveOrUpdate(null, dto);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CountryDto> updateDto(@PathVariable("id") UUID id, @RequestBody CountryDto dto) {
		CountryDto result = emrCountryService.saveOrUpdate(id, dto);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteDto(@PathVariable("id") UUID id) {
		Boolean result = emrCountryService.deleteById(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
	public ResponseEntity<Page<CountryDto>> searchByPage(@RequestBody SearchDto dto) {
		Page<CountryDto> result = emrCountryService.searchByPage(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/delete-fake/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteFakeVoided(@PathVariable("id") UUID id) {
		Boolean result = emrCountryService.deleteFakeVoided(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
