package no.david.gateway

import no.david.gateway.config.BrevtjenesteConfig
import no.david.gateway.config.FagsystemConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FagsystemConfig::class, BrevtjenesteConfig::class)
class GatewayApplication

fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}
