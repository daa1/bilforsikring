package no.david.brevtjeneste.web

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import no.david.brevtjeneste.model.Avtale
import no.david.brevtjeneste.model.AvtaleStatus
import no.david.brevtjeneste.service.BrevtjenesteService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.ZonedDateTime
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.junit.jupiter.api.BeforeEach
import org.mockito.MockitoAnnotations

@WebMvcTest(BrevtjenesteController::class)
class BrevtjenesteControllerTests {
    @Autowired
    private lateinit var mvc: MockMvc


    @BeforeEach
    fun before() {
        BrevtjenesteService.collection.clear()
    }

    @Test
    fun `should return 200 ok and Avtale in body if avtale exists`() {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        val avtale = Avtale(123123, 98765, ZonedDateTime.now(), AvtaleStatus.RECEIVED)
        BrevtjenesteService.collection.add(avtale)

        mvc.perform(
            MockMvcRequestBuilders.get("/brevtjeneste/api/v1/avtale/123123")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(avtale)))
    }

    @Test
    fun `should return 404 if avtale does not exist`() {
        mvc.perform(
            MockMvcRequestBuilders.get("/brevtjeneste/api/v1/avtale/123123")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}