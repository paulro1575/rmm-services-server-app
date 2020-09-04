package com.rmm.rmmservices.service;

import com.rmm.rmmservices.exceptions.DatabaseException;

import java.util.List;
import java.util.Optional;

public interface GeneralCRUDCustomerFilteredService<DOMAIN, DTO>{
    DOMAIN create(DTO dtoObject) throws Exception;
    DTO update(Long id, DTO dtoObject) throws Exception;
    void deleteByCustomer(Long id, String username) throws DatabaseException;
    List<DTO> findAll(String customerName);
    Optional<DOMAIN> findByIdAndCustomer(Long id);
    DOMAIN mapTo(DTO dtoObject);
    DTO mapToDTO(DOMAIN domainObject);
    Optional<DOMAIN> findExistingPerCustomer(DTO dtoObject);
}
