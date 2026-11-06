# Interceptor Timing Example - Quick Start

## 📁 Localização
```
lesson16/
└── interceptor-timing/    # Novo exemplo de interceptor
    ├── src/main/kotlin/pt/isel/daw/
    │   ├── TimingInterceptor.kt      # ⭐ Interceptor principal
    │   ├── WebConfig.kt              # Configuração
    │   ├── DemoController.kt         # Endpoints para teste
    │   └── InterceptorTimingApplication.kt
    └── README.md
```

## 🎯 O que é?

Um exemplo simples mas completo de um `HandlerInterceptor` que:
- Mede o tempo de processamento de cada pedido HTTP
- Registra em log: método, URI, status e duração em milissegundos
- Armazena dados temporários na requisição

## 🚀 Iniciar Rápido

```bash
cd lesson16/interceptor-timing
gradle bootRun
```

## 📝 Testar

```bash
# Resposta rápida
curl http://localhost:8080/fast

# Resposta lenta (500ms)
curl http://localhost:8080/slow

# Delay customizável
curl http://localhost:8080/delay/300
```

## 📊 Saída Esperada (no console)

```
INFO: Request completed: method=GET, uri=/fast, status=200, duration=2ms
INFO: Request completed: method=GET, uri=/slow, status=200, duration=502ms
INFO: Request completed: method=GET, uri=/delay/300, status=200, duration=301ms
```

## 🔑 Conceitos-Chave

### 1. **preHandle() vs postHandle()**
```kotlin
override fun preHandle(...): Boolean {
    // Executado ANTES do handler
    request.setAttribute(requestStartTimeKey, System.currentTimeMillis())
    return true
}

override fun postHandle(...) {
    // Executado DEPOIS do handler
    val duration = System.currentTimeMillis() - startTime
    logger.info("duration={}ms", duration)
}
```

### 2. **Armazenar Dados na Requisição**
```kotlin
// ThreadSafe - cada thread tem seu próprio atributo
request.setAttribute(key, value)
request.getAttribute(key)
```

### 3. **Registrar o Interceptor**
```kotlin
@Configuration
class WebConfig(private val timingInterceptor: TimingInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(timingInterceptor)
    }
}
```

## 🔄 Fluxo de Execução

```
Requisição HTTP
    ↓
TimingInterceptor.preHandle()     ← Regista tempo de início
    ↓
Handler/Controller
    ↓
TimingInterceptor.postHandle()    ← Calcula e registra duração
    ↓
Response HTTP
```

## 💡 Casos de Uso

- ✅ Medir performance de endpoints
- ✅ Detectar gargalos
- ✅ Logging de métricas
- ✅ Alertar em respostas lentas

## 🧪 Estender o Exemplo

Para adicionar mais métricas:

```kotlin
override fun postHandle(...) {
    val duration = System.currentTimeMillis() - startTime
    
    // Alertar se for muito lento
    if (duration > 1000) {
        logger.warn("SLOW REQUEST: {}ms", duration)
    }
    
    // Contar por endpoint
    metrics.recordDuration(uri, duration)
}
```

## 📚 Referências

- [Spring HandlerInterceptor Docs](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/HandlerInterceptor.html)
- [lesson16/interceptor-timing/README.md](interceptor-timing/README.md)
- [lesson16/interceptor-timing/PROJECT_STRUCTURE.md](interceptor-timing/PROJECT_STRUCTURE.md)
