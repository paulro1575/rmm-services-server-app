package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.service.GeneralCRUDService;
import com.rmm.rmmservices.utils.ResponseEntityHeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(this.generalCRUDService.mapToDTO(domainObject),
                ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                HttpStatus.CREATED);
    }

    public ResponseEntity<Object> update(Long objectId,
                                         @Valid @RequestBody DTO objectDTO
    ) throws Exception {
        final DTO dtoResultObject = this.generalCRUDService.update(objectId, objectDTO);
        return new ResponseEntity<>(dtoResultObject,
                ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                HttpStatus.OK);
    }

    public ResponseEntity<Object> findAll(String orderCriteria, Sort.Direction direction) throws Exception {
        final List<DTO> customerServices = this.generalCRUDService.findAll(orderCriteria, direction);
        return new ResponseEntity<>(customerServices,
                ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                HttpStatus.OK);
    }

    public ResponseEntity<Object> findAll(String customerName) throws Exception {
        final List<DTO> customerServices = this.generalCRUDService.findAll(customerName);
        return new ResponseEntity<>(customerServices,
                ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                HttpStatus.OK);
    }

    public ResponseEntity<Object> delete(Long objectId) throws DatabaseException {
        this.generalCRUDService.delete(objectId);
        return new ResponseEntity<>("{ \"result\": \"Object was deleted successfully\"}",
                    ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                    HttpStatus.OK);
    }
}
