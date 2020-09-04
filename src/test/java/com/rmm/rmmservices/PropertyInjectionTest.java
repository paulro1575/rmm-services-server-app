package com.rmm.rmmservices;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class test the Property Injection Method
 * @author Paul Rodríguez-Ch
 */
@SpringBootTest(properties = {"config.device-cost=4.00"}, classes = PropertyInjectionTest.class)
public class PropertyInjectionTest {

    @Value("${config.device-cost}")
    private String deviceCost;

    @Test
    public void testProperlyInjected() {
        Assert.assertEquals(deviceCost, "4.00");
    }
}
