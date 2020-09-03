package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.model.dto.ServicePriceDTO;
import com.rmm.rmmservices.model.persistence.entities.ServicePrice;
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
@RequestMapping(value = "/service/price/")
@Validated
public class ServicePriceController extends GeneralCrudController<ServicePrice, ServicePriceDTO>{

    @Autowired
    @Qualifier("servicePriceServiceImpl")
    private GeneralCRUDService<ServicePrice, ServicePriceDTO> servicePriceService;

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            path="/")
    public ResponseEntity<Object> create(@Valid @RequestBody ServicePriceDTO servicePriceDTO) throws Exception {
        return super.create(servicePriceDTO);
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            path="/")
    public ResponseEntity<Object> findAll() throws Exception {
        return super.findAll("id", Sort.Direction.ASC);
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.DELETE,
            path = "/{service_price_id}")
    public ResponseEntity<Object> delete(@PathVariable(name="service_price_id") Long objectId) {
        return super.delete(objectId);
    }
}
