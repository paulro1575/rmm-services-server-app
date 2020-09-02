package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.ServicePriceController;
import com.rmm.rmmservices.model.dto.ServicePriceDTO;
import com.rmm.rmmservices.model.persistence.entities.ServicePrice;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class test the Device Type Controller REST Methods
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServicePriceController {

    private MockMvc mockMvc;

    @Mock
    private GeneralCRUDService<ServicePrice, ServicePriceDTO> servicePriceService;
    @InjectMocks
    static ServicePriceController servicePriceController;

    ServicePriceDTO servicePriceDTO = new ServicePriceDTO(null,
            "Windows WorkStation",
            "TeamViewer", new BigDecimal("5.00"));

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(servicePriceController).build();
    }

    @Test
    public void testServicePriceCreation() throws Exception {
        this.mockMvc.perform(post("/service/price/")
                .content(servicePriceDTO.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testServicePriceCreationValidation() throws Exception {
        this.mockMvc.perform(post("/service/price/")
                .content("{\"deviceTypeName\": \"" + "\"" +
                        ", \"customerServiceName\": \"\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testServicePriceList() throws Exception {
        this.mockMvc.perform(get("/service/price/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testServicePriceDelete() throws Exception {
        this.mockMvc.perform(delete("/service/price/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
