package com.rmm.rmmservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rmm.rmmservices.model.dto.CostPerServiceDTO;
import com.rmm.rmmservices.model.dto.CustomerDevicesCalculationDTO;
import com.rmm.rmmservices.utils.BillCalculationUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class test the Customer Calculation Bill method
 * @author Paul Rodríguez-Ch
 */
public class TestBillCalculationUtils {

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateMonthlyPriceUtils() throws JsonProcessingException {

        ResponseEntity<Object> responseEntity = BillCalculationUtils.getMonthlyBill(getServicesArray(), getDevicesArray());
        CustomerDevicesCalculationDTO customerDevicesCalculationDTO = getCustomerCalculationsDTO();
        CustomerDevicesCalculationDTO customerDevicesCalculationDTO1 = (CustomerDevicesCalculationDTO)responseEntity.getBody();
        Assert.assertEquals(customerDevicesCalculationDTO.getOutput(), customerDevicesCalculationDTO1.getOutput());
    }

    private static List<Object[]> getServicesArray(){
        Object[] service1 = new Object[4];
        service1[0] = "User-PC";
        service1[1] = "Windows WorkStation";
        service1[2] = "Antivirus";
        service1[3] = "5";

        Object[] service2 = new Object[4];
        service2[0] = "User-Mac";
        service2[1] = "Mac";
        service2[2] = "Antivirus";
        service2[3] = "7";

        Object[] service3 = new Object[4];
        service3[0] = "User-PC";
        service3[1] = "Windows WorkStation";
        service3[2] = "PSA";
        service3[3] = "2";

        Object[] service4 = new Object[4];
        service4[0] = "User-Mac";
        service4[1] = "Mac";
        service4[2] = "PSA";
        service4[3] = "2";

        List<Object[]> servicesArray = new ArrayList<>();
        servicesArray.add(service1);
        servicesArray.add(service2);
        servicesArray.add(service3);
        servicesArray.add(service4);
        return servicesArray;
    }


    private static List<Object[]> getDevicesArray(){
        Object[] device = new Object[2];
        device[0] = "2";
        device[1] = "8";
        List<Object[]> devicesArray = new ArrayList<>();
        devicesArray.add(device);
        return devicesArray;
    }

    private static CustomerDevicesCalculationDTO getCustomerCalculationsDTO(){
        CostPerServiceDTO costPerDevicesDTO = new CostPerServiceDTO("2 Devices", new BigDecimal("8"));
        CostPerServiceDTO costPerAntivirusDTO = new CostPerServiceDTO("Antivirus", new BigDecimal("12"));
        CostPerServiceDTO costPerPSADTO = new CostPerServiceDTO("Antivirus", new BigDecimal("4"));
        List<CostPerServiceDTO>explanation = new ArrayList<>();
        explanation.add(costPerDevicesDTO);
        explanation.add(costPerAntivirusDTO);
        explanation.add(costPerPSADTO);
        BigDecimal output = new BigDecimal("24");
        return new CustomerDevicesCalculationDTO(output, explanation);
    }
}
