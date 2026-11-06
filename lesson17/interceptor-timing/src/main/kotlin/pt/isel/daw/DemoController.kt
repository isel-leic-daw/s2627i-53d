package pt.isel.daw

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class DemoController {

    @GetMapping("/fast")
    fun fastEndpoint(): Map<String, String> {
        return mapOf("message" to "Fast response", "duration" to "~1ms")
    }

    @GetMapping("/slow")
    fun slowEndpoint(): Map<String, String> {
        Thread.sleep(500)
        return mapOf("message" to "Slow response", "duration" to "~500ms")
    }

    @GetMapping("/delay/{millis}")
    fun delayedEndpoint(@PathVariable millis: Long): Map<String, Any> {
        Thread.sleep(millis)
        return mapOf("message" to "Delayed response", "delayMs" to millis)
    }
}
