package no.david.fagsystem.model

data class KundeDTO(
    val id: Int,
    val fodselsnummer: String,
    val fornavn: String,
    val etternavn: String,
    val epost: String
)
