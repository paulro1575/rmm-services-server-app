package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.DeviceTypeController;
import com.rmm.rmmservices.model.dto.DeviceDTO;
import com.rmm.rmmservices.model.dto.DeviceTypeDTO;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.service.GeneralCRUDService;
import com.rmm.rmmservices.utils.ConfigUtils;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class test the Device Type Controller REST Methods
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
public class TestDeviceTypeController {

    private MockMvc mockMvc;

    @Mock
    private GeneralCRUDService<DeviceType, DeviceDTO> deviceTypeServices;
    @InjectMocks
    static DeviceTypeController deviceTypeController;
    @Mock
    private ConfigUtils configUtils;

    DeviceTypeDTO deviceTypeDTO = new DeviceTypeDTO(null, "NewDeviceTypeName", new BigDecimal("4.00"));

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviceTypeController).build();
    }

    @Test
    public void testDeviceTypeCreation() throws Exception {
        when(configUtils.getDeviceCost()).thenReturn("4.00");
        this.mockMvc.perform(post("/device-type/")
                .content(deviceTypeDTO.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeviceTypeCreationValidation() throws Exception {
        this.mockMvc.perform(post("/device-type/")
                .content("{\"typeName\": \"" + "" +
                        "\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeviceTypeList() throws Exception {
        this.mockMvc.perform(get("/device-type/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeviceTypeDelete() throws Exception {
        this.mockMvc.perform(delete("/device-type/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
