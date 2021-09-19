package no.david.brevtjeneste.model

import java.time.ZonedDateTime

data class Avtale(
    val id: Long,
    val kundeId: Long,
    val createdAt: ZonedDateTime,
    var status: AvtaleStatus
)
