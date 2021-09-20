package no.david.gateway.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import no.david.gateway.model.brevtjeneste.SendAvtaleTilKunde
import no.david.gateway.model.fagsystem.Avtale
import no.david.gateway.model.fagsystem.Kunde
import no.david.gateway.model.fagsystem.OpprettAvtale
import no.david.gateway.model.fagsystem.OpprettKunde
import no.david.gateway.service.BrevtjenesteService
import no.david.gateway.service.FagsystemService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(GatewayController::class)
class GatewayControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var brevtjenesteService: BrevtjenesteService

    @MockBean
    private lateinit var fagsystemService: FagsystemService

    @Test
    fun `when all calls succeed then return 200 OK and avtale body`() {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        val kundeMock = Kunde(1, "123451234567", "Rudolf", "Rolfsen", "post@post.no")
        val avtaleMock = Avtale(1, "EK12345", 15, "Opprettet", kundeMock)
        Mockito.`when`(fagsystemService.opprettKunde(any(OpprettKunde::class.java)))
            .thenReturn(kundeMock)
        Mockito.`when`(fagsystemService.opprettAvtale(any(OpprettAvtale::class.java)))
            .thenReturn(avtaleMock)
        Mockito.`when`(brevtjenesteService.sendAvtaleTilKunde(any(SendAvtaleTilKunde::class.java)))
            .thenReturn("Mock")


        mvc.perform(
            MockMvcRequestBuilders.post("/gateway/api/v1/bilforsikring")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                        {
                            "registreringsnummer": "EK12345", 
                            "bonus": 15, 
                            "fodselsnummer": "12345123456", 
                            "fornavn": "Rudolf", 
                            "etternavn": "Rolfsen", 
                            "epost": "post@post.no"
                        }
                    """.trimIndent()
                )
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(avtaleMock)))
    }

    @Test
    fun `when all opprettAvtale return null return 500 server error`() {
        val kundeMock = Kunde(1, "123451234567", "Rudolf", "Rolfsen", "post@post.no")
        Mockito.`when`(fagsystemService.opprettKunde(any(OpprettKunde::class.java)))
            .thenReturn(kundeMock)
        Mockito.`when`(fagsystemService.opprettAvtale(any(OpprettAvtale::class.java)))
            .thenReturn(null)
        Mockito.`when`(brevtjenesteService.sendAvtaleTilKunde(any(SendAvtaleTilKunde::class.java)))
            .thenReturn("Mock")


        mvc.perform(
            MockMvcRequestBuilders.post("/gateway/api/v1/bilforsikring")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                        {
                            "registreringsnummer": "EK12345", 
                            "bonus": 15, 
                            "fodselsnummer": "12345123456", 
                            "fornavn": "Rudolf", 
                            "etternavn": "Rolfsen", 
                            "epost": "post@post.no"
                        }
                    """.trimIndent()
                )
        )
            .andExpect(MockMvcResultMatchers.status().is5xxServerError)
    }

    // Workaround for Mockito.any
    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
}