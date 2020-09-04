package com.rmm.rmmservices.model.persistence.entities;

import javax.persistence.*;

/**
 * @author Paul Rodríguez-Ch
 */
@Entity(name = "customer_service")
@Table(name = "customer_service")
public class CustomerService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn (name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "service_id", referencedColumnName = "id")
    private RmmService service;


    public CustomerService() {
    }


    public CustomerService(Long id, Customer customer, RmmService service) {
        this.id = id;
        this.customer = customer;
        this.service = service;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public RmmService getService() {
        return service;
    }


    public void setService(RmmService service) {
        this.service = service;
    }

    
    @Override
    public String toString() {
        return "CustomerService{" +
                "id=" + id +
                ", customer=" + customer +
                ", service=" + service +
                '}';
    }
}
