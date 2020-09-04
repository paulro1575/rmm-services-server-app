package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.model.dto.RmmServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.RmmService;
import com.rmm.rmmservices.service.GeneralCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Paul Rodríguez-Ch
 */
@RestController
@RequestMapping(value = "/service/")
@Validated
public class RmmServiceController extends GeneralCrudController<RmmService, RmmServiceDTO> {


    @Autowired
    @Qualifier("rmmServicesServiceImpl")
    private GeneralCRUDService<RmmService, RmmServiceDTO> rmmServicesServiceImpl;


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            path="/")
    public ResponseEntity<Object> create(@Valid @RequestBody RmmServiceDTO rmmServiceDTO) throws Exception {
        return super.create(rmmServiceDTO);
    }


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT,
            path="/{service_id}")
    public ResponseEntity<Object> update(
            @PathVariable(name="service_id") Long rmmServiceId,
            @Valid @RequestBody RmmServiceDTO rmmServiceDTO
    ) throws Exception {
        return super.update(rmmServiceId, rmmServiceDTO);
    }


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            path="/")
    public ResponseEntity<Object> findAll() throws Exception {
        return super.findAll("id", Sort.Direction.ASC);
    }


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.DELETE,
            path = "/{service_id}")
    public ResponseEntity<Object> delete(@PathVariable(name="service_id") Long objectId) {
        return super.delete(objectId);
    }
}
