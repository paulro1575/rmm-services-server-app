package com.rmm.rmmservices;

import com.rmm.rmmservices.model.dto.*;
import com.rmm.rmmservices.model.persistence.entities.*;
import com.rmm.rmmservices.utils.MapperUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * This class test the MapperUtils class
 * @author Paul Rodríguez-Ch
 */
public class TestMapperUtils {

    static final Customer customer = new Customer(1L,
            "user",
            "$2a$10$4FxTu96Enm2l7aezce8bmebIlb.3rwyvtVTVUd0rFl9bT2YHq8TiS");
    static final CustomerService customerService = new CustomerService(1L, "Antivirus");
    final DeviceType deviceType = new DeviceType(1L, "Mac");
    final Device device = new Device(1L, "USER-MAC", deviceType, customerService, customer);
    final ServicePrice servicePrice = new ServicePrice(1L, deviceType, customerService, new BigDecimal("5.00"));


    @Test
    public void testCustomerMappers(){
        final CustomerDTO customerDTO = MapperUtils.mapCustomer(customer);
        Assert.assertEquals(customer.toString(), MapperUtils.unmapCustomer(customerDTO).toString());
    }

    @Test
    public void testCustomerServiceMappers(){
        final CustomerServiceDTO customerServiceDTO = MapperUtils.mapCustomerService(customerService);
        Assert.assertEquals(customerService.toString(), MapperUtils.unmapCustomerService(customerServiceDTO).toString());
    }

    @Test
    public void testCustomerDeviceMappers(){

        final DeviceDTO deviceDTO = MapperUtils.mapCustomerDevice(device);
        Assert.assertEquals(device.toString(), MapperUtils.unmapsCustomerDevice(deviceDTO,
                deviceType,
                customerService,
                customer).toString());
    }

    @Test
    public void testDeviceTypeMapper() {
        final DeviceTypeDTO deviceTypeDTO = MapperUtils.mapDeviceType(deviceType);
        Assert.assertEquals(deviceType.toString(), MapperUtils.unmapDeviceType(deviceTypeDTO).toString());
    }

    @Test
    public void testServicePriceMapper(){
        final ServicePriceDTO servicePriceDTO = MapperUtils.mapServicePrice(servicePrice);
        Assert.assertEquals(servicePrice.toString(), MapperUtils.unmapServicePrice(servicePriceDTO,
                deviceType, customerService).toString());
    }
}
