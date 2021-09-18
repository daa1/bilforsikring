package no.david.fagsystem.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Kunde(
    var fodselsnummer: String,
    var fornavn: String,
    var etternavn: String,
    var epost: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)