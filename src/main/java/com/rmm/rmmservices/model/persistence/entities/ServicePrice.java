package com.rmm.rmmservices.model.persistence.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Paul Rodríguez-Ch
 */
@Entity(name = "service_price")
@Table(name = "service_price")
public class ServicePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_type_id", referencedColumnName = "id")
    private DeviceType deviceType;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private CustomerService customerService;

    @Column(name = "price")
    private BigDecimal price;

    public ServicePrice() {
    }

    public ServicePrice(Long id,
                        DeviceType deviceType,
                        CustomerService customerService,
                        BigDecimal price) {
        this.id = id;
        this.deviceType = deviceType;
        this.customerService = customerService;
        this.price = price;
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

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServicePrice{" +
                "id=" + id +
                ", deviceType=" + deviceType +
                ", customerService=" + customerService +
                ", price=" + price +
                '}';
    }
}
