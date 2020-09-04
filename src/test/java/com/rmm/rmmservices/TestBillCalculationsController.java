package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.BillCalculationsController;
import com.rmm.rmmservices.service.BillCalculationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class test the Bill Calculation Controller
 * @author Paul Rodríguez-Ch
 */
public class TestBillCalculationsController {

    private MockMvc mockMvc;

    @Mock
    private BillCalculationsService billCalculationsService;
    @InjectMocks
    static BillCalculationsController billCalculationsController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(billCalculationsController).build();
    }

    @Test
    public void testBillMonthlyPrice() throws Exception {
        this.mockMvc.perform(get("/customer/service/bill/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
