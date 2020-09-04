package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.CustomerController;
import com.rmm.rmmservices.model.dto.CustomerDTO;
import com.rmm.rmmservices.model.persistence.entities.Customer;
import com.rmm.rmmservices.service.GeneralCRUDService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class test the Customer Controller REST Methods
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
class TestCustomerController {

    private MockMvc mockMvc;

    @Mock
    private GeneralCRUDService<Customer, CustomerDTO> customerService;
    @InjectMocks
    static CustomerController customerController;

    CustomerDTO customerDTO = new CustomerDTO(null, "NewUser", "1234abcd");

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testCustomerCreation() throws Exception {
        this.mockMvc.perform(post("/customer/register/")
                .content(customerDTO.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCustomerCreationValidation() throws Exception {
        this.mockMvc.perform(post("/customer/register")
                .content("{\"username\": \"" + "" +
                        "\",\"password\": \"" + customerDTO.getPassword() +
                        "\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerLoginNotFound() throws Exception{
        this.mockMvc.perform(post("/customer/login/")
                .content("{\"username\": \"" + customerDTO.getUsername() +
                        "\",\"password\": \"" + customerDTO.getPassword() +
                        "\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
