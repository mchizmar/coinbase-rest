package com.coinbase

import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class AccessSignGeneratorSpec extends Specification implements GrailsUnitTest {


    /*
        Javascript output
        console.log("Test coinbase hash: " + coinbaseHash('secret', '1', 'GET', 'requestPath',''))
        062803248d4893b99f7c1bbc2cdf72e796d5ada6c6335559e6a2fedb2aa185fd
     */


    void "Digest returns hex test value when body is empty"() {

        given:
        String secret = 'secret'
        Long epoch = 1
        String method='GET'
        String requestPath = 'requestPath'
        String body = ''

        when:
        String digest = AccessSignGenerator.generateAccessSign(secret, epoch,method,requestPath, body)

        then:
        digest == "062803248d4893b99f7c1bbc2cdf72e796d5ada6c6335559e6a2fedb2aa185fd"
        
    }
}