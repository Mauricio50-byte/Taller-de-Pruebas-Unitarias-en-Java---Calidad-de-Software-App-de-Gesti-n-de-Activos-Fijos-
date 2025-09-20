# Descripción: Reemplaza temporalmente las pruebas con versiones que fallan

$originalTest = "src\test\java\com\miApp\ActivoTest.java"
$failingTest = "ActivoTest-FAIL-EXAMPLE.java"
$backupTest = "ActivoTest-BACKUP.java"

Write-Host "=== Simulando fallo en las pruebas ===" -ForegroundColor Yellow

# Hacer backup del archivo original
if (Test-Path $originalTest) {
    Copy-Item $originalTest $backupTest
    Write-Host "✓ Backup creado: $backupTest" -ForegroundColor Green
}

# Reemplazar con la versión que falla
if (Test-Path $failingTest) {
    Copy-Item $failingTest $originalTest
    Write-Host "✓ Pruebas reemplazadas con versión que falla" -ForegroundColor Yellow
    
    Write-Host "=== Ejecutando pruebas (deberían fallar) ===" -ForegroundColor Red
    
    # Ejecutar pruebas con Maven si está disponible
    if (Get-Command "mvn" -ErrorAction SilentlyContinue) {
        mvn clean test
    } else {
        Write-Host "Maven no está disponible. Ejecutar manualmente: mvn clean test" -ForegroundColor Yellow
    }
    
    Write-Host "=== Restaurando pruebas originales ===" -ForegroundColor Green
    
    # Restaurar archivo original
    if (Test-Path $backupTest) {
        Copy-Item $backupTest $originalTest
        Remove-Item $backupTest
        Write-Host "✓ Pruebas originales restauradas" -ForegroundColor Green
    }
    
} else {
    Write-Host "❌ No se encontró el archivo de ejemplo: $failingTest" -ForegroundColor Red
    exit 1
}

Write-Host "=== Simulación completada ===" -ForegroundColor Cyan
Write-Host "Las pruebas ahora deberian pasar nuevamente." -ForegroundColor Green