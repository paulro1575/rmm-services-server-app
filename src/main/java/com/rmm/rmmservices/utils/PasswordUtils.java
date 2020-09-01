package com.rmm.rmmservices.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * This class manages passwords encryption and decryption process
 * @author Paul Rodríguez-Ch
 */
public final class PasswordUtils {

    /**
     * This method hash a password to be saved in database
     * @param password the plain password
     * @return String with the hashed password
     */
    public static String getPasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * This method verify if the plain password and the hashed password matches
     * @param plainPassword the plain password
     * @param hashedPassword the hashed password
     * @return True if the passwords match or false otherwise
     */
    public static boolean verifyPasswordMatch(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
