package no.david.fagsystem.model

import javax.persistence.*

@Entity
class Avtale(
    var registreringsnummer: String,
    var bonus: Int,
    var status: String,
    @OneToOne
    @JoinColumn(name = "kunde_id")
    var kunde: Kunde,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)