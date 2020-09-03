package com.rmm.rmmservices.model.dto;

import java.math.BigDecimal;

/**
 * @author Paul Rodríguez-Ch
 */
public class CostPerDeviceDTO {

    public String service;
    public BigDecimal monthlyCost;

    public CostPerDeviceDTO() {
    }

    public CostPerDeviceDTO(String service, BigDecimal monthlyCost) {
        this.service = service;
        this.monthlyCost = monthlyCost;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(BigDecimal monthlyCost) {
        this.monthlyCost = monthlyCost;
    }
}
