package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.service.BillCalculationsService;
import com.rmm.rmmservices.utils.CustomerCredentialsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@RestController
@RequestMapping(value = "/customer/service/bill/")
@Validated
public class BillCalculationsController {

    @Autowired
    @Qualifier("billCalculationsServiceImpl")
    private BillCalculationsService billCalculationsService;
    @Autowired
    private CustomerRepository customerRepository;


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            path="/")
    public ResponseEntity<Object> calculate(Authentication authentication) throws Exception {
        String username = CustomerCredentialsUtils.getUsernameFromToken(authentication.getName());
        Optional<Customer> customer = customerRepository.findByUsername(username);
        return this.billCalculationsService.getMonthlyBill(customer);
    }
}
