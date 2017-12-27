package coinbase.rest

import com.coinbase.AccessSignGenerator
import com.coinbase.Account
import com.coinbase.AccountResponseParser
import com.coinbase.TimeResponseParser
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.transform.CompileDynamic

class AccountsService implements GrailsConfigurationAware {

    def timestampService
    String accessKey
    String coinbaseUrl
    String accountsResource
    String version
    String secret

    @Override
    void setConfiguration(Config co) {
        accessKey = co.getProperty('coinbase.accessKey')
        coinbaseUrl = co.getProperty('coinbase.url')
        accountsResource = co.getProperty('coinbase.accountsResource')
        version = co.getProperty('coinbase.version')
        secret = co.getProperty('coinbase.secret')
    }

    @CompileDynamic
    List<Account> getAccounts (){

        RestBuilder rest = new RestBuilder()
        String url = "${coinbaseUrl}${accountsResource}"
        Long epoch = timestampService.getEpoch()
        String sign = AccessSignGenerator.generateAccessSign(secret, epoch, 'GET', accountsResource, '')

        RestResponse response = rest.get(url) {
            header 'CB-ACCESS-KEY', this.accessKey
            header 'CB-VERSION', this.version
            header 'CB-ACCESS-TIMESTAMP', "${epoch}"
            header 'CB-ACCESS-SIGN', sign
        }

        AccountResponseParser.accountsFromJsonElement(response.json)

    }

}
