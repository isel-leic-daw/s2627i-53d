# Interceptor Timing Example

Simple exemplo de um **HandlerInterceptor** que mede o tempo de processamento de cada pedido HTTP.

## Overview

Este projeto demonstra como criar um interceptor que:
- Regista o tempo de início do pedido no `preHandle`
- Calcula a duração do pedido no `postHandle`
- Registra a duração em milissegundos no log

## Componentes

### TimingInterceptor
HandlerInterceptor que:
- Armazena o tempo de início do pedido como atributo da requisição
- Calcula o tempo decorrido no final do processamento
- Registra em log: método HTTP, URI, status e duração em ms

### WebConfig
Classe de configuração Spring que:
- Registra o `TimingInterceptor` na aplicação

### DemoController
Controlador com 3 endpoints para testar:
- `/fast` - Resposta quase imediata (~1ms)
- `/slow` - Resposta com delay de 500ms
- `/delay/{millis}` - Resposta com delay parametrizável

## Log Output Example

```
INFO: Request completed: method=GET, uri=/fast, status=200, duration=1ms
INFO: Request completed: method=GET, uri=/slow, status=200, duration=501ms
INFO: Request completed: method=GET, uri=/delay/300, status=200, duration=301ms
```

## Building and Running

### Build
```bash
./gradlew build
```

### Run
```bash
./gradlew bootRun
```

### Test
```bash
# Fast response
curl http://localhost:8080/fast

# Slow response (500ms)
curl http://localhost:8080/slow

# Custom delay (300ms)
curl http://localhost:8080/delay/300
```

## Observar os Logs

Na consola, verá logs do tipo:
```
Request completed: method=GET, uri=/fast, status=200, duration=2ms
Request completed: method=GET, uri=/slow, status=200, duration=502ms
Request completed: method=GET, uri=/delay/300, status=200, duration=301ms
```

## Key Concepts

- **HandlerInterceptor**: Interface do Spring para interceptar requisições
- **preHandle()**: Executado antes de chegar ao handler
- **postHandle()**: Executado após o handler mas antes de renderizar a view
- **Request attributes**: Forma segura de armazenar dados por thread na requisição

## Technologies

- Spring Boot 4.1.1
- Spring MVC
- Kotlin 2.4.21
- Java 25
