package com.coinbase

import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class TransactionResponseParserSpec extends Specification implements GrailsUnitTest {


    void "Buys parsed from a JSON reponse"() {

        given:
        def transactionResponse =
                [   data: [
                        [
                            id: "4117f7d6-5694-5b36-bc8f-847509850ea4",
                            type: "buy",
                            status: "pending",
                            amount: [
                                amount: "486.34313725",
                                currency: "BTC"
                            ],
                            native_amount: [
                                amount: "4863.43",
                                currency: "USD"
                            ],
                            description: null,
                            created_at: "2015-03-26T23:44:08-07:00",
                            updated_at: "2015-03-26T23:44:08-07:00",
                            resource: "transaction",
                            resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/4117f7d6-5694-5b36-bc8f-847509850ea4",
                            buy: [
                                id: "9e14d574-30fa-5d85-b02c-6be0d851d61d",
                                resource: "buy",
                                resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/buys/9e14d574-30fa-5d85-b02c-6be0d851d61d"
                            ],
                            "details": [
                                "title": "Bought bitcoin",
                                "subtitle": "using Capital One Bank"
                            ]
                        ]

                    ]
                ]

        when:
        def ledger = TransactionResponseParser.transactionsFromJsonElement(transactionResponse)

        then:
        ledger.buys.size() == 1
        BuyTransaction buy = ledger.buys.get(0)
        buy.id == "4117f7d6-5694-5b36-bc8f-847509850ea4"
        buy.type == "buy"
        buy.status == "pending"
        buy.amount.amount == 486.34313725
        buy.amount.currency == "BTC"
        buy.nativeAmount.amount == 4863.43
        buy.nativeAmount.currency == "USD"
        buy.description == null
        buy.createdAt == CoinbaseDateParser.parseDate("2015-03-26T23:44:08-07:00")
        buy.updatedAt == CoinbaseDateParser.parseDate("2015-03-26T23:44:08-07:00")
        buy.resource == "transaction"
        buy.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/4117f7d6-5694-5b36-bc8f-847509850ea4"
        buy.party != null
        buy.party.id == "9e14d574-30fa-5d85-b02c-6be0d851d61d"
        buy.party.resource == "buy"
        buy.party.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/buys/9e14d574-30fa-5d85-b02c-6be0d851d61d"
        buy.details != null
        buy.details.title == "Bought bitcoin"
        buy.details.subtitle == "using Capital One Bank"

    }

    void "Requests are parsed from a JSON reponse"() {

        given:
        def transactionResponse =
                [   data: [
                        [
                            id: "005e55d1-f23a-5d1e-80a4-72943682c055",
                            type: "request",
                            status: "pending",
                            amount: [
                                amount: "0.10000000",
                                currency: "BTC"
                            ],
                            native_amount: [
                                amount: "1.00",
                                currency: "USD"
                            ],
                            description: null,
                            created_at: "2015-03-24T18:32:35-07:00",
                            updated_at: "2015-01-31T20:49:02Z",
                            resource: "transaction",
                            resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/005e55d1-f23a-5d1e-80a4-72943682c055",
                            to: [
                                resource: "email",
                                email: "rb@coinbase.com"
                            ],
                            details: [
                                title: "Requested bitcoin",
                                subtitle: "from rb@coinbase.com"
                            ]
                        ]

                ]
                ]

        when:
        def ledger = TransactionResponseParser.transactionsFromJsonElement(transactionResponse)

        then:
        ledger.requests.size() == 1
        RequestTransaction t = ledger.requests.get(0)
        t.id == "005e55d1-f23a-5d1e-80a4-72943682c055"
        t.type == "request"
        t.status == "pending"
        t.amount.amount == 0.10000000
        t.amount.currency == "BTC"
        t.nativeAmount.amount == 1.00
        t.nativeAmount.currency == "USD"
        t.description == null
        t.createdAt == CoinbaseDateParser.parseDate("2015-03-24T18:32:35-07:00")
        t.updatedAt == CoinbaseDateParser.parseDate("2015-01-31T20:49:02Z")
        t.resource == "transaction"
        t.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/005e55d1-f23a-5d1e-80a4-72943682c055"
        t.party != null
        t.party.resource == "email"
        t.party.email ==  "rb@coinbase.com"
        t.details != null
        t.details.title == "Requested bitcoin"
        t.details.subtitle == "from rb@coinbase.com"
    }

    void "Transfers are parsed from a JSON reponse"() {

        given:
        def transactionResponse =
                [   data: [
                        [
                        id: "ff01bbc6-c4ad-59e1-9601-e87b5b709458",
                        type: "transfer",
                        status: "completed",
                        amount: [
                            amount: "-5.00000000",
                            currency: "BTC"
                        ],
                        native_amount: [
                            amount: "-50.00",
                            currency: "USD"
                        ],
                        description: null,
                        created_at: "2015-03-12T15:51:38-07:00",
                        updated_at: "2015-01-31T20:49:02Z",
                        resource: "transaction",
                        resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/ff01bbc6-c4ad-59e1-9601-e87b5b709458",
                        to: [
                            id: "58542935-67b5-56e1-a3f9-42686e07fa40",
                            resource: "account",
                            resource_path: "/v2/accounts/58542935-67b5-56e1-a3f9-42686e07fa40"
                        ],
                        details: [
                            title: "Transfered bitcoin",
                            subtitle: "to Secondary Account"
                        ]
                    ]
                ]
                ]

        when:
        def ledger = TransactionResponseParser.transactionsFromJsonElement(transactionResponse)

        then:
        ledger.transfers.size() == 1
        TransferTransaction t = ledger.transfers.get(0)
        t.id == "ff01bbc6-c4ad-59e1-9601-e87b5b709458"
        t.type == "transfer"
        t.status == "completed"
        t.amount.amount == -5.00000000
        t.amount.currency == "BTC"
        t.nativeAmount.amount == -50.00
        t.nativeAmount.currency == "USD"
        t.description == null
        t.createdAt == CoinbaseDateParser.parseDate("2015-03-12T15:51:38-07:00")
        t.updatedAt == CoinbaseDateParser.parseDate("2015-01-31T20:49:02Z")
        t.resource == "transaction"
        t.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/ff01bbc6-c4ad-59e1-9601-e87b5b709458"
        t.party != null
        t.party.id == "58542935-67b5-56e1-a3f9-42686e07fa40"
        t.party.resource == "account"
        t.party.resourcePath == "/v2/accounts/58542935-67b5-56e1-a3f9-42686e07fa40"
        t.details != null
        t.details.title == "Transfered bitcoin"
        t.details.subtitle == "to Secondary Account"
    }

    void "Sends are parsed from a JSON reponse"() {

        given:
        def transactionResponse =
                [data: [
                        [
                        id: "57ffb4ae-0c59-5430-bcd3-3f98f797a66c",
                        type: "send",
                        status: "completed",
                        amount: [
                            amount: "-0.00100000",
                            currency: "BTC"
                        ],
                        native_amount: [
                            amount: "-0.01",
                            currency: "USD"
                        ],
                        description: null,
                        created_at: "2015-03-11T13:13:35-07:00",
                        updated_at: "2015-03-26T15:55:43-07:00",
                        resource: "transaction",
                        resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/57ffb4ae-0c59-5430-bcd3-3f98f797a66c",
                        network: [
                            status: "off_blockchain",
                            name: "bitcoin"
                        ],
                        to: [
                            id: "a6b4c2df-a62c-5d68-822a-dd4e2102e703",
                            resource: "user",
                            resource_path: "/v2/users/a6b4c2df-a62c-5d68-822a-dd4e2102e703"
                        ],
                        details: [
                            "title": "Send bitcoin",
                            "subtitle": "to User 2"
                        ]
                        ]
                ]
                ]

        when:
        def ledger = TransactionResponseParser.transactionsFromJsonElement(transactionResponse)

        then:
        ledger.sends.size() == 1
        SendTransaction t = ledger.sends.get(0)
        t.id == "57ffb4ae-0c59-5430-bcd3-3f98f797a66c"
        t.type == "send"
        t.status == "completed"
        t.amount.amount == -0.00100000
        t.amount.currency == "BTC"
        t.nativeAmount.amount == -0.01
        t.nativeAmount.currency == "USD"
        t.description == null
        t.createdAt == CoinbaseDateParser.parseDate("2015-03-11T13:13:35-07:00")
        t.updatedAt == CoinbaseDateParser.parseDate("2015-03-26T15:55:43-07:00")
        t.resource == "transaction"
        t.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/57ffb4ae-0c59-5430-bcd3-3f98f797a66c"
        t.network.status  == "off_blockchain"
        t.network.name == "bitcoin"
        t.party != null
        t.party.id == "a6b4c2df-a62c-5d68-822a-dd4e2102e703"
        t.party.resource == "user"
        t.party.resourcePath == "/v2/users/a6b4c2df-a62c-5d68-822a-dd4e2102e703"
        t.details != null
        t.details.title == "Send bitcoin"
        t.details.subtitle == "to User 2"
    }

    void "All known transaction types are parsed from a JSON reponse"() {

        given:
        def transactionResponse =
                [data: [
                        [
                                id: "57ffb4ae-0c59-5430-bcd3-3f98f797a66c",
                                type: "send",
                                status: "completed",
                                amount: [
                                        amount: "-0.00100000",
                                        currency: "BTC"
                                ],
                                native_amount: [
                                        amount: "-0.01",
                                        currency: "USD"
                                ],
                                description: null,
                                created_at: "2015-03-11T13:13:35-07:00",
                                updated_at: "2015-03-26T15:55:43-07:00",
                                resource: "transaction",
                                resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/57ffb4ae-0c59-5430-bcd3-3f98f797a66c",
                                network: [
                                        status: "off_blockchain",
                                        name: "bitcoin"
                                ],
                                to: [
                                        id: "a6b4c2df-a62c-5d68-822a-dd4e2102e703",
                                        resource: "user",
                                        resource_path: "/v2/users/a6b4c2df-a62c-5d68-822a-dd4e2102e703"
                                ],
                                details: [
                                        "title": "Send bitcoin",
                                        "subtitle": "to User 2"
                                ]
                        ],
                        [
                                id: "ff01bbc6-c4ad-59e1-9601-e87b5b709458",
                                type: "transfer",
                                status: "completed",
                                amount: [
                                        amount: "-5.00000000",
                                        currency: "BTC"
                                ],
                                native_amount: [
                                        amount: "-50.00",
                                        currency: "USD"
                                ],
                                description: null,
                                created_at: "2015-03-12T15:51:38-07:00",
                                updated_at: "2015-01-31T20:49:02Z",
                                resource: "transaction",
                                resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/ff01bbc6-c4ad-59e1-9601-e87b5b709458",
                                to: [
                                        id: "58542935-67b5-56e1-a3f9-42686e07fa40",
                                        resource: "account",
                                        resource_path: "/v2/accounts/58542935-67b5-56e1-a3f9-42686e07fa40"
                                ],
                                details: [
                                        title: "Transfered bitcoin",
                                        subtitle: "to Secondary Account"
                                ]
                        ],
                        [
                                id: "4117f7d6-5694-5b36-bc8f-847509850ea4",
                                type: "buy",
                                status: "pending",
                                amount: [
                                        amount: "486.34313725",
                                        currency: "BTC"
                                ],
                                native_amount: [
                                        amount: "4863.43",
                                        currency: "USD"
                                ],
                                description: null,
                                created_at: "2015-03-26T23:44:08-07:00",
                                updated_at: "2015-03-26T23:44:08-07:00",
                                resource: "transaction",
                                resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/4117f7d6-5694-5b36-bc8f-847509850ea4",
                                buy: [
                                        id: "9e14d574-30fa-5d85-b02c-6be0d851d61d",
                                        resource: "buy",
                                        resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/buys/9e14d574-30fa-5d85-b02c-6be0d851d61d"
                                ],
                                "details": [
                                        "title": "Bought bitcoin",
                                        "subtitle": "using Capital One Bank"
                                ]
                        ],
                        [
                                id: "005e55d1-f23a-5d1e-80a4-72943682c055",
                                type: "request",
                                status: "pending",
                                amount: [
                                        amount: "0.10000000",
                                        currency: "BTC"
                                ],
                                native_amount: [
                                        amount: "1.00",
                                        currency: "USD"
                                ],
                                description: null,
                                created_at: "2015-03-24T18:32:35-07:00",
                                updated_at: "2015-01-31T20:49:02Z",
                                resource: "transaction",
                                resource_path: "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/005e55d1-f23a-5d1e-80a4-72943682c055",
                                to: [
                                        resource: "email",
                                        email: "rb@coinbase.com"
                                ],
                                details: [
                                        title: "Requested bitcoin",
                                        subtitle: "from rb@coinbase.com"
                                ]
                        ]

                ]
                ]

        when:
        def ledger = TransactionResponseParser.transactionsFromJsonElement(transactionResponse)

        then:
        ledger.sends.size() == 1
        SendTransaction t = ledger.sends.get(0)
        t.id == "57ffb4ae-0c59-5430-bcd3-3f98f797a66c"
        t.type == "send"
        t.status == "completed"
        t.amount.amount == -0.00100000
        t.amount.currency == "BTC"
        t.nativeAmount.amount == -0.01
        t.nativeAmount.currency == "USD"
        t.description == null
        t.createdAt == CoinbaseDateParser.parseDate("2015-03-11T13:13:35-07:00")
        t.updatedAt == CoinbaseDateParser.parseDate("2015-03-26T15:55:43-07:00")
        t.resource == "transaction"
        t.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/57ffb4ae-0c59-5430-bcd3-3f98f797a66c"
        t.network.status  == "off_blockchain"
        t.network.name == "bitcoin"
        t.party != null
        t.party.id == "a6b4c2df-a62c-5d68-822a-dd4e2102e703"
        t.party.resource == "user"
        t.party.resourcePath == "/v2/users/a6b4c2df-a62c-5d68-822a-dd4e2102e703"
        t.details != null
        t.details.title == "Send bitcoin"
        t.details.subtitle == "to User 2"

        ledger.buys.size() == 1
        BuyTransaction buy = ledger.buys.get(0)
        buy.id == "4117f7d6-5694-5b36-bc8f-847509850ea4"
        buy.type == "buy"
        buy.status == "pending"
        buy.amount.amount == 486.34313725
        buy.amount.currency == "BTC"
        buy.nativeAmount.amount == 4863.43
        buy.nativeAmount.currency == "USD"
        buy.description == null
        buy.createdAt == CoinbaseDateParser.parseDate("2015-03-26T23:44:08-07:00")
        buy.updatedAt == CoinbaseDateParser.parseDate("2015-03-26T23:44:08-07:00")
        buy.resource == "transaction"
        buy.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/4117f7d6-5694-5b36-bc8f-847509850ea4"
        buy.party != null
        buy.party.id == "9e14d574-30fa-5d85-b02c-6be0d851d61d"
        buy.party.resource == "buy"
        buy.party.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/buys/9e14d574-30fa-5d85-b02c-6be0d851d61d"
        buy.details != null
        buy.details.title == "Bought bitcoin"
        buy.details.subtitle == "using Capital One Bank"

        ledger.requests.size() == 1
        RequestTransaction rt = ledger.requests.get(0)
        rt.id == "005e55d1-f23a-5d1e-80a4-72943682c055"
        rt.type == "request"
        rt.status == "pending"
        rt.amount.amount == 0.10000000
        rt.amount.currency == "BTC"
        rt.nativeAmount.amount == 1.00
        rt.nativeAmount.currency == "USD"
        rt.description == null
        rt.createdAt == CoinbaseDateParser.parseDate("2015-03-24T18:32:35-07:00")
        rt.updatedAt == CoinbaseDateParser.parseDate("2015-01-31T20:49:02Z")
        rt.resource == "transaction"
        rt.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/005e55d1-f23a-5d1e-80a4-72943682c055"
        rt.party != null
        rt.party.resource == "email"
        rt.party.email ==  "rb@coinbase.com"
        rt.details != null
        rt.details.title == "Requested bitcoin"
        rt.details.subtitle == "from rb@coinbase.com"

        ledger.transfers.size() == 1
        TransferTransaction tt = ledger.transfers.get(0)
        tt.id == "ff01bbc6-c4ad-59e1-9601-e87b5b709458"
        tt.type == "transfer"
        tt.status == "completed"
        tt.amount.amount == -5.00000000
        tt.amount.currency == "BTC"
        tt.nativeAmount.amount == -50.00
        tt.nativeAmount.currency == "USD"
        tt.description == null
        tt.createdAt == CoinbaseDateParser.parseDate("2015-03-12T15:51:38-07:00")
        tt.updatedAt == CoinbaseDateParser.parseDate("2015-01-31T20:49:02Z")
        tt.resource == "transaction"
        tt.resourcePath ==  "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/ff01bbc6-c4ad-59e1-9601-e87b5b709458"
        tt.party != null
        tt.party.id == "58542935-67b5-56e1-a3f9-42686e07fa40"
        tt.party.resource == "account"
        tt.party.resourcePath == "/v2/accounts/58542935-67b5-56e1-a3f9-42686e07fa40"
        tt.details != null
        tt.details.title == "Transfered bitcoin"
        tt.details.subtitle == "to Secondary Account"



    }

}