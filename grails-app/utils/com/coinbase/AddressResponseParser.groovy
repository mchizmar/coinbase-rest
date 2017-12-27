package com.coinbase

import groovy.transform.CompileDynamic

class AddressResponseParser {
    @CompileDynamic
    static List<Account> addressesFromJsonElement(json){

        def addresses = []
        json.data.each{ address ->
            Address a = new Address()
            a.id = address.id
            a.address = address.address
            a.name = address.name
            a.createdDate = CoinbaseDateParser.parseDate(address.created_at)
            a.updatedDate = CoinbaseDateParser.parseDate(address.updated_at)
            a.network = address.network
            a.resource = address.resource
            a.resourcePath = address.resource_path

            addresses.add(a)
        }
        addresses
    }


}

