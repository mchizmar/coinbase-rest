package com.coinbase

import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class AddressResponseParserSpec extends Specification implements GrailsUnitTest {


    /*
        Javascript output
        //"data":[{"id":"513b40a9-798b-5199-bde2-f4e2e8022838","name":"ETH Wallet","primary":false,"type":"wallet","currency":{"code":"ETH","name":"Ethereum","color":"#6F7CBA","exponent":8,"type":"crypto"},"balance":{"amount":"3.63197154","currency":"ETH"},"created_at":"2017-06-18T11:11:40Z","updated_at":"2017-11-08T17:09:34Z","resource":"account","resource_path":"/v2/accounts/513b40a9-798b-5199-bde2-f4e2e8022838","native_balance":{"amount":"1717.21","currency":"USD"}},{"id":"1d3be271-2355-5351-99b4-4fe293bab997","name":"BTC Wallet","primary":true,"type":"wallet","currency":{"code":"BTC","name":"Bitcoin","color":"#FFB119","exponent":8,"type":"crypto"},"balance":{"amount":"0.18607110","currency":"BTC"},"created_at":"2017-06-18T11:11:40Z","updated_at":"2017-10-27T18:16:24Z","resource":"account","resource_path":"/v2/accounts/1d3be271-2355-5351-99b4-4fe293bab997","native_balance":{"amount":"1756.51","currency":"USD"}}]}
        {
          "pagination": {
            "ending_before": null,
            "starting_after": null,
            "limit": 25,
            "order": "desc",
            "previous_uri": null,
            "next_uri": null
          },
          "data": [
            {
              "id": "dd3183eb-af1d-5f5d-a90d-cbff946435ff",
              "address": "mswUGcPHp1YnkLCgF1TtoryqSc5E9Q8xFa",
              "name": null,
              "created_at": "2015-01-31T20:49:02Z",
              "updated_at": "2015-03-31T17:25:29-07:00",
              "network": "bitcoin",
              "resource": "address",
              "resource_path": "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/addresses/dd3183eb-af1d-5f5d-a90d-cbff946435ff"
            },
            {
              "id": "ac5c5f15-0b1d-54f5-8912-fecbf66c2a64",
              "address": "mgSvu1z1amUFAPkB4cUg8ujaDxKAfZBt5Q",
              "name": null,
              "created_at": "2015-03-31T17:23:52-07:00",
              "updated_at": "2015-01-31T20:49:02Z",
              "network": "bitcoin",
              "resource": "address",
              "resource_path": "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/addresses/ac5c5f15-0b1d-54f5-8912-fecbf66c2a64"
            }
          ]
        }

     */


    void "List the addresses are parsed from a JSON reponse"() {

        given:
        def addressResponse =
                [data: [

                        [id: "dd3183eb-af1d-5f5d-a90d-cbff946435ff",
                        address: "mswUGcPHp1YnkLCgF1TtoryqSc5E9Q8xFa",
                        name: "foo",
                        created_at: "2015-01-31T20:49:02Z",
                        updated_at: "2015-03-31T17:25:29-07:00",
                        network: "bitcoin",
                        resource: "address",
                        resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/addresses/dd3183eb-af1d-5f5d-a90d-cbff946435ff"],

                        [id: "dd3183eb-af1d-5f5d-a90d-cbff946435ff2",
                        address: "mswUGcPHp1YnkLCgF1TtoryqSc5E9Q8xFa2",
                        name: "foo2",
                        created_at: "2015-01-31T20:49:02Z",
                        updated_at: "2015-03-31T17:25:29-07:00",
                        network: "bitcoin",
                        resource: "address",
                        resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/addresses/dd3183eb-af1d-5f5d-a90d-cbff946435ff"]
                    ]
                ]

        when:
        def addresses = AddressResponseParser.addressesFromJsonElement(addressResponse)
        addresses.size() == 2
        def a = addresses[0]

        then:
        a.id == "dd3183eb-af1d-5f5d-a90d-cbff946435ff"
        a.address == "mswUGcPHp1YnkLCgF1TtoryqSc5E9Q8xFa"
        a.name == "foo"
        a.createdDate == CoinbaseDateParser.parseDate("2015-01-31T20:49:02Z")
        a.updatedDate == CoinbaseDateParser.parseDate("2015-03-31T17:25:29-07:00")
        a.network == "bitcoin"
        a.resource == "address"
        a.resourcePath == "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/addresses/dd3183eb-af1d-5f5d-a90d-cbff946435ff"
    }

    void "test replace :account_id in url" (){
        when:
        def url = "https://api.coinbase.com/v2/accounts/:account_id/addresses"

        then:
        url.replaceAll(":account_id", "foo") == "https://api.coinbase.com/v2/accounts/foo/addresses"


    }
}