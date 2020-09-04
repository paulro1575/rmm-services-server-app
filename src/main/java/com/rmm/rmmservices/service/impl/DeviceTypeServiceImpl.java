package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.DeviceTypeDTO;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.model.persistence.repository.DeviceTypeRepository;
import com.rmm.rmmservices.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("deviceTypeServiceImpl")
public class DeviceTypeServiceImpl extends GeneralCRUDServiceImpl<DeviceType, DeviceTypeDTO>{

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;


    @Override
    public DeviceTypeDTO update(Long id, DeviceTypeDTO deviceTypeDto) throws DatabaseException, NoSuchElementException {
        Optional<DeviceType> actualDeviceType = this.deviceTypeRepository.findById(id);
        if(actualDeviceType.isPresent()){
            Optional<DeviceType> deviceTypeToUpdate = this.deviceTypeRepository
                    .findByDeviceTypeName(deviceTypeDto.getTypeName());
            if(!deviceTypeToUpdate.isPresent()){
                DeviceType deviceType = actualDeviceType.get();
                deviceType.setTypeName(deviceTypeDto.getTypeName());
                return mapToDTO(this.deviceTypeRepository.save(deviceType));
            } else {
                throw new DatabaseException(String.format("The device type %s already exists into database",
                        deviceTypeDto.getTypeName()));
            }
        } else {
            throw new NoSuchElementException(String.format("Device Type %S was not found into database",
                    deviceTypeDto.getTypeName()));
        }
    }


    @Override
    public void deleteByCustomer(Long id, String username) throws DatabaseException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }


    @Override
    public List<DeviceTypeDTO> findAll(String customerName) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Unsupported Operation");
    }


    @Override
    public DeviceType mapTo(DeviceTypeDTO dtoObject) {
        return MapperUtils.unmapDeviceType(dtoObject);
    }


    @Override
    public DeviceTypeDTO mapToDTO(DeviceType domainObject) {
        return MapperUtils.mapDeviceType(domainObject);
    }


    @Override
    public Optional<DeviceType> findExisting(DeviceTypeDTO dtoObject) {
        return deviceTypeRepository.findByDeviceTypeName(dtoObject.getTypeName());
    }
}
