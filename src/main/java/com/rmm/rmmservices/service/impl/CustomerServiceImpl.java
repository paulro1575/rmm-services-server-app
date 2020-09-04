package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.CustomerDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.service.GeneralCRUDService;
import com.rmm.rmmservices.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements GeneralCRUDService<Customer, CustomerDTO> {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer create(CustomerDTO customerDTO) throws DatabaseException, NoSuchElementException {
        try {
            final Optional<Customer> customer = findExisting(customerDTO);
            if(!customer.isPresent()){
                return this.customerRepository.save(mapTo(customerDTO));
            } else {
                throw new DatabaseException(String.format("Customer %s already exist into database",
                        customerDTO.getUsername()));
            }
        } catch(Exception ex) {
            if(ex instanceof DatabaseException) throw ex;
            throw new NoSuchElementException(ex.getMessage());
        }
    }


    @Override
    public CustomerDTO update(Long id, CustomerDTO dtoObject) throws DatabaseException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }


    @Override
    public void delete(Long id) throws DatabaseException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }


    @Override
    public void deleteByCustomer(Long id, String username) throws DatabaseException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }


    @Override
    public List<CustomerDTO> findAll(String orderCriteria, Sort.Direction direction)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }


    @Override
    public List<CustomerDTO> findAll(String customerName) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported Operation");
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
}
