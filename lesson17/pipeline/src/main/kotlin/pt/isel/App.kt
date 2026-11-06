package pt.isel

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class App

@Configuration
class AppConfig(
    val clientIpArgumentResolver: ClientIpArgumentResolver,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(resolvers)
        resolvers.add(clientIpArgumentResolver)
    }
}

fun main(args: Array<String>) {
    // Example: Set profile programmatically (Approach 1)
    val app = SpringApplication(App::class.java)

    // Set profile from environment variable or default to "pt"
    val profile = System.getenv("APP_LANGUAGE") ?: "pt"
    app.setAdditionalProfiles(profile)
    println("🔧 Spring profile activated: $profile")

    app.run(*args)
}
