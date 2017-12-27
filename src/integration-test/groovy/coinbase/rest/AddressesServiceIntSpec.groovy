package coinbase.rest

import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class AddressesServiceIntSpec extends Specification {

    def addressesService
    def accountsService 


    void "Get my existing addresses from coinbase." () {

        def accounts
        def addresses

        when:
        accounts = accountsService.getAccounts()
        addresses = addressesService.getAddresses(accounts[0].id)

        then:
        addresses.size() > 0

    }
}
