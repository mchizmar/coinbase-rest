package coinbase.rest

import com.coinbase.AccessSignGenerator
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class AccountsServiceIntSpec extends Specification {

    def timestampService
    def accountsService


    void "Get my existing accounts from coinbase." () {

        def accounts

        when:
        accounts = accountsService.getAccounts()

        then:
        accounts.size() > 0
    }
}
