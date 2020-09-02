package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.ServicePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ServicePriceRepository extends JpaRepository<ServicePrice, Long> {
    @Query(value = "SELECT * FROM service_price sp WHERE sp.device_type_id = :deviceTypeId AND sp.service_id = :serviceId", nativeQuery = true)
    Optional<ServicePrice> findByDeviceTypeAndService(Long deviceTypeId, Long serviceId);
}
