package com.rmm.rmmservices.model.persistence.entities;

import javax.persistence.*;

/**
 * @author Paul Rodríguez-Ch
 */
@Entity(name = "device_type")
@Table(name = "device_type")
public class DeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type_name")
    private String typeName;

    public DeviceType() {
    }

    public DeviceType(Long id, String typeName) {
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
        return "DeviceType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
