package com.rmm.rmmservices.model.persistence.repository;

import com.rmm.rmmservices.model.persistence.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from customer c where c.username = ?1")
    Optional<Customer> findByUsername(String username);
}
