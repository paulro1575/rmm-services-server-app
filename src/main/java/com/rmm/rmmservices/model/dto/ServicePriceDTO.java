package com.rmm.rmmservices.model.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author Paul Rodríguez-Ch
 */
public class ServicePriceDTO {

    @Nullable
    private Long id;

    @NotNull
    @NotEmpty
    private String deviceTypeName;

    @NotNull
    @NotEmpty
    private String rmmServiceName;

    @NotNull
    private BigDecimal price;

    public ServicePriceDTO() {
    }

    public ServicePriceDTO(Long id,
                           @NotNull @NotEmpty String deviceTypeName,
                           @NotNull @NotEmpty String rmmServiceName,
                           @NotNull @NotEmpty BigDecimal price) {
        this.id = id;
        this.deviceTypeName = deviceTypeName;
        this.rmmServiceName = rmmServiceName;
        this.price = price;
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

    public String getRmmServiceName() {
        return rmmServiceName;
    }

    public void setRmmServiceName(String rmmServiceName) {
        this.rmmServiceName = rmmServiceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "\"deviceTypeName\": \"" + deviceTypeName + '\"' +
                ", \"rmmServiceName\": \"" + rmmServiceName + '\"' +
                ", \"price\": \"" + price +
                "\"}";
    }
}
