package no.david.fagsystem.web

import no.david.fagsystem.model.*
import no.david.fagsystem.model.Kunde
import no.david.fagsystem.repository.AvtaleRepository
import no.david.fagsystem.repository.KundeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/fagsystem/api/v1/")
class FagsystemController(
    @Autowired private val avtaleRepository: AvtaleRepository,
    @Autowired private val kundeRepository: KundeRepository
) {

    @GetMapping(value = ["/kunde"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun hentKunder(): ResponseEntity<List<Kunde>> {
        return ResponseEntity.ok(kundeRepository.findAll().toList())
    }

    @PostMapping(value = ["/kunde"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun opprettKunde(@RequestBody body: OpprettKunde): ResponseEntity<Kunde> {
        val kunde: Kunde = kundeRepository.save(Kunde(body.fodselsnummer, body.fornavn, body.etternavn, body.epost))
        return ResponseEntity.ok(kunde)
    }

    @PostMapping(value = ["/avtale"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun opprettAvtale(@RequestBody body: OpprettAvtale): ResponseEntity<Avtale> {
        val kunde: Optional<Kunde> = kundeRepository.findById(body.kundeId)

        return if (kunde.isPresent) {
            val avtale: Avtale = avtaleRepository.save(Avtale(body.registreringsnummer, body.bonus, "Opprettet", kunde.get()))
            ResponseEntity.ok(avtale)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping(value = ["/avtale/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun hentAvtale(@PathVariable id: Long): ResponseEntity<Avtale> {
        val avtale = avtaleRepository.findById(id)
        return if (avtale.isPresent) {
            ResponseEntity.ok(avtale.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

}