package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.model.dto.CustomerDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.service.CustomerService;
import com.rmm.rmmservices.utils.PasswordUtils;
import com.rmm.rmmservices.utils.ResponseEntityHeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@RestController
@RequestMapping(value = "/customer/")
@Validated
public class CustomerController {

    @Autowired
    @Qualifier("customerServiceImpl")
    private CustomerService<Customer, CustomerDTO> customerService;

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            path="/register")
    public ResponseEntity<Object> create(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
        customerDTO.setPassword(PasswordUtils.getPasswordHash(customerDTO.getPassword()));
        final Customer customer = this.customerService.create(customerDTO);
        return new ResponseEntity<>(this.customerService.mapToDTO(customer),
                ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                HttpStatus.CREATED);
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            path="/login")
    public ResponseEntity<Object> login(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
        Optional<Customer> customer = this.customerService.login(customerDTO);
        return customer.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(this.customerService.mapToDTO(value),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
