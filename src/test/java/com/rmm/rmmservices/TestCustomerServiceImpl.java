package com.rmm.rmmservices;

import com.rmm.rmmservices.model.dto.CustomerDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCustomerServiceImpl {

    @Mock
    private CustomerRepository customerRepository;

    static final CustomerDTO customerDTO = new CustomerDTO (
        null, "username", "password"
    );

    static final Customer expectedCustomer = new Customer (
            1L, "username", "password"
    );

    @InjectMocks
    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCustomerRegister() {
        when(this.customerRepository.save(any())).thenReturn(expectedCustomer);
        Customer newCustomer = customerServiceImpl.create(customerDTO);
        Assert.assertEquals(expectedCustomer, newCustomer);
    }
}