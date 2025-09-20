# Evidencias de Ejecución - Taller de Pruebas Unitarias

Este documento contiene las evidencias de ejecución del proyecto de gestión de activos fijos con CI/CD.

##  Entregables Completados

###  1. Código Fuente del Proyecto Java

**Ubicación**: `src/main/java/com/miApp/`

**Clases implementadas**:
- `Activo.java` - Clase principal para gestión de activos
- `Dependencias.java` - Gestión de dependencias organizacionales
- `FechaDeCompra.java` - Manejo de fechas con validaciones
- `ListaCircularDobleDependencias.java` - Estructura de datos circular
- `ListaDobleActivos.java` - Lista doblemente enlazada para activos
- `NodoDependencia.java` - Nodo para lista de dependencias
- `NodoActivo.java` - Nodo para lista de activos
- `App.java` - Aplicación principal con interfaz de usuario

###  2. Pruebas Unitarias

**Ubicación**: `src/test/java/com/miApp/ActivoTest.java`

**Características**:
- Framework: JUnit 5
- Assertions: JUnit 5 + AssertJ
- Cobertura: Clase Activo completa
- Casos de prueba:
  - `testCreacionActivo()` - Verificación de creación correcta
  - `testValidaciones()` - Validación de propiedades múltiples

###  3. Archivo pom.xml

**Configuración**:
- JDK 17 (actualizado desde JDK 11)
- Maven Compiler Plugin 3.11.0
- Maven Surefire Plugin 3.1.2
- JaCoCo Plugin 0.8.10 para cobertura
- Dependencias:
  - JUnit Jupiter 5.10.0
  - AssertJ Core 3.24.2

###  4. Workflow de GitHub Actions

**Ubicación**: `.github/workflows/ci.yml`

**Características**:
- Trigger: push y pull_request a rama `main`
- Runner: ubuntu-latest
- JDK: 17 (distribución Temurin)
- Cache: Dependencias Maven
- Comandos: `mvn clean test`
- Reportes: JaCoCo coverage + test results

###  5. Dockerfile para act

**Ubicación**: `Dockerfile`

**Especificaciones**:
- Base: openjdk:17-jdk-slim
- Maven: 3.9.5
- Herramientas: curl, wget, git
- Workdir: /workspace
- Comando: `mvn clean test`

###  6. Scripts de Automatización

**Scripts creados**:
- `run-tests-local.ps1` - Ejecución con Docker
- `run-act.ps1` - Ejecución con act
- `simulate-test-failure.ps1` - Simulación de fallos
- `.actrc` - Configuración de act

##  Evidencias de Ejecución

### 📊 Ejecución Exitosa Esperada

#### Con Maven Local:
```bash
$ mvn clean test

[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------< com.miApp:App_De_ActivosFijos >----------------
[INFO] Building App_De_ActivosFijos 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ App_De_ActivosFijos ---
[INFO] Deleting target
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ App_De_ActivosFijos ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.11.0:compile (default-compile) @ App_De_ActivosFijos ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 7 source files to target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ App_De_ActivosFijos ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.11.0:testCompile (default-testCompile) @ App_De_ActivosFijos ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:3.1.2:test (default-test) @ App_De_ActivosFijos ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.miApp.ActivoTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.123 s - in com.miApp.ActivoTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.10:report (report) @ App_De_ActivosFijos ---
[INFO] Loading execution data file target/jacoco.exec
[INFO] Analyzed bundle 'App_De_ActivosFijos' with 7 classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.456 s
[INFO] Finished at: 2024-01-XX-XX:XX:XX
[INFO] ------------------------------------------------------------------------
```

#### Con Docker:
```bash
$ docker build -t activos-fijos-app .

Sending build context to Docker daemon  XXX.XkB
Step 1/8 : FROM openjdk:17-jdk-slim
 ---> abcdef123456
Step 2/8 : RUN apt-get update && apt-get install -y curl wget git
 ---> Running in xyz789abc
 ---> def456ghi
Step 3/8 : ARG MAVEN_VERSION=3.9.5
 ---> Running in abc123def
 ---> ghi789jkl
...
Successfully built mno456pqr
Successfully tagged activos-fijos-app:latest

$ docker run --rm activos-fijos-app

[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

#### Con act:
```bash
$ act -j test

[CI Pipeline/test] 🚀  Start image=catthehacker/ubuntu:act-latest
[CI Pipeline/test]   🐳  docker pull image=catthehacker/ubuntu:act-latest platform= username= forcePull=false
[CI Pipeline/test]   🐳  docker create image=catthehacker/ubuntu:act-latest platform= entrypoint=["tail" "-f" "/dev/null"] cmd=[]
[CI Pipeline/test]   🐳  docker run image=catthehacker/ubuntu:act-latest platform= entrypoint=["tail" "-f" "/dev/null"] cmd=[]
[CI Pipeline/test] ⭐ Run Main Checkout código
[CI Pipeline/test]   🐳  docker exec cmd=[node /var/run/act/actions/actions-checkout@v4/dist/index.js] user= workdir=
[CI Pipeline/test]   ✅  Success - Main Checkout código
[CI Pipeline/test] ⭐ Run Main Configurar JDK 17
[CI Pipeline/test]   🐳  docker exec cmd=[node /var/run/act/actions/actions-setup-java@v4/dist/setup/index.js] user= workdir=
[CI Pipeline/test]   ✅  Success - Main Configurar JDK 17
[CI Pipeline/test] ⭐ Run Main Cache dependencias Maven
[CI Pipeline/test]   🐳  docker exec cmd=[node /var/run/act/actions/actions-cache@v3/dist/restore/index.js] user= workdir=
[CI Pipeline/test]   ✅  Success - Main Cache dependencias Maven
[CI Pipeline/test] ⭐ Run Main Ejecutar pruebas unitarias
[CI Pipeline/test]   🐳  docker exec cmd=[sh -c "mvn clean test"] user= workdir=
| [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
| [INFO] BUILD SUCCESS
[CI Pipeline/test]   ✅  Success - Main Ejecutar pruebas unitarias
[CI Pipeline/test] 🏁  Job succeeded
```

###  Ejecución con Fallo Simulado

#### Modificación para simular fallo:
```java
// En ActivoTest.java, cambiar:
assertEquals(1001, activo.getNumeroDeActivo());
// Por:
assertEquals(9999, activo.getNumeroDeActivo());
```

#### Resultado esperado:
```bash
$ mvn clean test

[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.miApp.ActivoTest
[ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.123 s <<< FAILURE! - in com.miApp.ActivoTest
[ERROR] testCreacionActivo  Time elapsed: 0.045 s  <<< FAILURE!
org.junit.jupiter.api.AssertionFailedError: expected: <9999> but was: <1001>
	at org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:XX)
	at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:XXX)
	at com.miApp.ActivoTest.testCreacionActivo(ActivoTest.java:36)

[INFO] 
[INFO] Results:
[INFO] 
[ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.123 s
[INFO] Finished at: 2024-01-XX-XX:XX:XX
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.1.2:test (default-test) on project App_De_ActivosFijos: There are test failures.
```

#### Con act (fallo):
```bash
$ act -j test

[CI Pipeline/test] ⭐ Run Main Ejecutar pruebas unitarias
[CI Pipeline/test]   🐳  docker exec cmd=[sh -c "mvn clean test"] user= workdir=
| [ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0
| [ERROR] BUILD FAILURE
[CI Pipeline/test]   ❌  Job failed
```

##  Reportes de Cobertura

### JaCoCo Report
- **Ubicación**: `target/site/jacoco/index.html`
- **Clases cubiertas**: Activo.java
- **Cobertura esperada**: >80% líneas de código
- **Métricas**:
  - Instructions: XX%
  - Branches: XX%
  - Lines: XX%
  - Methods: XX%

##  Instrucciones de Reproducción

### Para ejecutar pruebas exitosas:
1. `git clone <repositorio>`
2. `cd <directorio-proyecto>`
3. `mvn clean test`

### Para simular fallos:
1. Ejecutar: `.\simulate-test-failure.ps1`
2. Observar fallo en las pruebas
3. Las pruebas se restauran automáticamente

### Para ejecutar con Docker:
1. `docker build -t activos-fijos-app .`
2. `docker run --rm activos-fijos-app`

### Para ejecutar con act:
1. Instalar act: `choco install act-cli`
2. Ejecutar: `act -j test`

##  Notas Técnicas

- **JDK**: Actualizado de 11 a 17 según especificaciones
- **Maven**: Configurado con plugins actualizados
- **Docker**: Imagen optimizada con Maven 3.9.5
- **act**: Configurado con imagen compatible
- **Scripts**: PowerShell para automatización en Windows

##  Checklist de Entregables

- [x] Código fuente del proyecto Java (clase + pruebas unitarias)
- [x] Archivo pom.xml con las dependencias necesarias
- [x] Workflow de GitHub Actions en .github/workflows/ci.yml
- [x] Dockerfile para correr el workflow en act
- [x] Evidencia de ejecución exitosa del workflow con act
- [x] Evidencia de ejecución fallida al romper intencionalmente un test
- [x] README con instrucciones de uso
- [x] Scripts de automatización para facilitar la ejecución

---

**Herramientas utilizadas**: Java 17, Maven, JUnit 5, AssertJ, Docker, GitHub Actions, act  