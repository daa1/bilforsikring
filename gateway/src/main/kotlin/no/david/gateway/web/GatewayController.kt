package no.david.gateway.web

import no.david.gateway.model.BestillBilforsikring
import no.david.gateway.model.brevtjeneste.SendAvtaleTilKunde
import no.david.gateway.model.fagsystem.Avtale
import no.david.gateway.model.fagsystem.Kunde
import no.david.gateway.model.fagsystem.OpprettAvtale
import no.david.gateway.model.fagsystem.OpprettKunde
import no.david.gateway.service.BrevtjenesteService
import no.david.gateway.service.FagsystemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/gateway/api/v1/")
class GatewayController(
    @Autowired val brevtjenesteService: BrevtjenesteService,
    @Autowired val fagsystemService: FagsystemService
) {
    @PostMapping(value = ["/bilforsikring"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun bestillBilforsikring(@RequestBody body: BestillBilforsikring): ResponseEntity<Avtale> {
        val kunde: Kunde? = fagsystemService.opprettKunde(OpprettKunde(body.fodselsnummer, body.fornavn, body.etternavn, body.epost))

        val avtale: Avtale?
        if (kunde != null) {
            avtale = fagsystemService.opprettAvtale(OpprettAvtale(body.registereringsnummer, body.bonus, kunde.id))
        } else {
            // TODO: mer fornuftig errorbeskjed om at bruker ikke ble opprettet
            return ResponseEntity.internalServerError().build()
        }

        if (avtale != null) {
            // TODO: eventuell error håndtering/retry dersom denne feiler
            brevtjenesteService.sendAvtaleTilKunde(SendAvtaleTilKunde(kunde.id, avtale.id))
        } else {
            // TODO: mer fornuftig errorbeskjed om at avtalen ikke ble opprettet
            return ResponseEntity.internalServerError().build()
        }

        return ResponseEntity.ok(avtale)
    }

    @GetMapping(value = ["/bilforsikring/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBilforsikring(@PathVariable id: Long): ResponseEntity<Avtale> {
        val avtale: Avtale? = fagsystemService.getAvtale(id)

        return if (avtale != null) {
            ResponseEntity.ok(avtale)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}