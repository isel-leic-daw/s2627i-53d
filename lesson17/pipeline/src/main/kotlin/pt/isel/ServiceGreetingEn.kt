package pt.isel

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("en")
class ServiceGreetingEn : ServiceGreetings {
    override fun greeting(): String = "Hello World"
}
