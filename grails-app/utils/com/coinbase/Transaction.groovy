package com.coinbase



/*

 --- Send aka Debit --
{
  "id": "57ffb4ae-0c59-5430-bcd3-3f98f797a66c",
  "type": "send",
  "status": "completed",
  "amount": {
    "amount": "-0.00100000",
    "currency": "BTC"
  },
  "native_amount": {
    "amount": "-0.01",
    "currency": "USD"
  },
  "description": null,
  "created_at": "2015-03-11T13:13:35-07:00",
  "updated_at": "2015-03-26T15:55:43-07:00",
  "resource": "transaction",
  "resource_path": "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/57ffb4ae-0c59-5430-bcd3-3f98f797a66c",

  "network": {
    "status": "off_blockchain",
    "name": "bitcoin"
  },
  "to": {
    "id": "a6b4c2df-a62c-5d68-822a-dd4e2102e703",
    "resource": "user",
    "resource_path": "/v2/users/a6b4c2df-a62c-5d68-822a-dd4e2102e703"
  },

  "instant_exchange": false,
  "details": {
    "title": "Sent bitcoin",
    "subtitle": "to User 2"
  }

  --- Buy aka Credit ---
  {
  "id": "8250fe29-f5ef-5fc5-8302-0fbacf6be51e",
  "type": "buy",
  "status": "pending",
  "amount": {
    "amount": "1.00000000",
    "currency": "BTC"
  },
  "native_amount": {
    "amount": "10.00",
    "currency": "USD"
  },
  "description": null,
  "created_at": "2015-03-26T13:42:00-07:00",
  "updated_at": "2015-03-26T15:55:45-07:00",
  "resource": "transaction",
  "resource_path": "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/transactions/8250fe29-f5ef-5fc5-8302-0fbacf6be51e",

  "buy": {
    "id": "5c8216e7-318a-50a5-91aa-2f2cfddfdaab",
    "resource": "buy",
    "resource_path": "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede/buys/5c8216e7-318a-50a5-91aa-2f2cfddfdaab"
  },
  "instant_exchange": false,
  "details": {
    "title": "Bought bitcoin",
    "subtitle": "using Capital One Bank"
  }
}
}
*/

class Transaction {
    String id;
    String type
    String status
    Amount amount
    Amount nativeAmount
    String description
    Date createdAt
    Date updatedAt
    String resource
    String resourcePath
    boolean instantExchange
    Details details

    Network network
    TransactionParty party
    
}
