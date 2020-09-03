package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.DeviceDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.entities.Device;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.model.persistence.repository.CustomerDeviceRepository;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.model.persistence.repository.DeviceTypeRepository;
import com.rmm.rmmservices.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("customerDeviceImpl")
public class CustomerDeviceImpl extends GeneralCRUDServiceImpl<Device, DeviceDTO>{

    @Autowired
    private CustomerDeviceRepository customerDeviceRepository;
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerDeviceImpl.class);

    @Override
    public DeviceDTO update(Long id, DeviceDTO dtoObject) throws DatabaseException {
        try {
            final Optional<Device> optionalDevice = findById(id);
            if(optionalDevice.isPresent()){
                Device device = mapTo(dtoObject);
                Optional<Device> newDevice = findExisting(dtoObject);
                if(!newDevice.isPresent()){
                    device.setId(id);
                    return mapToDTO(this.customerDeviceRepository.save(device));
                }else{
                    LOGGER.warn(String.format("The new object %s already exists into database", newDevice.toString()));
                    throw new DatabaseException("The new object properties already exists into database");
                }
            } else {
                LOGGER.warn(String.format("The object %s doesn't exists into database", dtoObject.toString()));
                throw new DatabaseException("The object doesn't exist into database");
            }
        } catch(Exception ex) {
            LOGGER.warn(String.format("Couldn't add object to database due the error: %s", ex.getMessage()));
            if(ex instanceof DatabaseException) throw ex;
            else throw new DatabaseException("Some objects doesn't exist into database");
        }
    }


    @Override
    public Device mapTo(DeviceDTO dtoObject) {
        Optional<DeviceType> deviceType = this.deviceTypeRepository
                .findByDeviceTypeName(dtoObject.getDeviceTypeName());
        Optional<Customer>customer = this.customerRepository.findById(dtoObject.getCustomerId());
        if(deviceType.isPresent() && customer.isPresent()){
            return MapperUtils.unmapsCustomerDevice(dtoObject, deviceType.get(),customer.get());
        }
        LOGGER.warn(String.format("The next object has non existing dependencies into database: %s", dtoObject));
        return null;
    }


    @Override
    public DeviceDTO mapToDTO(Device domainObject) {
        return MapperUtils.mapCustomerDevice(domainObject);
    }

    
    @Override
    public Optional<Device> findExisting(DeviceDTO dtoObject) {
        return this.customerDeviceRepository.findExistingDevicePerCustomer(dtoObject.getSystemName(),
                dtoObject.getDeviceTypeName(),
                dtoObject.getCustomerId());
    }
}
