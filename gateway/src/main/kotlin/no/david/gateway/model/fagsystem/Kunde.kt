package no.david.gateway.model.fagsystem

data class Kunde(
    val id: Long,
    val fodselsnummer: String,
    val fornavn: String,
    val etternavn: String,
    val epost: String,
)
