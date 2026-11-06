# Project Structure - Interceptor Timing Example

```
interceptor-timing/
├── build.gradle.kts                 # Gradle build configuration
├── settings.gradle.kts              # Gradle settings
├── README.md                        # Project documentation
├── PROJECT_STRUCTURE.md             # This file
├── build/                           # Build output
└── src/
    ├── main/
    │   ├── kotlin/pt/isel/daw/
    │   │   ├── InterceptorTimingApplication.kt    # Spring Boot main class
    │   │   ├── TimingInterceptor.kt               # Interceptor que mede tempo
    │   │   ├── WebConfig.kt                       # Configuração do interceptor
    │   │   └── DemoController.kt                  # Endpoints para teste
    │   └── resources/
    │       └── application.properties             # Spring properties
    └── test/
        └── kotlin/pt/isel/daw/
            └── InterceptorTimingApplicationTests.kt # Testes unitários
```

## Componentes Principais

### 1. TimingInterceptor
- **Responsabilidade**: Medir tempo de processamento de requisições
- **Métodos**:
  - `preHandle()`: Registra timestamp do início
  - `postHandle()`: Calcula duração e registra em log

### 2. WebConfig
- **Responsabilidade**: Configurar o interceptor na aplicação
- **Registra**: TimingInterceptor no InterceptorRegistry

### 3. DemoController
- **Endpoints**:
  - `GET /fast` - Resposta rápida (~1ms)
  - `GET /slow` - Resposta lenta (500ms)
  - `GET /delay/{millis}` - Resposta com delay parametrizável

## Como Usar

1. **Iniciar a aplicação**:
   ```bash
   gradle bootRun
   ```

2. **Testar endpoints**:
   ```bash
   curl http://localhost:8080/fast
   curl http://localhost:8080/slow
   curl http://localhost:8080/delay/300
   ```

3. **Observar logs**:
   ```
   Request completed: method=GET, uri=/fast, status=200, duration=2ms
   Request completed: method=GET, uri=/slow, status=200, duration=502ms
   Request completed: method=GET, uri=/delay/300, status=200, duration=301ms
   ```

## Key Points

- ✅ Interceptor apenas responsável por timing
- ✅ Usa atributos da requisição para armazenar dados
- ✅ Logging limpo e legível
- ✅ Fácil de estender com mais métricas
