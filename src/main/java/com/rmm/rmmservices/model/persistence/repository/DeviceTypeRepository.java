package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {

    @Query(value = "SELECT * FROM device_type dt WHERE dt.type_name = :typeName", nativeQuery = true)
    Optional<DeviceType> findByDeviceTypeName(String typeName);
}
