package com.rmm.rmmservices;

import com.rmm.rmmservices.utils.CustomerCredentialsUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * This class test the CustomerCredentialUtil class to verify its methods
 * @author Paul Rodríguez-Ch
 */
public class TestCustomerCredentialsUtils {

    @Test
    public void testCustomerGetDataFromAuthentication(){
        String principalFormatString = CustomerCredentialsUtils.getUsernameFromToken(
                "{sub=this-is-a-username, " +
                        "exp=2131233}");
        String expectedUsernameString = "this-is-a-username";
        Assert.assertEquals(principalFormatString, expectedUsernameString);
    }
}
