package com.globits.emr.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_system_config") // cấu hình hệ thống
public class SystemConfig extends BaseObject {
    @Column(name = "config_key")
    private String configKey;
    @Column(name = "config_value")
    private String configValue;
    @Column(name = "note")
    private String note;// ghi chú

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
