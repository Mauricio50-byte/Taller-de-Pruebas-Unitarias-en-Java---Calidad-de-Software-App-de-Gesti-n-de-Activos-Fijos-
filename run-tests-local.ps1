# Script para ejecutar las pruebas localmente con Docker
# Autor: Asistente de IA
# Descripción: Construye la imagen Docker y ejecuta las pruebas unitarias

Write-Host "=== Construyendo imagen Docker ===" -ForegroundColor Green
docker build -t activos-fijos-app .

if ($LASTEXITCODE -eq 0) {
    Write-Host "=== Imagen construida exitosamente ===" -ForegroundColor Green
    
    Write-Host "=== Ejecutando pruebas unitarias ===" -ForegroundColor Yellow
    docker run --rm -v ${PWD}:/workspace activos-fijos-app
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "=== Pruebas ejecutadas exitosamente ===" -ForegroundColor Green
    } else {
        Write-Host "=== Error en las pruebas ===" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "=== Error al construir la imagen ===" -ForegroundColor Red
    exit 1
}

Write-Host "=== Proceso completado ===" -ForegroundColor Cyan