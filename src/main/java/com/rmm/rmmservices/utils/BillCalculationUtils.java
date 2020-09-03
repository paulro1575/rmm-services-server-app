package com.rmm.rmmservices.utils;

import com.rmm.rmmservices.model.dto.CostPerServiceDTO;
import com.rmm.rmmservices.model.dto.CustomerDevicesCalculationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class manages the monthly calculation bill process
 * @author Paul Rodríguez-Ch
 */
public class BillCalculationUtils {

    /**
     * This method calculate the monthly price to pay based on customer devices and services
     * @param servicesValues List of services object values of a customer
     * @param devicesValues Listo of devices object values of a customer
     * @return ResponseEntity with CustomerDevicesCalculationsDTO
     */
    public static ResponseEntity<Object> getMonthlyBill(List<Object[]> servicesValues, List<Object[]> devicesValues){
        Map<String, BigDecimal> totalServiceValues = new HashMap<>();
        fillMapWithDevicesCost(totalServiceValues, devicesValues);
        fillMapWithServicesValues(totalServiceValues, servicesValues);
        CustomerDevicesCalculationDTO customerDevicesCalculationDTO = new CustomerDevicesCalculationDTO(
                getOutput(totalServiceValues), getListCostPerDeviceDto(totalServiceValues));
        return new ResponseEntity<>(customerDevicesCalculationDTO,
                ResponseEntityHeaderUtils.getJsonContentTypeHeaders(),
                HttpStatus.OK);
    }

    /**
     * This method fills the map with the devices total Values
     * @param totalServiceValues map to be filled
     * @param devicesValues List of devices values
     */
    private static void fillMapWithDevicesCost(Map<String, BigDecimal> totalServiceValues, List<Object[]> devicesValues){
        for(Object[] deviceCost: devicesValues){
            totalServiceValues.put(deviceCost[0].toString() + " Devices", new BigDecimal(deviceCost[1].toString()));
        }
    }

    /**
     * This method fills the map with the services total Values
     * @param totalServiceValues map to be filled
     * @param servicesValues List of services values
     */
    private static void fillMapWithServicesValues(Map<String, BigDecimal> totalServiceValues,
                                                       List<Object[]> servicesValues){
        for (Object[] serviceCost : servicesValues){
            String serviceName = serviceCost[2].toString();
            BigDecimal serviceTotalValue = new BigDecimal(serviceCost[3].toString());
            if(totalServiceValues.containsKey(serviceName)){
                serviceTotalValue = serviceTotalValue.add(totalServiceValues.get(serviceName));
            }
            totalServiceValues.put(serviceName, serviceTotalValue);
        }
    }

    /**
     * This method transforms Map of String, BigDecimal values into List of CostPerDeviceDto objects
     * @param servicesList services List of objects
     * @return List of CostPerDeviceDto
     */
    private static List<CostPerServiceDTO> getListCostPerDeviceDto(Map<String, BigDecimal> servicesList) {
        List<CostPerServiceDTO> costPerServiceDTOS = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> object : servicesList.entrySet()) {
            CostPerServiceDTO costPerServiceDTO = new CostPerServiceDTO(object.getKey(), object.getValue());
            costPerServiceDTOS.add(costPerServiceDTO);
        }
        return costPerServiceDTOS;
    }

    /**
     * This method returns the total monthly value final sum
     * @param servicesList Map of services list
     * @return BigDecimal final output
     */
    private static BigDecimal getOutput(Map<String, BigDecimal> servicesList) {
        BigDecimal output = new BigDecimal("0");
        for (Map.Entry<String, BigDecimal> object : servicesList.entrySet()) output = output.add(object.getValue());
        return output;
    }
}
