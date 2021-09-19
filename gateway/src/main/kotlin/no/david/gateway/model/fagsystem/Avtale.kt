package no.david.gateway.model.fagsystem

data class Avtale(
    val id: Long,
    val registreringsnummer: String,
    val bonus: Int,
    val status: String,
    val kunde: Kunde
)
