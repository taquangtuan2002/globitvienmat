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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globits.emr.EmrConstants;
import com.globits.emr.dto.EncounterTypeDto;
import com.globits.emr.dto.PersonAttributeTypeDto;
import com.globits.emr.service.EncounterTypeService;

@RestController
@RequestMapping("/api/encounter-type")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestEncounterTypeController {
@Autowired
EncounterTypeService encounterTypeService;

@Secured({EmrConstants.ROLE_ADMIN})
@PostMapping("/save-or-update")
public ResponseEntity<?> saveOrUpdate(@RequestBody EncounterTypeDto dto) {

	return new ResponseEntity<>(encounterTypeService.saveOrUpdate(dto), HttpStatus.OK);
}
@Secured({EmrConstants.ROLE_ADMIN})
@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
    Boolean result =encounterTypeService.delete(id);
    return new ResponseEntity<>(result, HttpStatus.OK);
}
@Secured({EmrConstants.ROLE_ADMIN})
@RequestMapping(value = "delete-voided/{id}", method = RequestMethod.PUT)
public ResponseEntity<Boolean> deleteVoided(@PathVariable UUID id) {
	boolean result;
   EncounterTypeDto dto= encounterTypeService.deleteVoided(id);
    if(dto!=null) {
    	result =true;
    	return new ResponseEntity<>(result,HttpStatus.OK);
    }else {
    	result =false;
    	return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
@Secured({EmrConstants.ROLE_ADMIN})
@GetMapping("/get-all")
public List<EncounterTypeDto>getAll(){
	return encounterTypeService.getAllEncounterTypeDtos();
}
@Secured({EmrConstants.ROLE_ADMIN})
@RequestMapping(value = "/check-code", method = RequestMethod.GET)
public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
                                         @RequestParam("code") String code) {
    Boolean result = encounterTypeService.checkCode(id, code);
    return new ResponseEntity<>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
}
}
