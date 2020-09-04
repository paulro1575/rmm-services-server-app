package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.DeviceTypeDTO;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.model.persistence.repository.DeviceTypeRepository;
import com.rmm.rmmservices.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOGGER = LoggerFactory.getLogger(DeviceTypeServiceImpl.class);

    @Override
    public DeviceTypeDTO update(Long id, DeviceTypeDTO dtoObject) throws Exception {
        Optional<DeviceType> optionalDeviceType = this.deviceTypeRepository.findById(id);
        if(optionalDeviceType.isPresent()){
            DeviceType deviceType = optionalDeviceType.get();
            deviceType.setTypeName(dtoObject.getTypeName());
            return mapToDTO(this.deviceTypeRepository.save(deviceType));
        } else {
            LOGGER.warn(String.format("Service %s not found into database",
                    dtoObject.toString()));
            throw new NoSuchElementException(String.format("Service: %S not found on the database",
                    dtoObject.getTypeName()));
        }
    }

    @Override
    public void deleteByCustomer(Long id, String username) throws DatabaseException {

    }

    @Override
    public List<DeviceTypeDTO> findAll(String customerName) {
        return null;
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
