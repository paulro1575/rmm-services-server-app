package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.service.BillCalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            path="/{customer_id}")
    public ResponseEntity<Object> calculate(@PathVariable(name="customer_id") Long customerId) throws Exception {
        return this.billCalculationsService.getMonthlyBill(customerId);
    }
}
