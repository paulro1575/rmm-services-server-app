package com.rmm.rmmservices.model.dto;

import com.sun.istack.Nullable;

import javax.validation.constraints.NotEmpty;

/**
 * @author Paul Rodríguez-Ch
 */
public class DeviceTypeDTO {

    @Nullable
    private Long id;
    @NotEmpty
    private String deviceType;

    public DeviceTypeDTO() {
    }

    public DeviceTypeDTO(Long id, @NotEmpty String deviceType) {
        this.id = id;
        this.deviceType = deviceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return "Device Type: " + deviceType;
    }
}
