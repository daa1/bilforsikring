package no.david.gateway.service

import no.david.gateway.config.FagsystemConfig
import no.david.gateway.model.fagsystem.Avtale
import no.david.gateway.model.fagsystem.Kunde
import no.david.gateway.model.fagsystem.OpprettAvtale
import no.david.gateway.model.fagsystem.OpprettKunde
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class FagsystemService(@Autowired val fagsystemConfig: FagsystemConfig) {

    fun opprettKunde(body: OpprettKunde): Kunde? {
        return WebClient.create(fagsystemConfig.host)
            .post()
            .uri(fagsystemConfig.uri.opprettKunde)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(Kunde::class.java)
            .block()
    }

    fun opprettAvtale(body: OpprettAvtale): Avtale? {
        return WebClient.create(fagsystemConfig.host)
            .post()
            .uri(fagsystemConfig.uri.opprettAvtale)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(Avtale::class.java)
            .block()
    }

    fun getAvtale(avtaleId: Long): Avtale? {
        return WebClient.create(fagsystemConfig.host)
            .get()
            .uri { uriBuilder -> uriBuilder
                .path(fagsystemConfig.uri.getAvtale)
                .build(avtaleId)
            }
            .retrieve()
            .bodyToMono(Avtale::class.java)
            .block()
    }
}