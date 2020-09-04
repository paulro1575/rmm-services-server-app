package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.model.dto.DeviceDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.entities.Device;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
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
@RequestMapping(value = "/customer/device/")
@Validated
public class CustomerDeviceController extends GeneralCrudController<Device, DeviceDTO> {

    @Autowired
    @Qualifier("customerDeviceImpl")
    private GeneralCRUDService<Device, DeviceDTO> customerDeviceService;

    @Autowired
    private CustomerRepository customerRepository;


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            path="/")
    public ResponseEntity<Object> create(@Valid @RequestBody DeviceDTO deviceDTO,
                                         Authentication authentication) throws Exception {
        String username = CustomerCredentialsUtils.getUsernameFromToken(authentication.getName());
        Optional<Customer> customer = customerRepository.findByUsername(username);
        customer.ifPresent(value -> deviceDTO.setCustomerId(value.getId()));
        return super.create(deviceDTO);
    }


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT,
            path="/{customer_device_id}")
    public ResponseEntity<Object> update(
            @PathVariable(name="customer_device_id") Long customerDeviceId,
            @Valid @RequestBody DeviceDTO deviceDTO, Authentication authentication
    ) throws Exception {
        String username = CustomerCredentialsUtils.getUsernameFromToken(authentication.getName());
        Optional<Customer> customer = customerRepository.findByUsername(username);
        customer.ifPresent(value -> deviceDTO.setCustomerId(value.getId()));
        return super.update(customerDeviceId, deviceDTO);
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
            path = "/{customer_device_id}")
    public ResponseEntity<Object> delete(@PathVariable(name="customer_device_id") Long customerDeviceId,
                                         Authentication authentication) {
        String username = CustomerCredentialsUtils.getUsernameFromToken(authentication.getName());
        customerDeviceService.deleteByCustomer(customerDeviceId, username);
        return new ResponseEntity<>(ResponseEntityHeaderUtils.getJsonContentTypeHeaders(), HttpStatus.OK);
    }
}
