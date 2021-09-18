package no.david.brevtjeneste.model

import java.time.ZonedDateTime

data class Avtale(
    val id: Int,
    val kundeId: Int,
    val createdAt: ZonedDateTime,
    var status: AvtaleStatus
)
