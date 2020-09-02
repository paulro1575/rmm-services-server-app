package com.rmm.rmmservices.model.persistence.entities;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(name = "device_price")
    private BigDecimal devicePrice;

    public DeviceType() {
    }

    public DeviceType(Long id,
                      String typeName,
                      BigDecimal devicePrice) {
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
        return "DeviceType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", devicePrice=" + devicePrice +
                '}';
    }
}
