package com.rmm.rmmservices.service;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
public interface CustomerService<DOMAIN, DTO> extends GeneralCRUDService<DOMAIN, DTO>{
    Optional<DOMAIN> login(DTO dtoObject);
}
