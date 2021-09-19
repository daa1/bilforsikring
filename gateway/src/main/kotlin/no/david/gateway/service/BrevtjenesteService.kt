package no.david.gateway.service

import no.david.gateway.config.BrevtjenesteConfig
import no.david.gateway.model.brevtjeneste.SendAvtaleTilKunde
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class BrevtjenesteService(@Autowired val brevtjenesteConfig: BrevtjenesteConfig) {

    fun sendAvtaleTilKunde(body: SendAvtaleTilKunde): String {
        return WebClient.create(brevtjenesteConfig.host)
            .post()
            .uri(brevtjenesteConfig.uri.sendAvtaleTilKunde)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String::class.java)
            .block().orEmpty()
    }
}