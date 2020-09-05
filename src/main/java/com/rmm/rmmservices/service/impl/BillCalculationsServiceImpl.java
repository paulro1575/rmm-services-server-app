package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.repository.CustomerServiceRepository;
import com.rmm.rmmservices.service.BillCalculationsService;
import com.rmm.rmmservices.utils.BillCalculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("billCalculationsServiceImpl")
public class BillCalculationsServiceImpl implements BillCalculationsService {

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @Override
    public ResponseEntity<Object> getMonthlyBill(Optional<Customer> customer) {
        if(customer.isPresent()){
            List<Object[]> servicesPrices = this.customerServiceRepository.listBillServicesPrice(customer.get().getId());
            List<Object[]> devicesPrices = this.customerServiceRepository.listBillDevicesPrice(customer.get().getId());
            return BillCalculationUtils.getMonthlyBill(servicesPrices, devicesPrices);
        } else {
            throw new DatabaseException(String.format("Customer %s doesn't exists into database", customer.get().getId()));
        }
    }
}
