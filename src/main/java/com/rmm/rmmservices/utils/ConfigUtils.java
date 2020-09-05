package com.rmm.rmmservices.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class read the properties from application.yml
 * @author Paul Rodríguez-Ch
 */
@Component
@ConfigurationProperties("config")
public class ConfigUtils {

    String deviceCost;

    public String getDeviceCost() {
        return deviceCost;
    }

    public void setDeviceCost(String deviceCost) {
        this.deviceCost = deviceCost;
    }
}
