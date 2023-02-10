package com.globits.emr.service;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.service.GenericService;
import com.globits.emr.dto.AdministrativeUnitDto;
import com.globits.emr.dto.function.AdministrativeUnitImportExcel;
import com.globits.emr.dto.search.AdministrativeUnitSearchDto;
import com.globits.emr.dto.search.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface AdministrativeUnitsService extends GenericService<AdministrativeUnit, UUID> {

    Page<AdministrativeUnitDto> searchByDto(AdministrativeUnitSearchDto dto);

    AdministrativeUnitDto getById(UUID id);

    Boolean deleteById(UUID id);

    AdministrativeUnitDto saveOrUpdate(AdministrativeUnitDto dto, UUID id);

    List<AdministrativeUnitDto> getAllChildByParentId(UUID parentId);

    List<AdministrativeUnitDto> getAllByLevel(Integer level);
    List<AdministrativeUnitDto> getAllByLevelNew(Integer level);

    Page<AdministrativeUnitDto> getRootUnit(SearchDto dto);

    Boolean checkDuplicateCode(AdministrativeUnitDto dto);

    List<AdministrativeUnitDto> importExcel(List<AdministrativeUnitImportExcel> dtos);

    List<UUID> getAllAdministrativeIdByParentId(UUID parentId, Boolean isCommunes);

    List<AdministrativeUnitDto> getAllByLevelAndParentId(Integer level, UUID parentId);

    AdministrativeUnit getAdministrativeUnitByNameAndLevel(String name, Integer level, UUID parentId);
}
