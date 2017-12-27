package com.coinbase

class Amount {
    String currency
    Double amount

    void setAmount(a){
        this.amount = new Double(Double.parseDouble(a.toString()))
    }

}
