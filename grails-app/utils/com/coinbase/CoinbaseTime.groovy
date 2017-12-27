package com.coinbase

import groovy.transform.CompileStatic

@CompileStatic
class CoinbaseTime {
    String iso
    Long epoch

    public CoinbaseTime(String iso, Long epoch){
        this.iso = iso
        this.epoch = epoch
    }
}

