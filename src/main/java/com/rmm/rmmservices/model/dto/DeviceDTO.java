package com.rmm.rmmservices.model.dto;

import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.util.Date;

/**
 * @author Paul Rodríguez-Ch
 */
public class DeviceDTO {

    @Nullable
    private Long id;

    @NotNull
    private DeviceTypeDTO deviceTypeDTO;

    @NotNull
    private Customer customer;

    @Nullable
    private Date initialDate;

    public DeviceDTO() {
    }

    public DeviceDTO(Long id, DeviceTypeDTO deviceTypeDTO, Customer customer, Date initialDate) {
        this.id = id;
        this.deviceTypeDTO = deviceTypeDTO;
        this.customer = customer;
        this.initialDate = initialDate;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    @Override
    public String toString() {
        return "Device Type: " + deviceTypeDTO +
                ", Cuystomer " +  customer;
    }
}
