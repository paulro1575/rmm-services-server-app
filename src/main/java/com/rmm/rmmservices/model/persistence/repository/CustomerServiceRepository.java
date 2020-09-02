package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Repository("CustomerServiceRepository")
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {

    @Query(value = "SELECT * FROM service s WHERE s.service_name = :serviceName", nativeQuery = true)
    Optional<CustomerService> findByServiceName(String serviceName);
}
