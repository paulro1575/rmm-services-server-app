package com.rmm.rmmservices.model.dto;

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
    private String deviceTypeName;

    @NotNull
    private String customerName;

    @Nullable
    private Date initialDate;

    public DeviceDTO() {
    }

    public DeviceDTO(Long id, String deviceTypeName, String customerName, Date initialDate) {
        this.id = id;
        this.deviceTypeName = deviceTypeName;
        this.customerName = customerName;
        this.initialDate = initialDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"deviceTypeName\": \"" + deviceTypeName +
                "\", \"customerName\" : \"" + customerName +
                "\", \"initialDate\" : \"" + initialDate +
                "\"}";
    }
}
