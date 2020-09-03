package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.*;
import com.rmm.rmmservices.model.persistence.repository.*;
import com.rmm.rmmservices.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("customerServicesServiceImpl")
public class CustomerServicesServiceImpl extends GeneralCRUDServiceImpl<CustomerService, CustomerServiceDTO> {

    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RmmServiceRepository rmmServiceRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerServicesServiceImpl.class);

    @Override
    public CustomerServiceDTO update(Long id, CustomerServiceDTO dtoObject) throws Exception {
        try {
            final Optional<CustomerService> optionalCustomerService = this.customerServiceRepository.findById(id);
            if (optionalCustomerService.isPresent()) {
                CustomerService customerService = mapTo(dtoObject);
                Optional<CustomerService> newCustomerService = findExisting(dtoObject);
                if (!newCustomerService.isPresent()) {
                    customerService.setId(id);
                    return mapToDTO(this.customerServiceRepository.save(customerService));
                } else {
                    LOGGER.warn(String.format("The new object %s already exists into database",
                            newCustomerService.toString()));
                    throw new DatabaseException("The new object properties already exists into database");
                }
            } else {
                LOGGER.warn(String.format("Service %s not found into database",
                        dtoObject.toString()));
                throw new NoSuchElementException(String.format("Service: %S not found on the database",
                        dtoObject.getServiceName()));
            }
        } catch(Exception ex) {
            LOGGER.warn(String.format("Couldn't add object to database due the error: %s", ex.getMessage()));
            if(ex instanceof DatabaseException) throw ex;
            else throw new DatabaseException("Some objects doesn't exist into database");
        }
    }

    @Override
    public CustomerService mapTo(CustomerServiceDTO dtoObject) {
        Optional<RmmService> rmmService = this.rmmServiceRepository.findByRmmServiceName(dtoObject.getServiceName());
        Optional<Customer>customer = this.customerRepository.findById(dtoObject.getCustomerId());
        if(rmmService.isPresent() && customer.isPresent()){
            return MapperUtils.unmapCustomerService(dtoObject, customer.get(), rmmService.get());
        }
        LOGGER.warn(String.format("The next object has non existing dependencies into database: %s", dtoObject));
        return null;
    }

    @Override
    public CustomerServiceDTO mapToDTO(CustomerService domainObject) {
        return MapperUtils.mapCustomerService(domainObject);
    }

    @Override
    public Optional<CustomerService> findExisting(CustomerServiceDTO dtoObject) {
        return customerServiceRepository.findByCustomerIdServiceNameA(dtoObject.getCustomerId(),
                dtoObject.getServiceName());
    }
}
