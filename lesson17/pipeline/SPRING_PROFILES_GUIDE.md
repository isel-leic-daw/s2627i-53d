# Spring Context Profiles - ServiceGreetings Example

## Overview

This example demonstrates how to use **Spring Context Profiles** (`@Profile`) to create multiple implementations of a service and activate the correct one based on execution context.

## Structure

```
ServiceGreetings (interface)
├── ServiceGreetingsPt (with @Profile("pt"))
└── ServiceGreetingEn (with @Profile("en"))
```

## How it works

### 1. **Interface Definition**
```kotlin
interface ServiceGreetings {
    fun greeting(): String
}
```

### 2. **Implementations with @Profile**

**Portuguese Context:**
```kotlin
@Component
@Profile("pt")  // Only registered when profile "pt" is active
class ServiceGreetingsPt : ServiceGreetings {
    override fun greeting(): String = "Olá Mundo"
}
```

**English Context:**
```kotlin
@Component
@Profile("en")  // Only registered when profile "en" is active
class ServiceGreetingEn : ServiceGreetings {
    override fun greeting(): String = "Hello World"
}
```

### 3. **Dependency Injection**
```kotlin
@RestController
class ControllerArgParser(
    val greetings: ServiceGreetings,  // Spring injects the active implementation
) {
    @GetMapping("/greeting")
    fun handlerGreeting(): String = greetings.greeting()
}
```

## How to Test

### Option 1: Default Configuration (Portuguese)
```bash
# application.properties has:
# spring.profiles.active=pt

curl http://localhost:8080/greeting
# Response: Olá Mundo
```

### Option 2: Activate English Context

**Method A: Change application.properties**
```properties
spring.profiles.active=en
```

**Method B: Environment Variable**
```bash
SPRING_PROFILES_ACTIVE=en java -jar pipeline.jar
```

**Method C: Command Line Arguments**
```bash
java -jar pipeline.jar --spring.profiles.active=en
```

**Test:**
```bash
curl http://localhost:8080/greeting
# Response: Hello World
```

## Configuration Files

- **application.properties** → Global configuration, activates default profile (pt)
- **application-pt.properties** → Configuration specific to Portuguese profile
- **application-en.properties** → Configuration specific to English profile

## Key Concepts

| Concept | Description |
|---------|-------------|
| `@Profile("pt")` | Activates the class only when profile "pt" is active |
| `@Component` | Register the class in Spring's IoC Container |
| `spring.profiles.active` | Defines which profile(s) are active |
| Automatic Injection | Spring finds the correct implementation based on active profile |

## Multiple Profiles (Advanced)

It's possible to activate multiple profiles simultaneously:
```properties
spring.profiles.active=pt,development,cache-enabled
```

Or with conditional:
```kotlin
@Profile("!production")  // Activates when NOT in production
@Component
class DevServiceGreetings : ServiceGreetings {
    override fun greeting(): String = "Dev Greeting"
}
```

## References

- [Spring Profiles Documentation](https://spring.io/blog/2011/02/15/spring-3-1-m1-introducing-profile)
- [Bean Definition Profiles](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-definition-profiles)
