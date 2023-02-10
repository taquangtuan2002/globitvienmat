package com.globits.emr.service;

import com.globits.core.service.GenericService;
import com.globits.emr.domain.SystemConfig;
import com.globits.emr.dto.SystemConfigDto;
import com.globits.emr.dto.search.SearchDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface SystemConfigService extends GenericService<SystemConfig, UUID> {
    Page<SystemConfigDto> pagingSystemConfig(SearchDto dto);

    SystemConfigDto saveOrUpdate(SystemConfigDto dto, UUID id);

    SystemConfigDto getById(UUID id);

    Boolean deleteById(UUID id);

    Boolean checkKeyCode(SystemConfigDto dto);

    SystemConfigDto getByKeyCode(String configKey);
}
