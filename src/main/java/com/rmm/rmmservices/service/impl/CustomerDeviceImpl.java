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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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


    @Override
    public DeviceDTO update(Long id, DeviceDTO deviceDTO)
            throws DatabaseException, NoSuchElementException {
        try {
            final Optional<Device> optionalActualDevice = this.customerDeviceRepository.findDevicePerIdAndCustomerId(id,
                    deviceDTO.getCustomerId());
            if(optionalActualDevice.isPresent()){
                Device device = mapTo(deviceDTO);
                Optional<Device> optionalDeviceToUpdate = findExisting(deviceDTO);
                if(!optionalDeviceToUpdate.isPresent()){
                    device.setId(id);
                    return mapToDTO(this.customerDeviceRepository.save(device));
                }else{
                    throw new DatabaseException("The new object properties already exists into database");
                }
            } else {
                throw new NoSuchElementException("The object doesn't exist into database");
            }
        } catch(Exception ex) {
            if(ex instanceof DatabaseException) throw ex;
            else throw new NoSuchElementException("Some objects doesn't exist into database");
        }
    }


    @Override
    public void deleteByCustomer(Long id, String username) throws DatabaseException, NoSuchElementException {
        try {
            Optional<Device> customerDevice = customerDeviceRepository.findByDeviceIdAndCustomerName(id, username);
            if(customerDevice.isPresent()){
                this.customerDeviceRepository.deleteByDeviceIdAndCustomer(id, username);
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
    public List<DeviceDTO> findAll(String customerName) {
        final List<Device> allDevices = this.customerDeviceRepository.findDevicesPerCustomerName(customerName);
        final List<DeviceDTO> allDevicesDTO = new ArrayList<>();
        allDevices.forEach(domain -> allDevicesDTO.add(mapToDTO(domain)));
        return allDevicesDTO;
    }


    @Override
    public Device mapTo(DeviceDTO deviceDTO) {
        Optional<DeviceType> deviceType = this.deviceTypeRepository
                .findByDeviceTypeName(deviceDTO.getDeviceTypeName());
        Optional<Customer>customer = this.customerRepository.findById(deviceDTO.getCustomerId());
        if(deviceType.isPresent() && customer.isPresent()){
            return MapperUtils.unmapsCustomerDevice(deviceDTO, deviceType.get(),customer.get());
        }
        return null;
    }


    @Override
    public DeviceDTO mapToDTO(Device device) {
        return MapperUtils.mapCustomerDevice(device);
    }

    
    @Override
    public Optional<Device> findExisting(DeviceDTO deviceDTO) {
        return this.customerDeviceRepository.findExistingDevicePerCustomer(deviceDTO.getSystemName(),
                deviceDTO.getDeviceTypeName(),
                deviceDTO.getCustomerId());
    }
}
