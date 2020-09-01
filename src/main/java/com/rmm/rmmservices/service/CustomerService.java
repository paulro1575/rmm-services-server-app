package com.rmm.rmmservices.service;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
public interface CustomerService<DOMAIN, DTO>{
    DOMAIN create(DTO dtoObject) throws Exception;
    DTO update(Long id, DTO dtoObject) throws Exception;
    DTO findById(Long id) throws Exception;
    DOMAIN mapTo(DTO dtoObject);
    DTO mapToDTO(DOMAIN domainObject);
    Optional<DOMAIN> findExisting(DTO dtoObject);
    Optional<DOMAIN> login(DTO dtoObject);
}
