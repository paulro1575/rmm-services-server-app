package com.rmm.rmmservices.model.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author Paul Rodríguez-Ch
 */
public class DeviceTypeDTO {

    @Nullable
    private Long id;
    @NotEmpty
    @NotNull
    private String typeName;
    @NotNull
    private BigDecimal devicePrice;

    public DeviceTypeDTO() {
    }

    public DeviceTypeDTO(Long id,
                         @NotEmpty @NotNull String typeName,
                         @NotNull BigDecimal devicePrice) {
        this.id = id;
        this.typeName = typeName;
        this.devicePrice = devicePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(BigDecimal devicePrice) {
        this.devicePrice = devicePrice;
    }

    @Override
    public String toString() {
        return "{" +
                "\"typeName\": \"" + typeName + "\"" +
                ", \"devicePrice\": \"" + devicePrice +
                "\"}";
    }
}
