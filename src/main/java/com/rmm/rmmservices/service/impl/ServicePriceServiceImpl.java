package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.ServicePriceDTO;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.model.persistence.entities.RmmService;
import com.rmm.rmmservices.model.persistence.entities.ServicePrice;
import com.rmm.rmmservices.model.persistence.repository.DeviceTypeRepository;
import com.rmm.rmmservices.model.persistence.repository.RmmServiceRepository;
import com.rmm.rmmservices.model.persistence.repository.ServicePriceRepository;
import com.rmm.rmmservices.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("servicePriceServiceImpl")
public class ServicePriceServiceImpl extends GeneralCRUDServiceImpl<ServicePrice, ServicePriceDTO> {

    @Autowired
    private ServicePriceRepository servicePriceRepository;
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;
    @Autowired
    private RmmServiceRepository rmmServiceRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ServicePriceServiceImpl.class);

    @Override
    public ServicePriceDTO update(Long id, ServicePriceDTO dtoObject) throws Exception {
        return null;
    }

    @Override
    public void deleteByCustomer(Long id, String username) throws DatabaseException {

    }

    @Override
    public List<ServicePriceDTO> findAll(String customerName) {
        return null;
    }

    @Override
    public ServicePrice mapTo(ServicePriceDTO dtoObject) {
        Optional<DeviceType> deviceType = this.deviceTypeRepository
                .findByDeviceTypeName(dtoObject.getDeviceTypeName());
        Optional<RmmService> rmmService = this.rmmServiceRepository
                .findByRmmServiceName(dtoObject.getRmmServiceName());
        if(deviceType.isPresent() && rmmService.isPresent()){
            return MapperUtils.unmapServicePrice(dtoObject, deviceType.get(), rmmService.get());
        }
        LOGGER.warn(String.format("ServicePrice %s some dependencies was not found",
                dtoObject.toString()));
        return null;
    }

    @Override
    public ServicePriceDTO mapToDTO(ServicePrice domainObject) {
        return MapperUtils.mapServicePrice(domainObject);
    }

    @Override
    public Optional<ServicePrice> findExisting(ServicePriceDTO dtoObject) {
        return this.servicePriceRepository.findByDeviceTypeAndService(dtoObject.getDeviceTypeName(),
                dtoObject.getRmmServiceName());
    }
}
