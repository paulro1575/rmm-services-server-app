package com.rmm.rmmservices;

import com.rmm.rmmservices.utils.PasswordUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * This class test the PasswordUtils encryption and hash verification
 * @author Paul Rodríguez-Ch
 */
public class TestPasswordUtils {

    @Test
    public void testPasswordEncryption(){
        String plainPassword = "1234abcd";
        String hashedPassword = PasswordUtils.getPasswordHash(plainPassword);
        Assert.assertNotEquals(hashedPassword, plainPassword);
    }

    @Test
    public void testPasswordVerified(){
        final String plainPassword = "1234abcd";
        final String hashedPassword = "$2a$10$4FxTu96Enm2l7aezce8bmebIlb.3rwyvtVTVUd0rFl9bT2YHq8TiS";
        Assert.assertTrue(PasswordUtils.verifyPasswordMatch(plainPassword, hashedPassword));

    }
}
