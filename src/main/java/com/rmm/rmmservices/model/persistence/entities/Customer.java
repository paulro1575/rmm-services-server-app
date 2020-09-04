package com.rmm.rmmservices.model.persistence.entities;

import javax.persistence.*;

/**
 * @author Paul Rodríguez-Ch
 */
@Entity(name = "customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;


    public Customer(Long id,
                    String username,
                    String password
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public Customer() {
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
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
