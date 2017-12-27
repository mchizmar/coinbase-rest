package coinbase.rest

import com.coinbase.AccessSignGenerator
import com.coinbase.Account
import com.coinbase.AddressResponseParser
import com.coinbase.TransactionLedger
import com.coinbase.TransactionResponseParser
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.transform.CompileDynamic

@Transactional
class TransactionsService implements GrailsConfigurationAware{

    def timestampService
    String accessKey
    String coinbaseUrl
    String transactionsResource
    String version
    String secret

    @Override
    void setConfiguration(Config co) {
        accessKey = co.getProperty('coinbase.accessKey')
        coinbaseUrl = co.getProperty('coinbase.url')
        transactionsResource = co.getProperty('coinbase.transactionsResource')
        version = co.getProperty('coinbase.version')
        secret = co.getProperty('coinbase.secret')
    }

    @CompileDynamic
    TransactionLedger getTransactions(accountId){

        if (!accountId){
            return null
        }

        String transactionResourceWithAccount = transactionsResource.replaceAll(":account_id", accountId)
        RestBuilder rest = new RestBuilder()
        String url = "${coinbaseUrl}${transactionResourceWithAccount}"
        Long epoch = timestampService.getEpoch()
        String sign = AccessSignGenerator.generateAccessSign(secret, epoch, 'GET', transactionResourceWithAccount, '')

        RestResponse response = rest.get(url) {
            header 'CB-ACCESS-KEY', this.accessKey
            header 'CB-VERSION', this.version
            header 'CB-ACCESS-TIMESTAMP', "${epoch}"
            header 'CB-ACCESS-SIGN', sign
        }

        TransactionResponseParser.transactionsFromJsonElement(response.json)
    }

    @CompileDynamic
    TransactionLedger getTransaction(accountId, transactionId) {

        if (!accountId || !transactionId){
            return null
        }

        String transactionResourceWithAccountAndTransaction = transactionsResource.replaceAll(":account_id", accountId).replace(":transaction_id", transactionId)

        RestBuilder rest = new RestBuilder()
        String url = "${coinbaseUrl}${transactionResourceWithAccountAndTransaction}"
        Long epoch = timestampService.getEpoch()
        String sign = AccessSignGenerator.generateAccessSign(secret, epoch, 'GET', transactionResourceWithAccountAndTransaction, '')

        RestResponse response = rest.get(url) {
            header 'CB-ACCESS-KEY', this.accessKey
            header 'CB-VERSION', this.version
            header 'CB-ACCESS-TIMESTAMP', "${epoch}"
            header 'CB-ACCESS-SIGN', sign
        }

        TransactionResponseParser.transactionsFromJsonElement(response.json)

    }

    
}
