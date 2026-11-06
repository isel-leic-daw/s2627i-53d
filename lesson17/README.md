# Lesson 16 - Spring MVC Pipeline e Interceptors

Cópia de lesson15 com adição de um exemplo de interceptor que mede o tempo de processamento.

## 📁 Estrutura

```
lesson16/
├── spring-mcv-intro/          # Exemplo básico de Spring MVC
├── java-servlet/              # Exemplo de servlets Java
├── pipeline/                  # Exemplo da pipeline do Spring MVC
└── interceptor-timing/        # ✨ NOVO: Exemplo de interceptor com timing
    ├── src/
    │   ├── main/kotlin/pt/isel/daw/
    │   │   ├── TimingInterceptor.kt
    │   │   ├── WebConfig.kt
    │   │   ├── DemoController.kt
    │   │   └── InterceptorTimingApplication.kt
    │   └── main/resources/
    │       └── application.properties
    ├── build.gradle.kts
    ├── settings.gradle.kts
    ├── README.md
    └── PROJECT_STRUCTURE.md
```

## 🎯 Novo Exemplo: interceptor-timing

Um exemplo simples de um `HandlerInterceptor` que:
- ⏱️ Mede o tempo de processamento de cada requisição
- 📝 Registra em log o tempo decorrido
- 🔒 Armazena dados de forma thread-safe na requisição

### Iniciar

```bash
cd interceptor-timing
gradle bootRun
```

### Testar

```bash
curl http://localhost:8080/fast
curl http://localhost:8080/slow
curl http://localhost:8080/delay/500
```

### Saída no Log

```
INFO: Request completed: method=GET, uri=/fast, status=200, duration=2ms
INFO: Request completed: method=GET, uri=/slow, status=200, duration=502ms
INFO: Request completed: method=GET, uri=/delay/500, status=200, duration=501ms
```

## 📚 Conceitos Cobertos

- ✅ HandlerInterceptor
- ✅ preHandle() vs postHandle()
- ✅ Armazenamento de dados na requisição
- ✅ Logging de métricas
- ✅ Configuração de interceptors em Spring

## 🔗 Documentação Adicional

- [INTERCEPTOR_TIMING_EXAMPLE.md](INTERCEPTOR_TIMING_EXAMPLE.md) - Guia completo
- [interceptor-timing/README.md](interceptor-timing/README.md) - Documentação do projeto
- [interceptor-timing/PROJECT_STRUCTURE.md](interceptor-timing/PROJECT_STRUCTURE.md) - Estrutura do projeto

## ⚙️ Build & Test

```bash
# Build
cd interceptor-timing && gradle build

# Run tests
gradle test

# Build output
build/libs/interceptor-timing-0.0.1-SNAPSHOT.jar
```

## 🚀 Technologies

- Spring Boot 4.1.1
- Spring MVC
- Kotlin 2.4.21
- Java 25
- Gradle
