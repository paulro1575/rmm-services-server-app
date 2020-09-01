package com.rmm.rmmservices.model.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.math.BigDecimal;

/**
 * @author Paul Rodríguez-Ch
 */
public class ServicePriceDTO {

    @Nullable
    private Long id;

    @NotNull
    private DeviceTypeDTO deviceTypeDTO;

    @NotNull
    private CustomerServiceDTO customerServiceDTO;

    @NotNull
    private BigDecimal price;

    public ServicePriceDTO() {
    }

    public ServicePriceDTO(Long id, DeviceTypeDTO deviceTypeDTO, CustomerServiceDTO customerServiceDTO, BigDecimal price) {
        this.id = id;
        this.deviceTypeDTO = deviceTypeDTO;
        this.customerServiceDTO = customerServiceDTO;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceTypeDTO getDeviceTypeDTO() {
        return deviceTypeDTO;
    }

    public void setDeviceTypeDTO(DeviceTypeDTO deviceTypeDTO) {
        this.deviceTypeDTO = deviceTypeDTO;
    }

    public CustomerServiceDTO getCustomerServiceDTO() {
        return customerServiceDTO;
    }

    public void setCustomerServiceDTO(CustomerServiceDTO customerServiceDTO) {
        this.customerServiceDTO = customerServiceDTO;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ", Device Type= " + deviceTypeDTO +
                ", Customer Service= " + customerServiceDTO +
                ", Price= " + price;
    }
}
