package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.CustomerDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.service.CustomerService;
import com.rmm.rmmservices.utils.MapperUtils;
import com.rmm.rmmservices.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService<Customer, CustomerDTO> {

    @Autowired
    private CustomerRepository customerRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerDeviceImpl.class);

    @Override
    public Customer create(CustomerDTO customerDTO) throws Exception {
        try {
            final Optional<Customer> customer = findExisting(customerDTO);
            if(!customer.isPresent()){
                return this.customerRepository.save(mapTo(customerDTO));
            } else {
                LOGGER.warn(String.format("Customer %s existing in the database",
                        customerDTO.getUsername()));
                throw new DatabaseException(String.format("Customer %s existing in the database",
                        customerDTO.getUsername()));
            }
        } catch(Exception ex) {
            LOGGER.warn(String.format("Couldn't add object to database due the error: %s", ex.getMessage()));
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dtoObject) throws DatabaseException {
        try {
            Optional<Customer> optionalCustomer = findById(id);
            if(optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                customer.setUsername(dtoObject.getUsername());
                customer.setPassword(dtoObject.getPassword());
                this.customerRepository.save(customer);
                return mapToDTO(customer);
            } else {
                LOGGER.warn(String.format("Customer: %S not found on the database",
                        dtoObject.getUsername()));
                throw new DatabaseException(String.format("Customer: %S not found on the database",
                        dtoObject.getUsername()));
            }
        } catch (Exception ex) {
            LOGGER.warn(String.format("Couldn't add object to database due the error: %s", ex.getMessage()));
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DatabaseException {
    // TODO
    }

    @Override
    public List<CustomerDTO> findAll(String orderCriteria, Sort.Direction direction) {
        // TODO
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public Customer mapTo(CustomerDTO dtoObject) {
        return MapperUtils.unmapCustomer(dtoObject);
    }

    @Override
    public CustomerDTO mapToDTO(Customer domainObject) {
        return MapperUtils.mapCustomer(domainObject);
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
            LOGGER.warn("Couldn't login cause customer password isn't correct");
        }
        LOGGER.warn(String.format("Couldn't login cause customer username is not correct: %s",
                customerDTO.toString()));
        return Optional.empty();
    }
}
