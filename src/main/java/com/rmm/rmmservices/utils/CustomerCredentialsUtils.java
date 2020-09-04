package com.rmm.rmmservices.utils;

/**
 * This class expose the customer credential methods to helps its management
 * @author Paul Rodríguez-Ch
 */
public class CustomerCredentialsUtils {

    public static String getUsernameFromToken(String tokenUserData){
        String userData = tokenUserData.split(",")[0];
        userData = userData.replace("{sub=", "");
        return userData;
    }
}
