package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.model.dto.DeviceTypeDTO;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.model.persistence.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DeviceTypeDTO update(Long id, DeviceTypeDTO dtoObject) throws Exception {
        Optional<DeviceType> optionalDeviceType = this.deviceTypeRepository.findById(id);
        if(optionalDeviceType.isPresent()){
            DeviceType deviceType = optionalDeviceType.get();
            deviceType.setTypeName(dtoObject.getTypeName());
            return mapToDTO(this.deviceTypeRepository.save(deviceType));
        } else {
            throw new NoSuchElementException(String.format("Service: %S not found on the database",
                    dtoObject.getTypeName()));
        }
    }

    @Override
    public DeviceType mapTo(DeviceTypeDTO dtoObject) {
        DeviceType deviceType = new DeviceType();
        if (dtoObject.getId() != null) deviceType.setId(dtoObject.getId());
        deviceType.setTypeName(dtoObject.getTypeName());
        return deviceType;
    }

    @Override
    public DeviceTypeDTO mapToDTO(DeviceType domainObject) {
        DeviceTypeDTO deviceTypeDTO = new DeviceTypeDTO();
        if (domainObject.getId() != null) deviceTypeDTO.setId(domainObject.getId());
        deviceTypeDTO.setTypeName(domainObject.getTypeName());
        return deviceTypeDTO;
    }

    @Override
    public Optional<DeviceType> findExisting(DeviceTypeDTO dtoObject) {
        return deviceTypeRepository.findByDeviceTypeName(dtoObject.getTypeName());
    }
}
