package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.model.dto.ServicePriceDTO;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.model.persistence.entities.ServicePrice;
import com.rmm.rmmservices.model.persistence.repository.CustomerServiceRepository;
import com.rmm.rmmservices.model.persistence.repository.DeviceTypeRepository;
import com.rmm.rmmservices.model.persistence.repository.ServicePriceRepository;
import com.rmm.rmmservices.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CustomerServiceRepository customerServiceRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ServicePriceServiceImpl.class);

    @Override
    public ServicePriceDTO update(Long id, ServicePriceDTO dtoObject) throws Exception {
        return null;
    }

    @Override
    public ServicePrice mapTo(ServicePriceDTO dtoObject) {
        Optional<DeviceType> deviceType = this.deviceTypeRepository
                .findByDeviceTypeName(dtoObject.getDeviceTypeName());
        Optional<CustomerService> customerService = this.customerServiceRepository
                .findByServiceName(dtoObject.getCustomerServiceName());
        if(deviceType.isPresent() && customerService.isPresent()){
            return MapperUtils.unmapServicePrice(dtoObject, deviceType.get(), customerService.get());
        }
        LOGGER.warn(String.format("ServicePrice %s some dependencies was not found into database",
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
                dtoObject.getCustomerServiceName());
    }
}
