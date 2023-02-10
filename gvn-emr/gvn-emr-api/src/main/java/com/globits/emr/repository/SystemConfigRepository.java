package com.globits.emr.repository;

import com.globits.emr.domain.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, UUID> {
    @Query("SELECT entity FROM SystemConfig entity WHERE entity.configKey = ?1")
    List<SystemConfig> getByConfigKey(String configKey);
}
