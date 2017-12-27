package coinbase.rest

import com.coinbase.CoinbaseTime
import com.coinbase.TimeResponseParser
import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional
import grails.config.Config
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.transform.CompileDynamic

@Transactional
class TimestampService implements GrailsConfigurationAware {

    String accessKey
    String url
    String timeResource
    String version

    @Override
    void setConfiguration(Config co) {
        accessKey = co.getProperty('coinbase.accessKey', String)
        url = co.getProperty('coinbase.url', String)
        timeResource = co.getProperty('coinbase.timeResource', String)
        version = co.getProperty('coinbase.version', String )
    }

    @CompileDynamic
    CoinbaseTime getFullTime (){
        RestBuilder rest = new RestBuilder()
        String url = "${url}${timeResource}"

        RestResponse response = rest.get(url) {
            header 'CB-ACCESS-KEY', this.accessKey
            header 'CB-VERSION', this.version
        }

        TimeResponseParser.timeFromJsonElement(response.json)
    }

    @CompileDynamic
    Long getEpoch (){
        getFullTime().epoch
    }
}
