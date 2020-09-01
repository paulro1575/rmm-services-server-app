package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.service.GeneralCRUDService;
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

    @Override
    public DOMAIN create(DTO dtoObject) throws Exception {
        try {
            final Optional<DOMAIN> domainObject = findExisting(dtoObject);
            if(!domainObject.isPresent()){
                DOMAIN domain = mapTo(dtoObject);
                System.out.println(domain);
                return this.repository.save(domain);
            } else {
                throw new DatabaseException(String.format("Object %s existing in the database", dtoObject.toString()));
            }
        } catch(Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception exception){
            throw new Exception(String.format("The object %s not exists into database", id));
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
    public DTO findById(Long id) throws Exception {
        final Optional<DOMAIN> object = repository.findById(id);
        if (object.isPresent()) {
            return mapToDTO(object.get());
        } else {
            throw new Exception(String.format("The object %s not exists into database", id));
        }
    }
}
