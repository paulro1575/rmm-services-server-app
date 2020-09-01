package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import com.rmm.rmmservices.model.persistence.repository.CustomerServiceRepository;
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

    @Override
    public CustomerServiceDTO update(Long id, CustomerServiceDTO dtoObject) throws Exception {
        Optional<CustomerService> customerService = this.customerServiceRepository.findById(id);
        if(customerService.isPresent()){
            CustomerService service = customerService.get();
            service.setServiceName(dtoObject.getServiceName());
            return mapToDTO(this.customerServiceRepository.save(service));
        } else {
            throw new NoSuchElementException(String.format("Service: %S not found on the database",
                    dtoObject.getServiceName()));
        }
    }

    @Override
    public CustomerService mapTo(CustomerServiceDTO dtoObject) {
        CustomerService customerService = new CustomerService();
        if (dtoObject.getId() != null) customerService.setId(dtoObject.getId());
        customerService.setServiceName(dtoObject.getServiceName());
        return customerService;
    }

    @Override
    public CustomerServiceDTO mapToDTO(CustomerService domainObject) {
        CustomerServiceDTO customerServiceDTO = new CustomerServiceDTO();
        if(domainObject.getId() != null) customerServiceDTO.setId(domainObject.getId());
        customerServiceDTO.setServiceName(domainObject.getServiceName());
        return customerServiceDTO;
    }

    @Override
    public Optional<CustomerService> findExisting(CustomerServiceDTO dtoObject) {
        return customerServiceRepository.findByServiceName(dtoObject.getServiceName());
    }
}