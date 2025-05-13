#  Conversor de Monedas en Java

Proyecto desarrollado como parte de un curso, que permite convertir entre distintas divisas usando la API pública de [ExchangeRate API](https://www.exchangerate-api.com/).  
Utiliza `HttpClient` para las solicitudes HTTP y `Gson` para el parseo de respuestas JSON.

---

##  Características

- Lectura de clave API desde un archivo externo (`src/key.txt`)
- Solicitudes HTTP con `HttpClient`
- Parseo de JSON con `Gson`
- Validación de divisas soportadas
- Estructura modular y comentada

---

##  Estructura del proyecto

```
src/
│
├── com/curso/alura/conversor/
│   ├── callapi/
│   │   └── Solicitud.java          # Lógica HTTP
│   │   └── GestorApiKey.java       # Lectura segura de la API key desde txt
│   ├── Interaccion/
│   │   └── ListaDivisas.java       # Validador de divisas soportadas
│   └── toGson/
│       └── ExchangeRateResponse.java  # Clase modelo para deserializar JSON
├── key.txt ( Ignorado por Git)
```

---

##  Cómo usar

1. **Consigue tu clave API gratuita** en:  
    https://www.exchangerate-api.com/

2. **Crea el archivo `src/key.txt`** con tu clave:

```
tu_clave_api_aquí
```

>  Este archivo está **ignorado por Git** para evitar exponer la clave.

3. **Compila y ejecuta tu aplicación:**

```bash
javac -d out $(find ./src -name "*.java")
java -cp out com.curso.alura.Main
```

> Asegúrate de tener Java 11+ para que funcione `HttpClient`.

---

##  Librerías utilizadas

- [`java.net.http.HttpClient`](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html) (Java 11+)
- [`Gson`](https://github.com/google/gson) para deserializar respuestas JSON

---

##  Nota sobre seguridad

Este proyecto es **educativo** y usa una clave API almacenada en texto plano.  
**Nunca subas claves reales a Git**, incluso si están en archivos `.txt`.  
Ya se configuró el `.gitignore` para evitarlo, pero en producción se recomienda usar `.env`, `.properties` o servicios de gestión de secretos.
