package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.CustomerDeviceController;
import com.rmm.rmmservices.model.dto.DeviceDTO;
import com.rmm.rmmservices.model.persistence.entities.Device;
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
 * This class test the Customer Devices Controller REST Methods
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCustomerDevicesController {

    private MockMvc mockMvc;

    @Mock
    private GeneralCRUDService<Device, DeviceDTO> customerDeviceService;
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    static CustomerDeviceController customerDeviceController;

    DeviceDTO deviceDTO = new DeviceDTO(null,
            "User-PC",
            "Windows WorkStation",
            1L);

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(customerDeviceController)
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
