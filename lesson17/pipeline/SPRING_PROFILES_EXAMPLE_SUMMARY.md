# Spring Profiles - Example Summary

## ✅ What was created

Complete example of **Spring Context Profiles** with implementations in Portuguese and English.

### Modified Files

1. **[ServiceGreetingEn.kt](./src/main/kotlin/pt/isel/ServiceGreetingEn.kt)**
   - Added `@Profile("en")`
   - Implementation registered only when profile is "en"

2. **[ServiceGreetingsPt.kt](./src/main/kotlin/pt/isel/ServiceGreetingsPt.kt)**
   - Added `@Profile("pt")`
   - Implementation registered only when profile is "pt"

3. **[ControllerArgParser.kt](./src/main/kotlin/pt/isel/ControllerArgParser.kt)**
   - Added handler `/greeting` that uses `ServiceGreetings`
   - Demonstrates automatic injection of the correct implementation

4. **[App.kt](./src/main/kotlin/pt/isel/App.kt)**
   - Modified `main()` to activate profile programmatically
   - Reads `APP_LANGUAGE` environment variable

### Created Files

5. **[AppConfigProgrammatic.kt](./src/main/kotlin/pt/isel/AppConfigProgrammatic.kt)**
   - 3 different approaches to activate profiles programmatically
   - Examples and inline explanations

6. **[application.properties](./src/main/resources/application.properties)**
   - Default configuration: `spring.profiles.active=pt`

7. **[application-pt.properties](./src/main/resources/application-pt.properties)**
   - Configuration specific to Portuguese profile

8. **[application-en.properties](./src/main/resources/application-en.properties)**
   - Configuration specific to English profile

9. **[requests.http](./src/main/resources/requests.http)**
   - Updated with examples to test `/greeting`

10. **[SPRING_PROFILES_GUIDE.md](./SPRING_PROFILES_GUIDE.md)**
    - Complete guide about Spring Profiles

11. **[PROGRAMMATIC_PROFILES.md](./PROGRAMMATIC_PROFILES.md)**
    - 4 approaches to define profiles via code

## 🧪 How to Test

### 1. Portuguese (default)
```bash
cd lesson15/pipeline
gradle bootRun

# In another terminal:
curl http://localhost:8080/greeting
# Output: Olá Mundo
```

### 2. English (via environment variable)
```bash
export APP_LANGUAGE=en
gradle bootRun

# In another terminal:
curl http://localhost:8080/greeting
# Output: Hello World
```

### 3. With compiled JAR
```bash
gradle build

# Portuguese
java -jar build/libs/pipeline.jar

# English
APP_LANGUAGE=en java -jar build/libs/pipeline.jar

# Or with system property
java -Dapp.language=en -jar build/libs/pipeline.jar
```

## 📚 Concepts Demonstrated

| Concept | File | Line |
|---------|------|------|
| `@Profile` | ServiceGreeting*.kt | class |
| `@Component` with Profile | ServiceGreeting*.kt | class |
| Dependency Injection | ControllerArgParser.kt | 24 |
| Programmatic Profile | App.kt | 24-26 |
| ApplicationContextInitializer | AppConfigProgrammatic.kt | 49-68 |
| Environment Variables | App.kt | 26 |
| Spring Boot properties | application*.properties | - |

## 🎯 Execution Flow

```
1. App.kt main() executes
   ↓
2. Reads APP_LANGUAGE from environment (default: "pt")
   ↓
3. Activates profile via SpringApplication.setAdditionalProfiles()
   ↓
4. Spring initializes and looks for @Component with @Profile("pt" or "en")
   ↓
5. Finds ServiceGreetingsPt (or ServiceGreetingEn)
   ↓
6. Registers in IoC Container
   ↓
7. ControllerArgParser receives automatic injection
   ↓
8. GET /greeting calls greetings.greeting()
   ↓
9. Returns "Olá Mundo" or "Hello World"
```

## 📖 Recommended Reading

1. [SPRING_PROFILES_GUIDE.md](./SPRING_PROFILES_GUIDE.md) - Fundamentals
2. [PROGRAMMATIC_PROFILES.md](./PROGRAMMATIC_PROFILES.md) - 4 approaches
3. AppConfigProgrammatic.kt - Code with comments

## ✨ Key Points

- ✅ Profile defines which implementation is registered
- ✅ Spring automatically injects based on active profile
- ✅ Can be defined via file or code
- ✅ Environment variables control behavior
- ✅ Practical and complete example, ready to expand

## 🚀 Next Steps

You can expand this with:
- More languages (es, it, fr, etc.)
- Different configurations per language
- Tests with different profiles
- Multiple simultaneous profiles (pt + cache-enabled)
