package com.globits.emr.dto;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.dto.BaseObjectDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdministrativeUnitDto extends BaseObjectDto {
    private String name;
    private String code;
    private Integer level;
    private AdministrativeUnitDto parent;
    private List<AdministrativeUnitDto> children;
    private String linePath;
    private UUID parentId;
    private String parentName;
    private String parentCode;

    public AdministrativeUnitDto() {
        super();
    }

    public AdministrativeUnitDto(AdministrativeUnit entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();
    }

    public AdministrativeUnitDto(AdministrativeUnit entity, Boolean includeChild) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getParent() != null) {
            this.parent = new AdministrativeUnitDto(entity.getParent(), false);
            this.parentId = entity.getParent().getId();
        }

        if(includeChild) {
            if(entity.getSubAdministrativeUnits() != null && entity.getSubAdministrativeUnits().size() > 0) {
                List<AdministrativeUnitDto> subs = new ArrayList<>();
                for(AdministrativeUnit unit : entity.getSubAdministrativeUnits()) {
                    subs.add(new AdministrativeUnitDto(unit, false));
                }
                this.children = subs;
            }
        }
    }

    public AdministrativeUnitDto(AdministrativeUnit entity, Boolean includeParent, Boolean includeChild) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();

        if (includeParent && entity.getParent() != null) {
            this.parent = new AdministrativeUnitDto(entity.getParent());
            this.parentId = entity.getParent().getId();
        }

        if (includeChild && entity.getSubAdministrativeUnits() != null
                && entity.getSubAdministrativeUnits().size() > 0) {
            List<AdministrativeUnitDto> subs = new ArrayList<>();
            for (AdministrativeUnit unit : entity.getSubAdministrativeUnits()) {
                subs.add(new AdministrativeUnitDto(unit, true, false));
            }
            this.children = subs;
        }

    }

    public AdministrativeUnitDto(UUID id, String name, String code, Integer level) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public AdministrativeUnitDto getParent() {
        return parent;
    }

    public void setParent(AdministrativeUnitDto parent) {
        this.parent = parent;
    }

    public List<AdministrativeUnitDto> getChildren() {
        return children;
    }

    public void setChildren(List<AdministrativeUnitDto> children) {
        this.children = children;
    }

    public String getLinePath() {
        return linePath;
    }

    public void setLinePath(String linePath) {
        this.linePath = linePath;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
