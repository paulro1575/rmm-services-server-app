package com.rmm.rmmservices.model.dto;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.validation.constraints.NotEmpty;

/**
 * @author Paul Rodríguez-Ch
 */
public class DeviceTypeDTO {

    @Nullable
    private Long id;
    @NotEmpty
    @NotNull
    private String typeName;

    public DeviceTypeDTO() {
    }

    public DeviceTypeDTO(Long id, @NotEmpty @NotNull String typeName) {
        this.id = id;
        this.typeName = typeName;
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

    @Override
    public String toString() {
        return "{" +
                "\"typeName\": \"" + typeName + "\"" +
                '}';
    }
}
