package com.rmm.rmmservices.service;

import org.springframework.http.ResponseEntity;

/**
 * @author Paul Rodríguez-Ch
 */
public interface BillCalculationsService {
    ResponseEntity<Object> getMonthlyBill(Long customerId);
}
