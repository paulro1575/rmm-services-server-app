package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.CustomerDeviceController;
import com.rmm.rmmservices.model.dto.CustomerServiceDTO;
import com.rmm.rmmservices.model.dto.DeviceDTO;
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
 * This class test the Customer Services Controller REST Methods
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCustomerDevicesController {

    private MockMvc mockMvc;

    @Mock
    private GeneralCRUDService<CustomerService, CustomerServiceDTO> customerServicesService;
    @InjectMocks
    static CustomerDeviceController customerDeviceController;

    DeviceDTO deviceDTO = new DeviceDTO(null,
            "User-PC",
            "Windows WorkStation",
            1L);

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerDeviceController).build();
    }

    @Test
    public void testDeviceCreation() throws Exception {
        this.mockMvc.perform(post("/customer/device/")
                .content(deviceDTO.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeviceList() throws Exception {
        this.mockMvc.perform(get("/customer/device/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeviceUpdate() throws Exception {
        this.mockMvc.perform(put("/customer/device/1")
                .content(deviceDTO.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCustomerServiceDelete() throws Exception {
        this.mockMvc.perform(delete("/customer/device/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
