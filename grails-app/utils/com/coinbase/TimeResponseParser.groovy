package com.coinbase

import groovy.transform.CompileDynamic

class TimeResponseParser {

    @CompileDynamic
    static CoinbaseTime timeFromJsonElement(json){
        new CoinbaseTime (json.data.iso, json.data.epoch as Long)
    }
}