package com.globits.emr.rest;

import com.globits.emr.EmrConstants;
import com.globits.emr.dto.AdministrativeUnitDto;
import com.globits.emr.dto.search.AdministrativeUnitSearchDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.AdministrativeUnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/administrative-unit")
public class RestEmrAdministrativeUnitController {
    @Autowired
    AdministrativeUnitsService service;

    @Secured({ EmrConstants.ROLE_ADMIN})
    @PostMapping("/search-by-dto")
    public ResponseEntity<?> searchByDto(@RequestBody AdministrativeUnitSearchDto dto) {
        Page<AdministrativeUnitDto> result = service.searchByDto(dto);

        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdministrativeUnitDto> getById(@PathVariable UUID id) {
        AdministrativeUnitDto result = service.getById(id);
        return new ResponseEntity<AdministrativeUnitDto>(result,
                (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AdministrativeUnitDto> save(@RequestBody AdministrativeUnitDto dto) {
        AdministrativeUnitDto result = service.saveOrUpdate(dto, null);
        return new ResponseEntity<AdministrativeUnitDto>(result,
                (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AdministrativeUnitDto> update(@RequestBody AdministrativeUnitDto dto,
                                                        @PathVariable("id") UUID id) {
        AdministrativeUnitDto result = service.saveOrUpdate(dto, id);
        return new ResponseEntity<AdministrativeUnitDto>(result,
                (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        Boolean result = service.deleteById(UUID.fromString(id));
        return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all-child-By-parent-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<AdministrativeUnitDto>> getAllChildByParentId(@PathVariable UUID id) {
        List<AdministrativeUnitDto> result = service.getAllChildByParentId(id);
        return new ResponseEntity<List<AdministrativeUnitDto>>(result,
                (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @PostMapping("/get-root-unit")
    public ResponseEntity<?> getRootUnit(@RequestBody SearchDto dto) {
        Page<AdministrativeUnitDto> result = service.getRootUnit(dto);
        return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/check-code", method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkCode(@RequestBody AdministrativeUnitDto dto) {
        Boolean result = service.checkDuplicateCode(dto);
        return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all-by-level/{level}", method = RequestMethod.GET)
    public ResponseEntity<List<AdministrativeUnitDto>> getAllByLevel(@PathVariable Integer level) {
        List<AdministrativeUnitDto> result = service.getAllByLevel(level);
        return new ResponseEntity<List<AdministrativeUnitDto>>(result,
                (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Secured({ EmrConstants.ROLE_ADMIN})
    @RequestMapping(value = "/get-all-administrative-id-by-parent-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<UUID>> getAllAdministrativeIdByParentId(@PathVariable UUID id) {
        List<UUID> result = service.getAllAdministrativeIdByParentId(id, true);
        return new ResponseEntity<List<UUID>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
