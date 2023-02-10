package com.globits.emr.rest;

import java.util.ArrayList;
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
import com.globits.emr.domain.VisitType;
import com.globits.emr.dto.PersonAttributeTypeDto;
import com.globits.emr.dto.VisitTypeDto;
import com.globits.emr.service.VisitTypeService;

@RestController
@RequestMapping("/api/visit-type")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestVisitTypeController {
	@Autowired
	VisitTypeService visitTypeService;

	@Secured({ EmrConstants.ROLE_ADMIN })
	@PostMapping("/save-or-update")
	public VisitTypeDto saveOrUpdate(@RequestBody VisitTypeDto dto) {

		return new VisitTypeDto(visitTypeService.saveOrUpdate(dto));
	}

	@Secured({ EmrConstants.ROLE_ADMIN })
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
		Boolean result = visitTypeService.delete(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Secured({ EmrConstants.ROLE_ADMIN })
	@RequestMapping(value = "/check-code", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = visitTypeService.checkCode(id, code);
		return new ResponseEntity<>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@Secured({ EmrConstants.ROLE_ADMIN })
	@RequestMapping(value = "delete-voided/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> deleteVoided(@PathVariable UUID id) {
		boolean result;
		VisitTypeDto dto = visitTypeService.deleteVoided(id);
		if (dto != null) {
			result = true;
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = false;
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@Secured({ EmrConstants.ROLE_ADMIN })
	@GetMapping("/get-all")
	public List<VisitTypeDto> getAll() {
		List<VisitTypeDto> lstVisitTypeDtos = new ArrayList<VisitTypeDto>();
		List<VisitType> lstVisitTypes = visitTypeService.getAllVisitTypes();
		for (VisitType visitType : lstVisitTypes) {
			VisitTypeDto dto = new VisitTypeDto(visitType);
			lstVisitTypeDtos.add(dto);

		}
		return lstVisitTypeDtos;
	}

}
