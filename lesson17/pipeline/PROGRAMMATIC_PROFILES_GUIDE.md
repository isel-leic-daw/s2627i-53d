# SPRING PROFILES - PROGRAMMATIC DEFINITION GUIDE

## 4 Ways to Activate Profiles Programmatically

### 1️⃣ **Via `main()` - SpringApplication.setAdditionalProfiles()**

**File: App.kt**
```kotlin
fun main(args: Array<String>) {
    val app = org.springframework.boot.SpringApplication(App::class.java)
    
    // Option A: Fixed profile
    app.setAdditionalProfiles("pt")
    
    // Option B: Profile based on environment variable
    val profile = System.getenv("APP_LANGUAGE") ?: "pt"
    app.setAdditionalProfiles(profile)
    
    app.run(*args)
}
```

**How to execute:**
```bash
# With environment variable
export APP_LANGUAGE=en
java -jar app.jar

# Or with system property (in main)
java -Dapp.language=en -jar app.jar
```

**Advantages:**
- ✓ Simple and straightforward
- ✓ Profile activated BEFORE Spring initializes
- ✓ Good for development and testing

---

### 2️⃣ **Via ApplicationContextInitializer**

**File: AppConfigProgrammatic.kt**
```kotlin
@Component
class ProgrammaticProfileInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val lang = System.getenv("APP_LANGUAGE") ?: System.getProperty("app.language", "pt")
        applicationContext.environment.setActiveProfiles(lang)
    }
}
```

**How to register in App.kt:**
```kotlin
fun main(args: Array<String>) {
    val app = org.springframework.boot.SpringApplication(App::class.java)
    app.addInitializers(ProgrammaticProfileInitializer())
    app.run(*args)
}
```

**How to execute:**
```bash
export APP_LANGUAGE=en
java -jar app.jar

# Or
java -Dapp.language=en -jar app.jar
```

**Advantages:**
- ✓ Centralized logic in class
- ✓ Initializes during Spring context
- ✓ Access to Environment

---

### 3️⃣ **Via @ConditionalOnProperty**

**File: AppConfigProgrammatic.kt**
```kotlin
@Configuration
class ConditionalProfileConfig {
    
    @Bean
    @ConditionalOnProperty(name = "app.language", havingValue = "en")
    fun greetingsEnglish(): ServiceGreetings = ServiceGreetingEn()
    
    @Bean
    @ConditionalOnProperty(name = "app.language", havingValue = "pt", matchIfMissing = true)
    fun greetingsPortuguese(): ServiceGreetings = ServiceGreetingsPt()
}
```

**How to execute:**
```bash
# Via application.properties
# app.language=en

# Or via command line
java -jar app.jar --app.language=en

# Or via system property
java -Dapp.language=en -jar app.jar
```

**Advantages:**
- ✓ No need for @Profile
- ✓ Flexible - property based
- ✓ Good for production

---

### 4️⃣ **Via Environment Variables (Advanced)**

**File: AppConfigProgrammatic.kt**
```kotlin
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class SmartProfileSelector(val environment: Environment) {
    init {
        selectProfileBasedOnLogic()
    }
    
    private fun selectProfileBasedOnLogic() {
        val profile = when {
            isProductionEnvironment() -> "en"
            isDevelopmentEnvironment() -> "pt"
            else -> "pt"
        }
        // Note: Spring is already initialized here
    }
    
    private fun isProductionEnvironment() =
        System.getenv("ENVIRONMENT") == "production"
}
```

**How to execute:**
```bash
export ENVIRONMENT=production
java -jar app.jar
```

**Advantages:**
- ✓ Logic based on multiple variables
- ✓ Complex decisions

**Disadvantages:**
- ✗ Spring is already initialized (too late for @Profile)
- ✗ Better to use ApplicationContextInitializer

---

## Quick Comparison

| Approach | Timing | Complexity | Use Case |
|----------|--------|------------|----------|
| **setAdditionalProfiles()** | Before init | Low | Dev/Test |
| **ApplicationContextInitializer** | During init | Medium | Dynamic |
| **@ConditionalOnProperty** | Bean creation | Low | Production |
| **Environment Vars** | After init | High | Special cases |

---

## Quick Test

### With application.properties (default):
```bash
cd lesson15/pipeline
./gradlew bootRun
curl http://localhost:8080/greeting
# Output: Olá Mundo (Portuguese)
```

### With setAdditionalProfiles() modified:
```kotlin
// In App.kt, change main() to:
fun main(args: Array<String>) {
    val app = org.springframework.boot.SpringApplication(App::class.java)
    app.setAdditionalProfiles("en")  // Force English
    app.run(*args)
}
```

```bash
./gradlew bootRun
curl http://localhost:8080/greeting
# Output: Hello World (English)
```

### With environment variable:
```bash
# Current App.kt already uses this:
export APP_LANGUAGE=en
./gradlew bootRun
curl http://localhost:8080/greeting
# Output: Hello World (English)

export APP_LANGUAGE=pt
./gradlew bootRun
curl http://localhost:8080/greeting
# Output: Olá Mundo (Portuguese)
```

---

## Summary: When to use each one?

✅ **application.properties** → Production, default configuration
✅ **setAdditionalProfiles()** → Tests, development
✅ **ApplicationContextInitializer** → Complex dynamic logic
✅ **@ConditionalOnProperty** → Alternative to @Profile, more flexible
✅ **Environment Vars** → Special cases, external orchestration
