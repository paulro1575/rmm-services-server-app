package com.rmm.rmmservices.model.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

/**
 * @author Paul Rodríguez-Ch
 */
public class DeviceDTO {

    @Nullable
    private Long id;

    @NotNull
    private String systemName;

    @NotNull
    private String deviceTypeName;

    @NotNull
    private String serviceName;

    @NotNull
    private Long customerId;

    public DeviceDTO() {
    }

    public DeviceDTO(Long id, String systemName, String deviceTypeName, String serviceName, Long customerId) {
        this.id = id;
        this.systemName = systemName;
        this.deviceTypeName = deviceTypeName;
        this.serviceName = serviceName;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"systemName\": \"" + systemName + "\"" +
                ", \"deviceTypeName\": \"" + deviceTypeName + "\"" +
                ", \"serviceName\": \"" + serviceName + "\"" +
                ", \"customerId\": \"" + customerId +
                "\"}";
    }
}
