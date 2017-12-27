package com.coinbase

/**
 * Parse a Coinbase timestamp
 *
 * 	Ex. 2017-06-18T11:11:40Z
 */

class CoinbaseDateParser {

    static final String DATE_FORMAT= "yyyy-MM-dd HH:mm:ss"

    static Date parseDate(String cbDate){
        String dateString = cbDate.replace("T", " ").replace("Z", "")
        new Date().parse(DATE_FORMAT, dateString)
    }
}
