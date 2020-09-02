package com.rmm.rmmservices.model.persistence.entities;

import javax.persistence.*;

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

    @Column(name = "system_name")
    private String systemName;

    @ManyToOne
    @JoinColumn(name = "device_type_id", referencedColumnName = "id")
    private DeviceType deviceType;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private CustomerService customerService;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public Device() {
    }

    public Device(Long id,
                  String systemName,
                  DeviceType deviceType,
                  CustomerService customerService,
                  Customer customer) {
        this.id = id;
        this.systemName = systemName;
        this.deviceType = deviceType;
        this.customerService = customerService;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", systemName='" + systemName + '\'' +
                ", deviceType=" + deviceType +
                ", customerService=" + customerService +
                ", customer=" + customer +
                '}';
    }
}
