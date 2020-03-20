package com.tianhy.study;


import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.UnsupportedEncodingException;


/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws UnsupportedEncodingException {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODI3MTI2MjcsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiV1JJR1RIX1dSSVRFIiwiV1JJR1RIX1JFQUQiXSwianRpIjoiZDgzMmE5NGQtMzc4OS00OTc5LWJlMWItOWQzYzM2NDhhZTI2IiwiY2xpZW50X2lkIjoienV1bF9zZXJ2ZXIiLCJzY29wZSI6WyJXUklHVEgiLCJyZWFkIl19.mqaELLGavferSocisF4cRTdJ6WnjVdz0KSD6SELRjgQ";
        byte[] bytes = Base64.decodeBase64(token);
        System.out.println(new String(bytes, "utf-8"));
    }
}
