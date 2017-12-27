package coinbase.rest

import com.coinbase.AccessSignGenerator
import com.coinbase.Account
import com.coinbase.AccountResponseParser
import com.coinbase.AddressResponseParser
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.transform.CompileDynamic

class AddressesService implements GrailsConfigurationAware {

    def timestampService
    String accessKey
    String coinbaseUrl
    String addressesResource
    String version
    String secret

    @Override
    void setConfiguration(Config co) {
        accessKey = co.getProperty('coinbase.accessKey')
        coinbaseUrl = co.getProperty('coinbase.url')
        addressesResource = co.getProperty('coinbase.addressesResource')
        version = co.getProperty('coinbase.version')
        secret = co.getProperty('coinbase.secret')
    }

    @CompileDynamic
    List<Account> getAddresses(accountId){

        if (!accountId){
            return []
        }

        String addressResourceWithAccount = addressesResource.replaceAll(":account_id", accountId)
        RestBuilder rest = new RestBuilder()
        String url = "${coinbaseUrl}${addressResourceWithAccount}"
        Long epoch = timestampService.getEpoch()
        String sign = AccessSignGenerator.generateAccessSign(secret, epoch, 'GET', addressResourceWithAccount, '')

        RestResponse response = rest.get(url) {
            header 'CB-ACCESS-KEY', this.accessKey
            header 'CB-VERSION', this.version
            header 'CB-ACCESS-TIMESTAMP', "${epoch}"
            header 'CB-ACCESS-SIGN', sign
        }

        AddressResponseParser.addressesFromJsonElement(response.json)

    }

}
