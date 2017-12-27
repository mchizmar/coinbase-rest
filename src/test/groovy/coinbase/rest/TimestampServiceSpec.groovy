package coinbase.rest

import com.stehno.ersatz.ContentType
import com.stehno.ersatz.Encoders
import com.stehno.ersatz.ErsatzServer
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class TimestampServiceSpec extends Specification implements ServiceUnitTest<TimestampService>{



    void "Epoch is returned from the Coinbase service"() {

        given:
        ErsatzServer mockServer = new ErsatzServer()
        mockServer.expectations {
            get("${service.timeResource}"){
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
        Long epoch = service.getEpoch()

        then:
        epoch == 1511453418

        and:
        mockServer.verify()

        cleanup:
        mockServer.stop()

    }
}
