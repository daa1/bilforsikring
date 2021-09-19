package no.david.gateway.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "endpoints.brevtjeneste")
class BrevtjenesteConfig {
    var host: String = ""
    val uri = Uri()

    class Uri {
        val sendAvtaleTilKunde: String = "/brevtjeneste/api/v1/avtale"
    }
}