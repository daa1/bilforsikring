package no.david.brevtjeneste.web

import no.david.brevtjeneste.model.Avtale
import no.david.brevtjeneste.model.AvtaleStatus
import no.david.brevtjeneste.model.SendAvtaleTilKunde
import no.david.brevtjeneste.service.BrevtjenesteService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime

@RestController
@RequestMapping("/brevtjeneste/api/v1/")
class BrevtjenesteController {

    @PostMapping(value = ["/avtale"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sendAvtaleTilKunde(@RequestBody body: SendAvtaleTilKunde): ResponseEntity<Avtale> {
        val avtale = Avtale(BrevtjenesteService.collection.size + 1L, body.kundeId, ZonedDateTime.now(), AvtaleStatus.RECEIVED)
        BrevtjenesteService.collection.add(avtale)

        return ResponseEntity.ok(avtale)
    }

    @GetMapping(value = ["/avtale/{avtaleId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAvtale(@PathVariable avtaleId: Long): ResponseEntity<Avtale> {
        val avtale = BrevtjenesteService.collection.find { avtale -> avtale.id == avtaleId }

        if (avtale != null) {
            val now = ZonedDateTime.now()
            // Supernaiv implementasjon som later som at noe har skjedd med status på avtale basert på tid
            if (avtale.status == AvtaleStatus.RECEIVED && avtale.createdAt.plusSeconds(30L).isAfter(now)) {
                avtale.status = AvtaleStatus.PROCESSING
            } else if (avtale.status == AvtaleStatus.PROCESSING && avtale.createdAt.plusSeconds(60L).isAfter(now)) {
                avtale.status = AvtaleStatus.SENT
            }

            return ResponseEntity.ok(avtale)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}