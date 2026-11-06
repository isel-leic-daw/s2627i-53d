package pt.isel

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

/**
 * Approach 1: Define profile in the main() function
 *
 * Modify the main function in App.kt like this:
 * ```
 * fun main(args: Array<String>) {
 *     val app = SpringApplication(App::class.java)
 *     app.setAdditionalProfiles("pt")  // or "en"
 *     app.run(*args)
 * }
 * ```
 *
 * Advantages:
 * ✓ Profile is set before Spring initialization
 * ✓ Simple and direct
 * ✓ Good for development/testing
 */
object ProgrammaticProfileExample1

/**
 * Approach 2: Define profile based on Environment/System Properties
 * Use an ApplicationContextInitializer
 *
 * Usage in App.kt:
 * ```
 * fun main(args: Array<String>) {
 *     val app = SpringApplication(App::class.java)
 *     app.addInitializers(ProgrammaticProfileInitializer())
 *     app.run(*args)
 * }
 * ```
 *
 * Run with:
 * - export APP_LANGUAGE=en; java -jar app.jar
 * - java -Dapp.language=en -jar app.jar
 */
@Component
class ProgrammaticProfileInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val environment = applicationContext.environment
        val activeProfiles = environment.activeProfiles

        if (activeProfiles.isEmpty()) {
            val lang = System.getenv("APP_LANGUAGE") ?: System.getProperty("app.language", "pt")
            applicationContext.environment.setActiveProfiles(lang)
            println("✓ Profile set programmatically to: $lang")
        } else {
            println("✓ Profile already active: ${activeProfiles.joinToString(", ")}")
        }
    }
}

/**
 * Approach 3: Programmatic profile with custom logic
 *
 * Usage:
 * - export ENVIRONMENT=production; java -jar app.jar
 * - export ENVIRONMENT=development; java -jar app.jar
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class SmartProfileSelector(
    val environment: Environment,
) {
    init {
        selectProfileBasedOnLogic()
    }

    private fun selectProfileBasedOnLogic() {
        val activeProfiles = environment.activeProfiles

        if (activeProfiles.isEmpty()) {
            val profile =
                when {
                    isProductionEnvironment() -> "en"
                    isDevelopmentEnvironment() -> "pt"
                    else -> "pt"
                }

            println("SmartProfileSelector: Would activate '$profile' (Spring already initialized)")
        }
    }

    private fun isProductionEnvironment(): Boolean = System.getenv("ENVIRONMENT") == "production"

    private fun isDevelopmentEnvironment(): Boolean = System.getenv("ENVIRONMENT") == "development"
}

/**
 * Summary of Approaches:
 *
 * | Approach | Timing | Use Case | Complexity |
 * |----------|--------|----------|-----------|
 * | application.properties | Startup (file) | Production | Low |
 * | setAdditionalProfiles() | Before init | Testing/Dev | Low |
 * | ApplicationContextInitializer | During init | Dynamic | Medium |
 * | Smart Selection | After init | Special cases | High |
 *
 * RECOMMENDED:
 * ✓ Use application.properties for production
 * ✓ Use setAdditionalProfiles() for testing
 * ✓ Use ApplicationContextInitializer for complex dynamic logic
 */
