package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.CustomerDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.service.CustomerService;
import com.rmm.rmmservices.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService<Customer, CustomerDTO> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(CustomerDTO customerDTO) throws Exception {
        try {
            final Optional<Customer> customer = findExisting(customerDTO);
            if(!customer.isPresent()){
                return this.customerRepository.save(mapTo(customerDTO));
            } else {
                throw new DatabaseException(String.format("Customer %s existing in the database",
                        customerDTO.getUsername()));
            }
        } catch(Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dtoObject) throws DatabaseException {
        try {
            final Customer customer = mapTo(findById(id));
            customer.setUsername(dtoObject.getUsername());
            customer.setPassword(dtoObject.getPassword());
            return mapToDTO(customer);
        } catch (Exception ex) {
            throw new DatabaseException(String.format("Database can't update the customer: %s", ex.getMessage()));
        }
    }

    @Override
    public CustomerDTO findById(Long id) throws Exception {
        final Optional<Customer> object = this.customerRepository.findById(id);
        if (object.isPresent()) {
            return mapToDTO(object.get());
        } else {
            throw new Exception(String.format("The customer %s not exists into database", id));
        }
    }

    @Override
    public Customer mapTo(CustomerDTO dtoObject) {
        System.out.println(dtoObject.getId());
        final Customer customer = new Customer();
        if (dtoObject.getId() !=null) customer.setId(dtoObject.getId());
        customer.setUsername(dtoObject.getUsername());
        customer.setPassword(dtoObject.getPassword());
        return customer;
    }

    @Override
    public CustomerDTO mapToDTO(Customer domainObject) {
        final CustomerDTO customerDto = new CustomerDTO();
        if (domainObject.getId() !=null) customerDto.setId(domainObject.getId());
        customerDto.setUsername(domainObject.getUsername());
        customerDto.setPassword(domainObject.getPassword());
        return customerDto;
    }

    @Override
    public Optional<Customer> findExisting(CustomerDTO dtoObject) {
        return this.customerRepository.findByUsername(dtoObject.getUsername());
    }

    @Override
    public Optional<Customer> login(CustomerDTO customerDTO) {
        Optional<Customer> customer = this.customerRepository.findByUsername(customerDTO.getUsername());
        if(customer.isPresent()) {
            if(PasswordUtils.verifyPasswordMatch(customerDTO.getPassword(), customer.get().getPassword())){
                return customer;
            }
        }
        return Optional.empty();
    }
}
