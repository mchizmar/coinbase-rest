package com.coinbase

//"data":[{"id":"513b40a9-798b-5199-bde2-f4e2e8022838","name":"ETH Wallet","primary":false,"type":"wallet","currency":{"code":"ETH","name":"Ethereum","color":"#6F7CBA","exponent":8,"type":"crypto"},"balance":{"amount":"3.63197154","currency":"ETH"},"created_at":"2017-06-18T11:11:40Z","updated_at":"2017-11-08T17:09:34Z","resource":"account","resource_path":"/v2/accounts/513b40a9-798b-5199-bde2-f4e2e8022838","native_balance":{"amount":"1717.21","currency":"USD"}},{"id":"1d3be271-2355-5351-99b4-4fe293bab997","name":"BTC Wallet","primary":true,"type":"wallet","currency":{"code":"BTC","name":"Bitcoin","color":"#FFB119","exponent":8,"type":"crypto"},"balance":{"amount":"0.18607110","currency":"BTC"},"created_at":"2017-06-18T11:11:40Z","updated_at":"2017-10-27T18:16:24Z","resource":"account","resource_path":"/v2/accounts/1d3be271-2355-5351-99b4-4fe293bab997","native_balance":{"amount":"1756.51","currency":"USD"}}]}
class Account {

    String id;
    String name
    boolean primary
    String type
    Currency currency
    Balance balance
    Date createdAt
    Date updatedAt
    String resource
    String resourcePath
    Balance nativeBalance

}
