package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.service.GeneralCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("GeneralCRUDService")
public abstract class GeneralCRUDServiceImpl<DOMAIN, DTO> implements GeneralCRUDService<DOMAIN, DTO> {

    @Autowired
    private JpaRepository<DOMAIN, Long> repository;


    @Override
    public DOMAIN create(DTO dtoObject) throws DatabaseException, NoSuchElementException {
        try {
            final Optional<DOMAIN> domainObject = findExisting(dtoObject);
            if(!domainObject.isPresent()){
                DOMAIN domain = mapTo(dtoObject);
                return this.repository.save(domain);
            } else {
                throw new DatabaseException("The object already exists into database");
            }
        } catch(Exception ex) {
            if(ex instanceof DatabaseException) throw ex;
            else throw new NoSuchElementException("Some objects doesn't exist into database");
        }
    }


    @Override
    public void delete(Long id) throws NoSuchElementException {
        try {
            repository.deleteById(id);
        } catch (Exception exception){
            throw new NoSuchElementException(String.format("The object %s was not found into database", id));
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
