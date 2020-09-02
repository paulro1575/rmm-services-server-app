package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.model.dto.ServicePriceDTO;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.model.persistence.entities.ServicePrice;
import com.rmm.rmmservices.model.persistence.repository.CustomerServiceRepository;
import com.rmm.rmmservices.model.persistence.repository.DeviceTypeRepository;
import com.rmm.rmmservices.model.persistence.repository.ServicePriceRepository;
import com.rmm.rmmservices.utils.MapperUtils;
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
        } else{
            return null;
        }
    }

    @Override
    public ServicePriceDTO mapToDTO(ServicePrice domainObject) {
        return MapperUtils.mapServicePrice(domainObject);
    }

    @Override
    public Optional<ServicePrice> findExisting(ServicePriceDTO dtoObject) {
        System.out.println("SHEGA");
        Optional<DeviceType> deviceType = this.deviceTypeRepository
                .findByDeviceTypeName(dtoObject.getDeviceTypeName());
        System.out.println("PASA: " + dtoObject.getDeviceTypeName());
        Optional<CustomerService> customerService = this.customerServiceRepository
                .findByServiceName(dtoObject.getCustomerServiceName());
        System.out.println("ACABA " + dtoObject.getCustomerServiceName());
        if (deviceType.isPresent() && customerService.isPresent()) {
            System.out.println("HASTA ABAJO " + dtoObject.getPrice());
            return this.servicePriceRepository.findByDeviceTypeAndService(
                    deviceType.get().getId(),
                    customerService.get().getId());
        } else {
            System.out.println("ELSE");
            return Optional.empty();
        }
    }
}
