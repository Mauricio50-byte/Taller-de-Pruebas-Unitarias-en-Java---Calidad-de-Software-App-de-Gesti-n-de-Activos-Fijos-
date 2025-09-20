# Aplicación de Gestión de Activos Fijos

Este proyecto implementa una aplicación de gestión de activos fijos con pruebas unitarias completas y un pipeline de CI/CD configurado para GitHub Actions y ejecución local con act.

##  Tabla de Contenidos

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Instalación y Configuración](#instalación-y-configuración)
- [Ejecución de Pruebas](#ejecución-de-pruebas)
- [CI/CD Pipeline](#cicd-pipeline)
- [Ejecución Local con act](#ejecución-local-con-act)
- [Evidencias de Ejecución](#evidencias-de-ejecución)

##  Descripción del Proyecto

La aplicación gestiona activos fijos organizados por dependencias, implementando:

- **Clases principales**: `Activo`, `Dependencias`, `FechaDeCompra`
- **Estructuras de datos**: Listas circulares dobles para dependencias y listas dobles para activos
- **Funcionalidades**: CRUD de activos, ordenamiento, búsquedas, persistencia en archivos
- **Pruebas unitarias**: Cobertura completa con JUnit 5 y AssertJ

##  Estructura del Proyecto

```
├── .github/
│   └── workflows/
│       └── ci.yml                 # Workflow de GitHub Actions
├── src/
│   ├── main/java/com/miApp/
│   │   ├── App.java              # Aplicación principal
│   │   ├── Activo.java           # Clase Activo
│   │   ├── Dependencias.java     # Clase Dependencias
│   │   ├── FechaDeCompra.java    # Clase FechaDeCompra
│   │   ├── ListaCircularDobleDependencias.java
│   │   ├── ListaDobleActivos.java
│   │   ├── NodoDependencia.java
│   │   └── NodoActivo.java
│   └── test/java/com/miApp/
│       └── ActivoTest.java       # Pruebas unitarias
├── Dockerfile                    # Imagen Docker para act
├── pom.xml                      # Configuración Maven
├── run-tests-local.ps1          # Script para Docker
├── run-act.ps1                  # Script para act
└── README.md                    # Este archivo
```

##  Requisitos Previos

### Para desarrollo local:
- **JDK 17** o superior
- **Maven 3.6+**
- **Git**

### Para ejecución con Docker:
- **Docker Desktop** para Windows
- **PowerShell 5.0+**

### Para ejecución con act:
- **act** (GitHub Actions runner local)
- **Docker Desktop**

##  Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd Taller-de-Pruebas-Unitarias-en-Java---Calidad-de-Software-App-de-Gesti-n-de-Activos-Fijos-
```

### 2. Verificar JDK 17
```bash
java -version
# Debe mostrar version 17.x.x
```

### 3. Verificar Maven
```bash
mvn -version
# Debe mostrar Maven 3.6+ y JDK 17
```

##  Ejecución de Pruebas

### Ejecución local con Maven
```bash
# Compilar y ejecutar pruebas
mvn clean test

# Generar reporte de cobertura
mvn clean test jacoco:report

# Ver reporte de cobertura
# Abrir: target/site/jacoco/index.html
```

### Ejecución con Docker
```powershell
# Ejecutar script de PowerShell
.\run-tests-local.ps1

# O manualmente:
docker build -t activos-fijos-app .
docker run --rm -v ${PWD}:/workspace activos-fijos-app
```

##  CI/CD Pipeline

### GitHub Actions Workflow

El archivo `.github/workflows/ci.yml` configura un pipeline que:

1. **Se ejecuta en**: push y pull_request hacia `main`
2. **Instala**: JDK 17 (distribución Temurin)
3. **Cachea**: dependencias Maven para mejor rendimiento
4. **Ejecuta**: `mvn clean test`
5. **Genera**: reportes de cobertura con JaCoCo
6. **Publica**: resultados de pruebas

### Características del workflow:
-  Ejecución automática en cambios
-  Cache de dependencias Maven
-  Reportes de cobertura
-  Publicación de resultados
-  Compatible con JDK 17

##  Ejecución Local con act

### Instalación de act

#### Windows (Chocolatey):
```powershell
choco install act-cli
```

#### Windows (Scoop):
```powershell
scoop install act
```

#### Manual:
Descargar desde: https://github.com/nektos/act/releases

### Ejecución con act

```powershell
# Usar script de PowerShell
.\run-act.ps1

# O manualmente:
act -j test

# Con imagen específica:
act -j test -P ubuntu-latest=catthehacker/ubuntu:act-latest
```

### Configuración de act

El archivo `.actrc` configura la imagen por defecto:
```
-P ubuntu-latest=catthehacker/ubuntu:act-latest
```

##  Evidencias de Ejecución

###  Ejecución Exitosa

**Comando ejecutado:**
```bash
mvn clean test
```

**Resultado esperado:**
```
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

**Con act:**
```bash
act -j test
```

**Resultado esperado:**
```
[CI Pipeline/test] 🏁  Job succeeded
```

###  Simulación de Fallo

Para simular un fallo intencionalmente:

1. **Modificar prueba en `ActivoTest.java`:**
```java
// Cambiar línea 36:
assertEquals(1001, activo.getNumeroDeActivo());
// Por:
assertEquals(9999, activo.getNumeroDeActivo()); // Valor incorrecto
```

2. **Ejecutar pruebas:**
```bash
mvn clean test
```

3. **Resultado esperado:**
```
[ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0
[INFO] BUILD FAILURE
```

4. **Con act:**
```bash
act -j test
```

**Resultado esperado:**
```
[CI Pipeline/test]   Job failed
```

## 🛠️ Comandos Útiles

### Maven
```bash
# Limpiar proyecto
mvn clean

# Compilar sin pruebas
mvn compile

# Ejecutar solo pruebas
mvn test

# Empaquetar aplicación
mvn package

# Ver dependencias
mvn dependency:tree
```

### Docker
```bash
# Construir imagen
docker build -t activos-fijos-app .

# Ejecutar contenedor
docker run --rm activos-fijos-app

# Ver imágenes
docker images

# Limpiar imágenes
docker image prune
```

### act
```bash
# Listar workflows
act -l

# Ejecutar workflow específico
act -j test

# Modo verbose
act -j test -v

# Usar imagen específica
act -j test -P ubuntu-latest=node:16-buster-slim
```

##  Solución de Problemas

### Error: "mvn no se reconoce"
- **Solución**: Instalar Maven y agregarlo al PATH del sistema

### Error: "docker no se reconoce"
- **Solución**: Instalar Docker Desktop para Windows

### Error: "act no se reconoce"
- **Solución**: Instalar act usando Chocolatey o Scoop

### Error: "Java version incompatible"
- **Solución**: Verificar que JDK 17 esté instalado y configurado

### Error en pruebas: "ClassNotFoundException"
- **Solución**: Ejecutar `mvn clean compile` antes de las pruebas

##  Notas Adicionales

- El proyecto usa **JDK 17** como se especifica en los requisitos
- Las pruebas incluyen **JUnit 5** y **AssertJ** para assertions fluidas
- El workflow está optimizado con **cache de Maven** para mejor rendimiento
- Se incluye **JaCoCo** para reportes de cobertura de código
- Los scripts de PowerShell facilitan la ejecución en Windows

##  Contribución

1. Fork el proyecto
2. Crear rama feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear Pull Request

##  Licencia

Este proyecto es parte de un taller académico de pruebas unitarias y calidad de software.

---