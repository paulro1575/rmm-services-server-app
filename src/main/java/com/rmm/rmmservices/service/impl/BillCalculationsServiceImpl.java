package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.model.persistence.repository.CustomerServiceRepository;
import com.rmm.rmmservices.service.BillCalculationsService;
import com.rmm.rmmservices.utils.BillCalculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("billCalculationsServiceImpl")
public class BillCalculationsServiceImpl implements BillCalculationsService {

    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Object> getMonthlyBill(Long customerId) {
        if(this.customerRepository.findById(customerId).isPresent()){
            List<Object[]> servicesPrices = this.customerServiceRepository.listBillServicesPrice(customerId);
            List<Object[]> devicesPrices = this.customerServiceRepository.listBillDevicesPrice(customerId);
            return BillCalculationUtils.getMonthlyBill(servicesPrices, devicesPrices);
        } else {
            throw new DatabaseException(String.format("Customer %s doesn't exists into database", customerId));
        }
    }
}
