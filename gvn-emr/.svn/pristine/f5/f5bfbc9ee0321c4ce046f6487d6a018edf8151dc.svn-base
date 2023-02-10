package com.globits.emr.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.domain.SystemConfig;
import com.globits.emr.dto.SystemConfigDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.repository.SystemConfigRepository;
import com.globits.emr.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class SystemConfigServiceImpl extends GenericServiceImpl<SystemConfig, UUID> implements SystemConfigService {
    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @Override
    public Page<SystemConfigDto> pagingSystemConfig(SearchDto dto) {
        if (dto == null)
            return null;

        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        if (pageIndex > 0)
            pageIndex--;
        else
            pageIndex = 0;

        String whereClause = "";

        String orderBy = " ORDER BY entity.configKey ASC ";

        String sqlCount = "select count(entity.id) from SystemConfig as entity where (1=1)";
        String sql = "select new com.globits.vitimes.patient.dto.SystemConfigDto(entity) from SystemConfig as entity where (1=1)";
        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            whereClause += " AND ( entity.configKey LIKE :text OR entity.configValue LIKE :text OR entity.note LIKE :text ) ";
        }
        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, SystemConfigDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            q.setParameter("text", '%' + dto.getKeyword() + '%');
            qCount.setParameter("text", '%' + dto.getKeyword() + '%');
        }

        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<SystemConfigDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(entities, pageable, count);
    }

    @Override
    public SystemConfigDto saveOrUpdate(SystemConfigDto dto, UUID id) {
        if(dto != null) {
            SystemConfig entity = null;
            if(id != null && id.equals(dto.getId())) {
                entity = systemConfigRepository.findById(id).orElse(null);
            }
            if(entity == null) {
                entity = new SystemConfig();
            }
            entity.setConfigKey(dto.getConfigKey());
            entity.setConfigValue(dto.getConfigValue());
            entity.setNote(dto.getNote());

            entity = systemConfigRepository.save(entity);

            if(entity != null) {
                return new SystemConfigDto(entity);
            }
        }
        return null;
    }

    @Override
    public SystemConfigDto getById(UUID id) {
        if(id != null) {
            SystemConfig entity = systemConfigRepository.findById(id).orElse(null);
            if(entity != null) {
                return new SystemConfigDto(entity);
            }
        }
        return null;
    }

    @Override
    public Boolean deleteById(UUID id) {
        if(id != null) {
            SystemConfig entity = systemConfigRepository.findById(id).orElse(null);
            if(entity != null) {
                systemConfigRepository.delete(entity);
                return true;
            }
        }
        return null;
    }
    @Override
    public Boolean checkKeyCode(SystemConfigDto dto) {
        if(dto != null && dto.getConfigKey() != null && StringUtils.hasText(dto.getConfigKey())) {
            List<SystemConfig> listSystemConfig = systemConfigRepository.getByConfigKey(dto.getConfigKey());
            if(listSystemConfig != null && listSystemConfig.size() > 0) {
                SystemConfig entity = listSystemConfig.get(0);
                if(entity.getId() != null && dto.getId() != null && dto.getId().equals(entity.getId())) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return null;
    }

    @Override
    public SystemConfigDto getByKeyCode(String configKey) {
        if(configKey != null && StringUtils.hasText(configKey)) {
            List<SystemConfig> listSystemConfig = systemConfigRepository.getByConfigKey(configKey);
            if(listSystemConfig != null && listSystemConfig.size() > 0) {
                SystemConfig entity = listSystemConfig.get(0);
                return new SystemConfigDto(entity);
            }
        }
        return null;
    }
}
