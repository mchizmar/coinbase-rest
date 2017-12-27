package com.coinbase

import com.coinbase.TimeResponseParser
import spock.lang.Specification

class TimeResponseParserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "Time is returned from a JSON element"() {

        given:
        def t = TimeResponseParser.timeFromJsonElement([data:[epoch: 1511452266, iso: '2017-11-23T15:51:06Z']])

        expect:"Time to parse JSON element"
        t.iso == '2017-11-23T15:51:06Z'
        t.epoch == 1511452266

    }

}
