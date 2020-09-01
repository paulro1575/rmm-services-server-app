package com.rmm.rmmservices.model.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Paul Rodríguez-Ch
 */
@Entity(name = "device")
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "device_type_id", referencedColumnName = "id")
    private DeviceType deviceType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @NotNull
    @Column(name = "initial_date")
    private Date initialDate;

    public Device() {
    }

    public Device(Long id, @NotNull DeviceType deviceType, @NotNull Customer customer, @NotNull Date initialDate) {
        this.id = id;
        this.deviceType = deviceType;
        this.customer = customer;
        this.initialDate = initialDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", deviceType=" + deviceType +
                ", customer=" + customer +
                ", initialDate=" + initialDate +
                '}';
    }
}
