package no.david.gateway.model

data class BestillBilforsikring(
    val registreringsnummer: String,
    val bonus: Int,
    val fodselsnummer: String,
    val fornavn: String,
    val etternavn: String,
    val epost: String
)
