package pt.isel

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("pt")
class ServiceGreetingsPt(
    // val dummy: IDummy,
) : ServiceGreetings {
    override fun greeting(): String = "Olá Mundo"
}
