package com.coinbase

import groovy.transform.CompileStatic

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class AccessSignGenerator {

    @CompileStatic
    static String generateAccessSign(String secret, Long epoch, String method, String requestPath, String body=""){
        Mac hmac = Mac.getInstance("HmacSHA256")
        SecretKeySpec secretKeySpec= new SecretKeySpec(secret.getBytes(), "HmacSHA256")
        hmac.init(secretKeySpec)
        byte[] digest = hmac.doFinal("${epoch}${method}${requestPath}${body}".getBytes())
        digest.encodeHex().toString()
    }
}
