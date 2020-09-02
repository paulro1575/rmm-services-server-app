package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import com.rmm.rmmservices.model.persistence.repository.CustomerServiceRepository;
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
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerServicesServiceImpl.class);

    @Override
    public CustomerServiceDTO update(Long id, CustomerServiceDTO dtoObject) throws Exception {
        Optional<CustomerService> customerService = this.customerServiceRepository.findById(id);
        if(customerService.isPresent()){
            CustomerService service = customerService.get();
            service.setServiceName(dtoObject.getServiceName());
            return mapToDTO(this.customerServiceRepository.save(service));
        } else {
            LOGGER.warn(String.format("Service %s not found into database",
                    dtoObject.toString()));
            throw new NoSuchElementException(String.format("Service: %S not found on the database",
                    dtoObject.getServiceName()));
        }
    }

    @Override
    public CustomerService mapTo(CustomerServiceDTO dtoObject) {
        return MapperUtils.unmapCustomerService(dtoObject);
    }

    @Override
    public CustomerServiceDTO mapToDTO(CustomerService domainObject) {
        return MapperUtils.mapCustomerService(domainObject);
    }

    @Override
    public Optional<CustomerService> findExisting(CustomerServiceDTO dtoObject) {
        return customerServiceRepository.findByServiceName(dtoObject.getServiceName());
    }
}
