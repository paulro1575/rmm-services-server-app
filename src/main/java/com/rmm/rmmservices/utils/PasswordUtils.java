package com.rmm.rmmservices.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * This class manages passwords encryption and decryption process
 * @author Paul Rodríguez-Ch
 */
public class PasswordUtils {

    public static String getPasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPasswordMatch(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
