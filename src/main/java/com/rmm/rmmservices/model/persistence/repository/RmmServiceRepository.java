package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.RmmService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
public interface RmmServiceRepository extends JpaRepository<RmmService, Long> {

    @Query(value = "SELECT * FROM service s WHERE s.service_name = :serviceName", nativeQuery = true)
    Optional<RmmService> findByRmmServiceName(String serviceName);
}
