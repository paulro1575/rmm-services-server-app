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


    @Override
    public CustomerServiceDTO update(Long id, CustomerServiceDTO dtoObject) throws DatabaseException,
            NoSuchElementException {
        try {
            final Optional<CustomerService> optionalCustomerService = this.customerServiceRepository
                    .findCustomerServicePerIdAndCustomerId(id, dtoObject.getCustomerId());
            if (optionalCustomerService.isPresent()) {
                CustomerService customerService = mapTo(dtoObject);
                Optional<CustomerService> customerServiceToUpdate = findExisting(dtoObject);
                if (!customerServiceToUpdate.isPresent()) {
                    customerService.setId(id);
                    return mapToDTO(this.customerServiceRepository.save(customerService));
                } else {
                    throw new DatabaseException("The object to be updated already exists into database");
                }
            } else {
                throw new NoSuchElementException(String.format("Service %S not found on the database",
                        dtoObject.getServiceName()));
            }
        } catch(Exception ex) {
            if(ex instanceof DatabaseException) throw ex;
            else throw new NoSuchElementException("Some objects doesn't exist into database");
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
                throw new NoSuchElementException(String.format(
                        "The Customer Service %s doesn't exists into database", id));
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
        return null;
    }


    @Override
    public CustomerServiceDTO mapToDTO(CustomerService domainObject) {
        return MapperUtils.mapCustomerService(domainObject);
    }


    @Override
    public Optional<CustomerService> findExisting(CustomerServiceDTO dtoObject) {
        return customerServiceRepository.findByCustomerIdAndServiceName(dtoObject.getCustomerId(),
                dtoObject.getServiceName());
    }
}
