package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.model.persistence.repository.CustomerServiceRepository;
import com.rmm.rmmservices.service.GeneralCRUDService;
import com.rmm.rmmservices.utils.CustomerCredentialsUtils;
import com.rmm.rmmservices.utils.ResponseEntityHeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@RestController
@RequestMapping(value = "/customer/service/")
@Validated
public class CustomerServiceController extends GeneralCrudController<CustomerService, CustomerServiceDTO>{

    @Autowired
    @Qualifier("customerServicesServiceImpl")
    private GeneralCRUDService<CustomerService, CustomerServiceDTO> customerServicesServiceImpl;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            path="/")
    public ResponseEntity<Object> create(@Valid @RequestBody CustomerServiceDTO customerServiceDTO,
                                         Authentication authentication
    ) throws Exception {
        String username = CustomerCredentialsUtils.getUsernameFromToken(authentication.getName());
        Optional<Customer> customer = customerRepository.findByUsername(username);
        customer.ifPresent(value -> customerServiceDTO.setCustomerId(value.getId()));
        return super.create(customerServiceDTO);
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            path="/")
    public ResponseEntity<Object> findAll(Authentication authentication) throws Exception {
        String username = CustomerCredentialsUtils.getUsernameFromToken(authentication.getName());
        return super.findAll(username);
    }


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.DELETE,
            path = "/{service_id}")
    public ResponseEntity<Object> delete(@PathVariable(name="service_id") Long serviceId,
                                         Authentication authentication) {
        String username = CustomerCredentialsUtils.getUsernameFromToken(authentication.getName());
        customerServicesServiceImpl.deleteByCustomer(serviceId, username);
        return new ResponseEntity<>(ResponseEntityHeaderUtils.getJsonContentTypeHeaders(), HttpStatus.OK);
    }
}
