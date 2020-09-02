package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Repository("customerDeviceRepository")
public interface CustomerDeviceRepository extends JpaRepository<Device, Long> {

    @Query(value = "SELECT d FROM device d WHERE d.customer_id = :customerId", nativeQuery = true)
    Optional<Device> listByCustomerId(Long customerId);

    @Query(value = "SELECT * FROM device d WHERE d.system_name = :systemName AND d.device_type_id = " +
            "(SELECT dt.id FROM device_type dt WHERE dt.type_name = :deviceTypeName) AND d.service_id = " +
            "(SELECT s.id FROM service s WHERE s.service_name = :serviceName) AND customer_id = :customerId",
            nativeQuery = true)
    Optional<Device> findExistingServiceIntoDevicePerCustomer(String systemName,
                                                              String deviceTypeName,
                                                              String serviceName,
                                                              Long customerId);
}
