package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.service.GeneralCRUDService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("GeneralCRUDService")
public abstract class GeneralCRUDServiceImpl<DOMAIN, DTO> implements GeneralCRUDService<DOMAIN, DTO> {

    @Autowired
    private JpaRepository<DOMAIN, Long> repository;

    private Logger LOGGER;

    @Override
    public DOMAIN create(DTO dtoObject) throws DatabaseException {
        try {
            final Optional<DOMAIN> domainObject = findExisting(dtoObject);
            if(!domainObject.isPresent()){
                DOMAIN domain = mapTo(dtoObject);
                return this.repository.save(domain);
            } else {
                LOGGER.warn(String.format("The object %s already exists into database", domainObject.toString()));
                throw new DatabaseException("The object already exists into database");
            }
        } catch(Exception ex) {
            LOGGER.warn(String.format("Couldn't add object to database due the error: %s", ex.getMessage()));
            if(ex instanceof DatabaseException) throw ex;
            else throw new DatabaseException("Some objects doesn't exist into database");
        }
    }

    @Override
    public void delete(Long id) throws DatabaseException {
        try {
            repository.deleteById(id);
        } catch (Exception exception){
            LOGGER.warn(String.format("Couldn't add object to database due the error: ", exception.getMessage()));
            throw new DatabaseException(String.format("The object %s not exists into database", id));
        }
    }

    @Override
    public List<DTO> findAll(String orderCriteria, Sort.Direction direction) {
        final List<DOMAIN> allDomainObjects = repository.findAll(Sort.by(direction, orderCriteria));
        final List<DTO> allDtoObjects = new ArrayList<DTO>();
        allDomainObjects.forEach(domain -> allDtoObjects.add(mapToDTO(domain)));
        return allDtoObjects;
    }

    @Override
    public Optional<DOMAIN> findById(Long id) {
        return this.repository.findById(id);
    }
}
