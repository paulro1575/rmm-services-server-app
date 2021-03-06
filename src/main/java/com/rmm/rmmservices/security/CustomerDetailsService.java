package com.rmm.rmmservices.security;

import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * This class manages the customer details through login process
 * @author Paul Rodríguez-Ch
 */
@Service
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;


    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            return new User(customer.get().getUsername(), customer.get().getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException(username);
    }
}
