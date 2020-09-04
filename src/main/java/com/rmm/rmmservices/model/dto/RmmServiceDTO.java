package com.rmm.rmmservices.model.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.validation.constraints.NotEmpty;

/**
 * @author Paul Rodríguez-Ch
 */
public class RmmServiceDTO {

    @Nullable
    private Long id;

    @NotNull
    @NotEmpty
    private String serviceName;


    public RmmServiceDTO() {
    }


    public RmmServiceDTO(Long id,
                         @NotNull @NotEmpty String serviceName
    ) {
        this.id = id;
        this.serviceName = serviceName;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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
                "\"id\": \"" + id +
                "\", \"serviceName\": \"" + serviceName + '\"' +
                '}';
    }
}
