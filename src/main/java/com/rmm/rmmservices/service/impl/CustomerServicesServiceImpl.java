package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import com.rmm.rmmservices.model.persistence.entities.RmmService;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.model.persistence.repository.CustomerServiceRepository;
import com.rmm.rmmservices.model.persistence.repository.RmmServiceRepository;
import com.rmm.rmmservices.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public CustomerServiceDTO update(Long id, CustomerServiceDTO dtoObject) throws DatabaseException,
            NoSuchElementException {
        try {
            final Optional<CustomerService> optionalCustomerService = this.customerServiceRepository
                    .findCustomerServicePerIdAndCustomerId(id, dtoObject.getCustomerId());
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
    public void deleteByCustomer(Long id, String username) throws DatabaseException, NoSuchElementException {
        try {
            Optional<CustomerService> customerService = customerServiceRepository
                    .findByServiceIdAndCustomerName(id, username);
            if(customerService.isPresent()){
                this.customerServiceRepository.deleteByServiceIdAndCustomer(id, username);
            } else{
                throw new NoSuchElementException(String.format("The object %s doesn't exists into database", id));
            }
        } catch (Exception exception){
            if (exception instanceof NoSuchElementException) throw exception;
            else throw new DatabaseException(String.format("Couldn't add object to database due the error: %s",
                            exception.getMessage()));
        }
    }


    @Override
    public List<CustomerServiceDTO> findAll(String customerName) {
        final List<CustomerService> allCustomerServices = this.customerServiceRepository
                .findCustomerServicesPerCustomerName(customerName);
        final List<CustomerServiceDTO> allCustomerServicesDTO = new ArrayList<>();
        allCustomerServices.forEach(domain -> allCustomerServicesDTO.add(mapToDTO(domain)));
        return allCustomerServicesDTO;
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
