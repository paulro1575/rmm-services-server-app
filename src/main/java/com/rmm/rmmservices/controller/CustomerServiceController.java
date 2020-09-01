package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import com.rmm.rmmservices.service.GeneralCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
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
public class CustomerServiceController extends GeneralCrudController<CustomerService, CustomerServiceDTO> {

    @Autowired
    @Qualifier("customerServicesServiceImpl")
    private GeneralCRUDService<CustomerService, CustomerServiceDTO> customerServicesService;

    @RequestMapping(method = RequestMethod.POST, path="/")
    public ResponseEntity<Object> create(@Valid @RequestBody CustomerServiceDTO serviceDTO) throws Exception {
        return super.create(serviceDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, path="/{service_id}")
    public ResponseEntity<Object> create(
            @PathVariable(name="service_id") Long customerServiceId,
            @Valid @RequestBody CustomerServiceDTO serviceDTO
    ) throws Exception {
        return super.update(customerServiceId, serviceDTO);
    }

    @RequestMapping(method = RequestMethod.GET, path="/")
    public ResponseEntity<Object> findAll() throws Exception {
        return super.findAll("id", Sort.Direction.ASC);
    }
}
