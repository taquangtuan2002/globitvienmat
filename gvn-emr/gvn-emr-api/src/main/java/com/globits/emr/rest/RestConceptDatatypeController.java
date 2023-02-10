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
import com.globits.emr.dto.concept.ConceptDatatypeDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.ConceptDatatypeService;

@RestController
@RequestMapping("/api/concept-data-type")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestConceptDatatypeController {
	@Autowired
	ConceptDatatypeService conceptDatatypeService;
	
	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ConceptDatatypeDto> create(@RequestBody ConceptDatatypeDto dto) {
    	ConceptDatatypeDto result = conceptDatatypeService.saveOrUpdate(null, dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ConceptDatatypeDto> update(@RequestBody ConceptDatatypeDto dto, @PathVariable UUID id) {
    	ConceptDatatypeDto result = conceptDatatypeService.saveOrUpdate(id, dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ConceptDatatypeDto> getConceptDatatype(@PathVariable UUID id) {
        ConceptDatatypeDto result = conceptDatatypeService.getConceptDatatype(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        Boolean result = conceptDatatypeService.deleteById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
    public ResponseEntity<Page<ConceptDatatypeDto>> searchByPage(@RequestBody SearchDto searchDto) {
        Page<ConceptDatatypeDto> page = this.conceptDatatypeService.searchByPage(searchDto);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

	@Secured({EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/check-code", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
                                             @RequestParam("code") String code) {
        Boolean result = conceptDatatypeService.checkCode(id, code);
        return new ResponseEntity<>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
	
	@Secured({EmrConstants.ROLE_ADMIN})
	@RequestMapping(value = "/delete-fake/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> deleteFakeVoided(@PathVariable("id") UUID id) {
		Boolean result = conceptDatatypeService.deleteFakeVoided(id);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

}
