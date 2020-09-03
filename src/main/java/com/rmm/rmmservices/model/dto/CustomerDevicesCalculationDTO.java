package com.rmm.rmmservices.model.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Paul Rodríguez-Ch
 */
public class CustomerDevicesCalculationDTO {

    private BigDecimal output;
    private List<CostPerDeviceDTO> explanation;

    public CustomerDevicesCalculationDTO() {
    }

    public CustomerDevicesCalculationDTO(BigDecimal output, List<CostPerDeviceDTO> explanation) {
        this.output = output;
        this.explanation = explanation;
    }

    public BigDecimal getOutput() {
        return output;
    }

    public void setOutput(BigDecimal output) {
        this.output = output;
    }

    public List<CostPerDeviceDTO> getExplanation() {
        return explanation;
    }

    public void setExplanation(List<CostPerDeviceDTO> explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return "{" +
                "\"output\" : \"" + output +
                "\", \"explanation\": \"" + explanation +
                "\"}";
    }
}
