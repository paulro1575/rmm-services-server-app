package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.RmmServiceController;
import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.dto.RmmServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.CustomerService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class test the Rmm Services Controller REST Methods
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
class TestRmmServiceController {

    private MockMvc mockMvc;

    @Mock
    private GeneralCRUDService<CustomerService, CustomerServiceDTO> customerServicesService;
    @InjectMocks
    static RmmServiceController rmmServiceController;

    RmmServiceDTO rmmServiceDTO = new RmmServiceDTO(null, "NewService");

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(rmmServiceController).build();
    }

    @Test
    public void testCustomerServiceCreation() throws Exception {
        this.mockMvc.perform(post("/service/")
                .content(rmmServiceDTO.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCustomerServiceCreationValidation() throws Exception {
        this.mockMvc.perform(post("/service/")
                .content("{\"serviceName\": \"" + "" +
                        "\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerServiceList() throws Exception {
        this.mockMvc.perform(get("/service/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCustomerServiceDelete() throws Exception {
        this.mockMvc.perform(delete("/service/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
