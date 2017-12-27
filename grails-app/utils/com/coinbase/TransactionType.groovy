package com.coinbase

class TransactionType {

    static final TRANSACTION_TYPES = [
            "send",  //Sent bitcoin/litecoin/ethereum to a bitcoin/litecoin/ethereum address or email (documentation)
            "request",  //Requested bitcoin/litecoin/ethereum from a user or email (documentation)
            "transfer",  //Transfered funds between two of a user’s accounts (documentation)
            "buy",  //Bought bitcoin, litecoin or ethereum (documentation)
            "sell",  //Sold bitcoin, litecoin or ethereum (documentation)
            "fiat_deposit",  //Deposited funds into a fiat account from a financial institution (documentation)
            "fiat_withdrawal",  //Withdrew funds from a fiat account (documentation)
            "exchange_deposit",  //Deposited money into GDAX
            "exchange_withdrawal",  //Withdrew money from GDAX
            "vault_withdrawal"  //Withdrew funds from a vault account
            //More to be added soon..
    ]

    String type

    TransactionType(String type){
        if (!type || !TRANSACTION_TYPES.contains(type)){
            throw new IllegalArgumentException("type cannot be null or must be a valid TRANSACTION_TYPE")
        }
        this.type = type 
    }
}
