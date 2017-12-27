package coinbase.rest

import com.coinbase.CoinbaseTime
import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class TimestampServiceIntSpec extends Specification {

    def timestampService

    void "Full time  is returned from Coinbase"() {

        CoinbaseTime time

        given:
        time = timestampService.getFullTime()

        expect:
        time.epoch >  1511706083
        time.iso != null
        
    }

    void "Epoch is returned from Coinbase"() {

        expect:
        timestampService.getEpoch() > 1511706083
    }


}
