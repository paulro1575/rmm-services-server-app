package com.rmm.rmmservices.service;

import com.rmm.rmmservices.model.persistence.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
public interface BillCalculationsService {
    ResponseEntity<Object> getMonthlyBill(Optional<Customer> customer);
}
