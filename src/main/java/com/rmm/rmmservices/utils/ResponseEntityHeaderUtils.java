package com.rmm.rmmservices.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * This class manages Response Entity headers
 * @author Paul Rodríguez-Ch
 */
public final class ResponseEntityHeaderUtils {

    private static final HttpHeaders httpHeaders = new HttpHeaders();

    /**
     * This method creates Http Headers for a JSON content types
     * @return HttpHeaders
     */
    public static HttpHeaders getJsonContentTypeHeaders(){
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
