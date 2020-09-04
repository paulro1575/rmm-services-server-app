package com.rmm.rmmservices.model.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.validation.constraints.NotEmpty;

/**
 * @author Paul Rodríguez-Ch
 */
public class CustomerServiceDTO {

    @Nullable
    private Long id;

    @Nullable
    private Long customerId;

    @NotNull
    @NotEmpty
    private String serviceName;


    public CustomerServiceDTO() {
    }

    public CustomerServiceDTO(Long id, @Nullable Long customerId, @NotNull @NotEmpty String serviceName) {
        this.id = id;
        this.customerId = customerId;
        this.serviceName = serviceName;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getCustomerId() {
        return customerId;
    }


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public String getServiceName() {
        return serviceName;
    }


    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    @Override
    public String toString() {
        return "{" +
                "\"customerId\": \"" + customerId + "\"," +
                "\"serviceName\": \"" + serviceName + "\"" +
                "}";
    }
}
