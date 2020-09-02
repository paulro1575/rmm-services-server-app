package com.rmm.rmmservices;

import com.rmm.rmmservices.utils.ResponseEntityHeaderUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * This class test the ResponseHttpHeadersUtils class
 * @author Paul Rodríguez-Ch
 */
public class testResponseHttpHeaderUtils {

    @Test
    public void testJsonContentTypeHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Assert.assertEquals(httpHeaders.getContentType(),
                ResponseEntityHeaderUtils.getJsonContentTypeHeaders().getContentType());
    }
}
