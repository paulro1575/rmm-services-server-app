package com.rmm.rmmservices.service;

import com.rmm.rmmservices.exceptions.DatabaseException;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
public interface GeneralCRUDService<DOMAIN, DTO> {
    DOMAIN create(DTO dtoObject) throws Exception;
    DTO update(Long id, DTO dtoObject) throws Exception;
    void delete(Long id) throws DatabaseException;
    List<DTO> findAll(String orderCriteria, Sort.Direction direction);
    DTO findById(Long id) throws Exception;
    DOMAIN mapTo(DTO dtoObject);
    DTO mapToDTO(DOMAIN domainObject);
    Optional<DOMAIN> findExisting(DTO dtoObject);
}

