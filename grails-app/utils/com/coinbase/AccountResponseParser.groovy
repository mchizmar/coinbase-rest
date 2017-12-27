package com.coinbase

import groovy.transform.CompileDynamic

class AccountResponseParser {

    //"data":[{"id":"513b40a9-798b-5199-bde2-f4e2e8022838","name":"ETH Wallet","primary":false,"type":"wallet","currency":{"code":"ETH","name":"Ethereum","color":"#6F7CBA","exponent":8,"type":"crypto"},"balance":{"amount":"3.63197154","currency":"ETH"},
    // "created_at":"2017-06-18T11:11:40Z","updated_at":"2017-11-08T17:09:34Z","resource":"account","resource_path":"/v2/accounts/513b40a9-798b-5199-bde2-f4e2e8022838",
    // "native_balance":{"amount":"1717.21","currency":"USD"}},

    /*
        yyyy-MM-dd HH:mm:ss	2012-01-31 23:59:59
        2017-06-18T11:11:40Z
     */
    @CompileDynamic
    static List<Account> accountsFromJsonElement(json){

        def accounts = []
        json.data.each{ acct->

            Account account = new Account()
            account.id = acct.id
            account.name = acct.name
            account.primary = acct.primary
            account.type = acct.type

            Currency currency = new Currency()
            currency.code = acct.currency.code
            currency.name = acct.currency.name
            currency.color = acct.currency.color
            currency.exponent = acct.currency.exponent
            currency.type = acct.currency.type
            account.currency = currency

            Balance balance = new Balance()
            balance.amount = acct.balance.amount
            balance.currency = acct.balance.currency
            account.balance = balance

            account.createdAt = CoinbaseDateParser.parseDate(acct.created_at)
            account.updatedAt = CoinbaseDateParser.parseDate(acct.updated_at)
            account.resource = acct.resource
            account.resourcePath = acct.resource_path

            Balance nativeBalance = new Balance()
            nativeBalance.amount = acct.native_balance.amount
            nativeBalance.currency = acct.native_balance.currency
            account.nativeBalance = nativeBalance

            accounts.add(account)

        }
        accounts
    }

}
