package no.david.fagsystem.repository

import no.david.fagsystem.model.Avtale
import org.springframework.data.repository.CrudRepository

interface AvtaleRepository  : CrudRepository<Avtale, Long>