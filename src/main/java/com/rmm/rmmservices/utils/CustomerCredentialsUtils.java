package com.rmm.rmmservices.utils;

import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;

import java.util.Optional;

public class CustomerCredentialsUtils {

    public static Long getUserIdPerUsername(CustomerRepository customerRepository, String username){
        Optional<Customer> customerOptional = customerRepository.findByUsername(username);
        return customerOptional.map(Customer::getId).orElse(null);
    }

    public static String getUsernameFromToken(String tokenUserData){
        String userData = tokenUserData.split(",")[0];
        userData = userData.replace("{sub=", "");
        return userData;
    }
}
