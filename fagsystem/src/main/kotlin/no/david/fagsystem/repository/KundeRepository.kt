package no.david.fagsystem.repository

import no.david.fagsystem.model.Kunde
import org.springframework.data.repository.CrudRepository

interface KundeRepository : CrudRepository<Kunde, Long>