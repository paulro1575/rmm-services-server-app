package com.rmm.rmmservices;

import com.rmm.rmmservices.utils.ConfigUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class test the Property Injection Method
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = { "config.device-cost=4.00" })
public class TestConfigPropertiesInjectionUtil {

    @Autowired
    private ConfigUtils configUtils;

    @Test
    public void testProperlyInjected() {
        Assert.assertEquals(configUtils.getDeviceCost(), "4.00");
    }
}
