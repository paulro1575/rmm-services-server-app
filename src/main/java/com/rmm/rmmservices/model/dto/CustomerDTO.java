package com.rmm.rmmservices.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;

import javax.validation.constraints.NotEmpty;

/**
 * @author Paul Rodríguez-Ch
 */
public class CustomerDTO {

    @Nullable
    private Long id;
    @NotEmpty
    private String username;
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    private String password;

    public CustomerDTO(Long id, @NotEmpty String username, @NotEmpty String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public CustomerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + username;
    }
}
