package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.service.GeneralCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Paul Rodríguez-Ch
 */
public class GeneralCrudController<DOMAIN, DTO> {

    @Autowired(required = true)
    private GeneralCRUDService<DOMAIN, DTO> generalCRUDService;

    public ResponseEntity<Object> create(@Valid @RequestBody DTO objectDto) throws Exception {
        final DOMAIN domainObject = this.generalCRUDService.create(objectDto);
        return new ResponseEntity<>(this.generalCRUDService.mapToDTO(domainObject), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> update(@PathVariable(name="service_id") Long objectId,
                                         @Valid @RequestBody DTO objectDTO
    ) throws Exception {
        final DTO dtoResultObject = this.generalCRUDService.update(objectId, objectDTO);
        return new ResponseEntity<>(dtoResultObject, HttpStatus.OK);
    }

    public ResponseEntity<Object> findAll(String orderCriteria, Sort.Direction direction) throws Exception {
        final List<DTO> customerServices = this.generalCRUDService.findAll(orderCriteria, direction);
        return new ResponseEntity<>(customerServices, HttpStatus.OK);
    }
}