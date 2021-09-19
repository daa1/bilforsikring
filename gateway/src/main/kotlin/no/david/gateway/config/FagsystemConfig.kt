package no.david.gateway.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "endpoints.fagsystem")
class FagsystemConfig {
    var host: String = ""
    val uri = Uri()

    class Uri {
        val opprettKunde: String = "/fagsystem/api/v1/kunde"
        val opprettAvtale: String = "/fagsystem/api/v1/avtale"
        val getAvtale: String = "/fagsystem/api/v1/avtale/{id}"
    }
}