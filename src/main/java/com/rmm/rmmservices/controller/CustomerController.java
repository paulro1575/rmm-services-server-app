package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.CustomerDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.service.GeneralCRUDService;
import com.rmm.rmmservices.utils.ResponseEntityHeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * @author Paul Rodríguez-Ch
 */
@RestController
@RequestMapping(value = "/customer/")
@Validated
public class CustomerController {

    @Autowired
    @Qualifier("customerServiceImpl")
    private GeneralCRUDService<Customer, CustomerDTO> customerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            path="/register")
    public ResponseEntity<Object> create(@Valid @RequestBody CustomerDTO customerDTO)
            throws DatabaseException, NoSuchElementException {
        customerDTO.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));
        final Customer customer = this.customerService.create(customerDTO);
        return new ResponseEntity<>(this.customerService.mapToDTO(customer),
                ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                HttpStatus.CREATED);
    }

}
