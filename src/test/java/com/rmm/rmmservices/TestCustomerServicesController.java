package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.CustomerServiceController;
import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.service.GeneralCRUDService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class test the Customer Services Controller REST Methods
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCustomerServicesController {

    private MockMvc mockMvc;

    @Mock
    private GeneralCRUDService<CustomerService, CustomerServiceDTO> customerServicesService;
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    static CustomerServiceController customerServiceController;


    CustomerServiceDTO customerServiceDTO;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(customerServiceController)
                .build();
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(
                "rmm-user",
                "12341234");
        when(authentication.getPrincipal()).thenReturn(principal);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn(principal.getName());
        customerServiceDTO = new CustomerServiceDTO();
        customerServiceDTO.setCustomerId(1l);
        customerServiceDTO.setServiceName("NewService");
    }

    @Test
    public void testCustomerServiceCreation() throws Exception {
        this.mockMvc.perform(post("/customer/service/")
                .content(customerServiceDTO.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCustomerServiceList() throws Exception {
        this.mockMvc.perform(get("/customer/service/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCustomerServiceDelete() throws Exception {
        this.mockMvc.perform(delete("/customer/service/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
