package com.coinbase

import groovy.transform.CompileDynamic
import org.hibernate.resource.transaction.backend.jta.internal.JtaTransactionAdapterUserTransactionImpl

/*

Flows
    As bitcoin experimenter, I would like to purchase my first bitcoin, so I can support the botcoin cause

    -- Exchange Deposit --
    1. I have a fiat currency in a bank account that supports Electronic Fund Transfers
        - prerequisite for a first time crypto purchaser and most common use case
    2. I set up an account on an exchange like Coinbase(GDAX) that supports transfers of fiat money
    3. I set up an **Exchange Deposit** transaction to move fiat from my bank to the exchange
        - I now have the ability to purchase bitcoin

    -- Buy --
    4. On the exchange, I create Buy transaction
    5. The completed transaction
        - the exchange takes a fee from the fiat
        - the exchange rate is calculated
        - the amount-fee is taken to purchase that amount of Bitcoin based off exchange rate

    -- Now I have BTC and can do a number of things with it --
    (Taxable)** Sell ** BTC for fiat -> realized Gain or Loss

    (No Tax)** Transfer **  between MY BTC accounts -> no gains

    (Tax??) ** Send ** to another BTC account -> Gains or Loss? Is a send to an account a realized gain?

    (No Tax)** Exchange Withdrawal -> fiat back a bank over EFT, gaines/loses already realized, not taxing

Buy
Sell
Transfer
Send
Exchange Deposit
Exchange Withdrawal
Vault Withdrawal

Request? An email is not a request. Its a transfer.
An email can be sent to a person from your exchange. This email will contain an address to which the BTC will be transferred.
The transaction is pending until the recipient chooses to accept it or, I think, a specified time runs out.
If this transfer is to an address you own, it should not be taxed, and considered a transfer.
If the transfer is someone else, then it should be a taxable transaction.
*/

class TransactionResponseParser {
    @CompileDynamic
    static TransactionLedger transactionsFromJsonElement(json){

        TransactionLedger ledger = new TransactionLedger()
        json.data.each{ tx ->

            Transaction t =  getTransactionInstance(tx, ledger)
            t.id = tx.id
            t.type = tx.type
            t.status = tx.status

            t.amount = new Amount()
            t.amount.amount = tx.amount.amount
            t.amount.currency = tx.amount.currency

            t.nativeAmount = new Amount()
            t.nativeAmount.amount = tx.native_amount.amount
            t.nativeAmount.currency = tx.native_amount.currency
            
            t.description = tx.description
            t.createdAt = CoinbaseDateParser.parseDate(tx.created_at)
            t.updatedAt = CoinbaseDateParser.parseDate(tx.updated_at)
            t.resource = tx.resource
            t.resourcePath = tx.resource_path

            t.details = new Details()
            t.details.title = tx.details.title
            t.details.subtitle = tx.details.subtitle
            
        }
        ledger
    }

    static Transaction getTransactionInstance(tx, ledger){

        Transaction t
        switch (tx.type){
            case "buy":
                t = new BuyTransaction()
                t.party = new TransactionParty()
                t.party.id = tx.buy.id
                t.party.resource = tx.buy.resource
                t.party.resourcePath = tx.buy.resource_path
                ledger.buys.add(t)
                break;
            case "sell":
                t = new SellTransaction()
                ledger.sells.add(t)
                break;
            case "send":
                t = new SendTransaction()
                t.party = new TransactionParty()
                t.party.id = tx.to.id
                t.party.resource = tx.to.resource
                t.party.resourcePath = tx.to.resource_path
                t.network = new Network()
                t.network.status = tx.network.status
                t.network.name = tx.network.name
                ledger.sends.add(t)
                break;
            case "request":
                t = new RequestTransaction()
                t.party = new TransactionParty()
                t.party.id = tx.to.id
                t.party.resource = tx.to.resource
                t.party.email = tx.to.email
                ledger.requests.add(t)
                break;
            case "transfer":
                t = new TransferTransaction()
                t.party = new TransactionParty()
                t.party.id = tx.to.id
                t.party.resource = tx.to.resource
                t.party.resourcePath = tx.to.resource_path
                ledger.transfers.add(t)
                break;
            case "fiat_deposit":
                t = new FiatDepositTransaction()
                ledger.fiatDeposits.add(t)
                break;
            case "fiat_withdrawal":
                t = new FiatWithdrawalTransaction()
                ledger.fiatWithdrawals.add(t)
                break;
            case "exchange_deposit":
                t = new ExchangeDepositTransaction()
                ledger.exchangeDeposits.add(t)
                break;
            case "exchange_withdrawal":
                t = new ExchangeWithdrawalTransaction()
                ledger.exchangeWithdrawals.add(t)
                break;
            case "vault_withdrawal":
                t = new VaultWithdrawalTransaction()
                ledger.vaultWithdrawals.add(t)
                break;
            default:
                t = new Transaction()
                ledger.unknowns.add(t)
        }
        t

    }

}

