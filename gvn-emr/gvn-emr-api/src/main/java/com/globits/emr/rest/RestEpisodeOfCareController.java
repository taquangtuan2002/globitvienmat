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

import com.globits.emr.EmrConstants;
import com.globits.emr.domain.EpisodeOfCare;
import com.globits.emr.dto.EpisodeOfCareDto;
import com.globits.emr.dto.PatientDto;
import com.globits.emr.dto.PersonAttributeTypeDto;
import com.globits.emr.dto.concept.ConceptAttributeTypeDto;
import com.globits.emr.repository.EpisodeOfCareRepository;
import com.globits.emr.service.EpisodeOfCareService;

@RestController
@RequestMapping("/api/episode-of-care")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestEpisodeOfCareController {
	@Autowired
	EpisodeOfCareService episodeOfCareService;
	@Secured({EmrConstants.ROLE_ADMIN})
	@PostMapping("/save-or-update")
	public ResponseEntity<?> create(@RequestBody EpisodeOfCareDto dto) {
		EpisodeOfCare result = episodeOfCareService.save(dto);
		EpisodeOfCareDto resultDto=new EpisodeOfCareDto(result);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
	}
	@Secured({EmrConstants.ROLE_ADMIN})
	@PostMapping("/save-or-update-entity")
	public ResponseEntity<?> createEntity(@RequestBody EpisodeOfCare entity) {
		EpisodeOfCare result = episodeOfCareService.saveEntity(entity);
		EpisodeOfCareDto resultDto=new EpisodeOfCareDto(result);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
	}
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
	    Boolean result = episodeOfCareService.delete(id);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "delete-voided/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> deleteVoided(@PathVariable UUID id) {
		boolean result;
	    EpisodeOfCare dto= episodeOfCareService.deletevoided(id);
	    if(dto!=null) {
	    	result =true;
	    	return new ResponseEntity<>(result,HttpStatus.OK);
	    }else {
	    	result =false;
	    	return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
	    }
	}
	@Secured({EmrConstants.ROLE_ADMIN})
	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id){
		EpisodeOfCare result=episodeOfCareService.getById(id);
		EpisodeOfCareDto resultDto=new EpisodeOfCareDto(result);
		return new ResponseEntity<>(resultDto, HttpStatus.OK);
	}
	@Secured({EmrConstants.ROLE_ADMIN})
	@GetMapping("/getAll")
	public List<EpisodeOfCare> getAll(){
		return episodeOfCareService.getAll();
	}
}
