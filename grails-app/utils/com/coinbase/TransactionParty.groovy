package com.coinbase

import com.sun.deploy.security.ValidationState

/**
 * TransacgtionParties are not completely understood and the object below represents an interpretation
 * by analyzing the example responses. To and From are documented as being parties, but Buys are not documented,
 * however, they semm to fit the mold as it appears these types are unique:
 *
 * to - sending money to a resource
 * from - recieve money from a resource
 * buy - purchase more currency from fiat
 *
 * "to": {
 *     "id": "a6b4c2df-a62c-5d68-822a-dd4e2102e703",
 *     "resource": "user",
 *     "resource_path": "/v2/users/a6b4c2df-a62c-5d68-822a-dd4e2102e703"
 *
 */
class TransactionParty {

    String id
    String resource
    String resourcePath
    String email

}
