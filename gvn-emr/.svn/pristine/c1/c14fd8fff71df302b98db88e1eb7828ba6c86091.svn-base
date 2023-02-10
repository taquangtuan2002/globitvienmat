package com.globits.emr.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.emr.domain.SystemConfig;

public class SystemConfigDto extends BaseObjectDto {
    private String configKey;
    private String configValue;
    private String note;// ghi chú

    public SystemConfigDto() {
        super();
    }

    public SystemConfigDto(SystemConfig entity) {
        super();
        if(entity != null) {
            this.id = entity.getId();
            this.configKey = entity.getConfigKey();
            this.configValue = entity.getConfigValue();
            this.note = entity.getNote();
        }
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
