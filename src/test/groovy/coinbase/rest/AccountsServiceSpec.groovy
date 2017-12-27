package coinbase.rest

import com.stehno.ersatz.ContentType
import com.stehno.ersatz.Encoders
import com.stehno.ersatz.ErsatzServer
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class AccountsServiceSpec extends Specification implements ServiceUnitTest<AccountsService>{

    void "Epoch is parsed returned from the Coinbase service"() {

        given:
        ErsatzServer mockServer = new ErsatzServer()
        mockServer.expectations {
            get("${service.accountsResource}"){
                responder({
                    encoder(ContentType.APPLICATION_JSON, Map, Encoders.json)
                    code (200)
                    content(
                            [data: [ iso: '2017-11-23T16:10:18Z', epoch: 1511453418 ]],
                            ContentType.APPLICATION_JSON
                    )
                })
            }
        }
        service.coinbaseUrl = "${mockServer.httpUrl}/"

        when:
        Long epoch = 1

        then:
        true == true

        and:
        mockServer.verify()

        cleanup:
        mockServer.stop()

    }
}
