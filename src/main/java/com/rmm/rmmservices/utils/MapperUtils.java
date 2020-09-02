package com.rmm.rmmservices.utils;

import com.rmm.rmmservices.model.dto.*;
import com.rmm.rmmservices.model.persistence.entities.*;

/**
 * This class helps to manage DOMAIN - DTO mappers
 * @author Paul Rodríguez-Ch
 */
public final class MapperUtils {

    /**
     * This method maps from Customer model to CustomerDTO
     * @param domainObject Customer object
     * @return CustomerDTO
     */
    public static CustomerDTO mapCustomer(Customer domainObject) {
        final CustomerDTO customerDto = new CustomerDTO();
        if (domainObject.getId() !=null) customerDto.setId(domainObject.getId());
        customerDto.setUsername(domainObject.getUsername());
        customerDto.setPassword(domainObject.getPassword());
        return customerDto;
    }

    /**
     * This method unmaps from CustomerDTO to Customer model
     * @param dtoObject CustomerDto object
     * @return Customer object
     */
    public static Customer unmapCustomer(CustomerDTO dtoObject) {
        final Customer customer = new Customer();
        if (dtoObject.getId() !=null) customer.setId(dtoObject.getId());
        customer.setUsername(dtoObject.getUsername());
        customer.setPassword(dtoObject.getPassword());
        return customer;
    }

    /**
     * This method maps from DeviceType to DeviceTypeDTO
     * @param deviceType DeviceType pbject
     * @return DeviceTypeDTO
     */
    public static DeviceTypeDTO mapDeviceType(DeviceType deviceType){
        DeviceTypeDTO deviceTypeDTO = new DeviceTypeDTO();
        if(deviceType.getId() != null) deviceTypeDTO.setId(deviceType.getId());
        deviceTypeDTO.setTypeName(deviceType.getTypeName());
        return deviceTypeDTO;
    }

    /**
     * This method unmaps from DeviceTypeDTo to DeviceType object
     * @param deviceTypeDTO DeviceTypeDTO
     * @return DeviceType object
     */
    public static DeviceType unmapDeviceType(DeviceTypeDTO deviceTypeDTO){
        DeviceType deviceType = new DeviceType();
        if(deviceTypeDTO.getId() != null) deviceType.setId(deviceTypeDTO.getId());
        deviceType.setTypeName(deviceTypeDTO.getTypeName());
        return deviceType;
    }

    /**
     * This method maps from CustomerService object to CustomerServiceDTO
     * @param customerService CustomerService object
     * @return CustomerServiceDTO
     */
    public static CustomerServiceDTO mapCustomerService(CustomerService customerService){
        CustomerServiceDTO customerServiceDTO = new CustomerServiceDTO();
        if(customerService.getId() != null) customerServiceDTO.setId(customerService.getId());
        customerServiceDTO.setServiceName(customerService.getServiceName());
        return customerServiceDTO;
    }

    /**
     * This method unmaps from CustomerServiceDTO to CustomerService object
     * @param customerServiceDTO CustomerServiceDTO
     * @return CustomerService object
     */
    public static CustomerService unmapCustomerService(CustomerServiceDTO customerServiceDTO){
        CustomerService customerService = new CustomerService();
        if(customerServiceDTO.getId() != null) customerService.setId(customerServiceDTO.getId());
        customerService.setServiceName(customerServiceDTO.getServiceName());
        return customerService;
    }

    /**
     * this method maps from ServicePrice object to ServicePriceDTO
     * @param servicePrice ServicePrice object
     * @return ServicePriceDTO
     */
    public static ServicePriceDTO mapServicePrice(ServicePrice servicePrice) {
        ServicePriceDTO servicePriceDTO = new ServicePriceDTO();
        if (servicePrice.getId() != null ) servicePriceDTO.setId(servicePrice.getId());
        servicePriceDTO.setDeviceTypeName(servicePrice.getDeviceType().getTypeName());
        servicePriceDTO.setCustomerServiceName(servicePrice.getCustomerService().getServiceName());
        servicePriceDTO.setPrice(servicePrice.getPrice());
        return servicePriceDTO;
    }

    /**
     * This method unmaps from ServicePriceDTO to ServicePrice object
     * @param servicePriceDTO ServicePriceDTO
     * @param deviceType DeviceType object
     * @param customerService customerService object
     * @return ServicePrice object
     */
    public static ServicePrice unmapServicePrice(ServicePriceDTO servicePriceDTO, DeviceType deviceType,
                                                 CustomerService customerService) {
        ServicePrice servicePrice = new ServicePrice();
        if (servicePriceDTO.getId() != null ) servicePrice.setId(servicePriceDTO.getId());
        servicePrice.setDeviceType(deviceType);
        servicePrice.setCustomerService(customerService);
        servicePrice.setPrice(servicePriceDTO.getPrice());
        return servicePrice;
    }

    /**
     * This method maps from Device object to DeviceDTO
     * @param device Device object
     * @return DeviceDTO
     */
    public static DeviceDTO mapCustomerDevice(Device device) {
        DeviceDTO deviceDTO = new DeviceDTO();
        if(device.getId() != null) deviceDTO.setId(device.getId());
        deviceDTO.setSystemName(device.getSystemName());
        deviceDTO.setDeviceTypeName(device.getDeviceType().getTypeName());
        deviceDTO.setServiceName(device.getCustomerService().getServiceName());
        deviceDTO.setCustomerId(device.getCustomer().getId());
        return  deviceDTO;
    }

    /**
     * This method unmaps from DeviceDTO to Device object
     * @param deviceDTO DeviceDTO
     * @param deviceType DeviceType object
     * @param customerService CustomerService object
     * @param customer Customer Object
     * @return Device object
     */
    public static Device unmapsCustomerDevice(DeviceDTO deviceDTO, DeviceType deviceType,
                                              CustomerService customerService, Customer customer){
        Device device = new Device();
        if(deviceDTO.getId() != null) device.setId(deviceDTO.getId());
        device.setSystemName(deviceDTO.getSystemName());
        device.setDeviceType(deviceType);
        device.setCustomerService(customerService);
        device.setCustomer(customer);
        return device;
    }
}
