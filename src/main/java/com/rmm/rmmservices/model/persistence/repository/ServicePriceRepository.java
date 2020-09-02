package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.ServicePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Repository("servicePriceRepository")
public interface ServicePriceRepository extends JpaRepository<ServicePrice, Long> {
    @Query(value = "SELECT * FROM service_price sp WHERE sp.device_type_id = (SELECT dt.id FROM device_type dt WHERE " +
            "dt.type_name = :deviceTypeName) AND sp.service_id = (SELECT s.id FROM service s WHERE s.service_name " +
            "= :serviceName)", nativeQuery = true)
    Optional<ServicePrice> findByDeviceTypeAndService(String deviceTypeName, String serviceName);
}
