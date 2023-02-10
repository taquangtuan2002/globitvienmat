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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globits.emr.EmrConstants;
import com.globits.emr.dto.concept.ConceptTypeDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.ConceptTypeService;

@RestController
@RequestMapping("/api/concept-type")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestConceptTypeController {
	@Autowired
	ConceptTypeService conceptTypeService;
	
	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ConceptTypeDto> create(@RequestBody ConceptTypeDto dto) {
    	ConceptTypeDto result = conceptTypeService.saveOrUpdate(null, dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ConceptTypeDto> update(@RequestBody ConceptTypeDto dto, @PathVariable UUID id) {
    	ConceptTypeDto result = conceptTypeService.saveOrUpdate(id, dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ConceptTypeDto> getConceptType(@PathVariable UUID id) {
        ConceptTypeDto result = conceptTypeService.getConceptType(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        Boolean result = conceptTypeService.deleteById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public ResponseEntity<Page<ConceptTypeDto>> searchByPage(@RequestBody SearchDto searchDto) {
        Page<ConceptTypeDto> page = this.conceptTypeService.searchByPage(searchDto);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/check-code", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
                                             @RequestParam("code") String code) {
        Boolean result = conceptTypeService.checkCode(id, code);
        return new ResponseEntity<>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/delete-fake/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> deleteFakeVoided(@PathVariable("id") UUID id) {
		Boolean result = conceptTypeService.deleteFakeVoided(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

}
