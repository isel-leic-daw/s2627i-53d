package pt.isel.daw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InterceptorTimingApplication

fun main(args: Array<String>) {
    runApplication<InterceptorTimingApplication>(*args)
}
