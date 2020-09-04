package com.rmm.rmmservices;

import com.rmm.rmmservices.controller.BillCalculationsController;
import com.rmm.rmmservices.model.persistence.repository.CustomerRepository;
import com.rmm.rmmservices.service.BillCalculationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    static BillCalculationsController billCalculationsController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(billCalculationsController).build();
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
    public void testBillMonthlyPrice() throws Exception {
        this.mockMvc.perform(get("/customer/service/bill/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
