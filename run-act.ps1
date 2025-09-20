# Script para ejecutar el workflow de GitHub Actions localmente con act
# Autor: Asistente de IA
# Descripción: Ejecuta el workflow de CI usando act

Write-Host "=== Verificando que act esté instalado ===" -ForegroundColor Green

# Verificar si act está instalado
if (!(Get-Command "act" -ErrorAction SilentlyContinue)) {
    Write-Host "act no está instalado. Por favor instálalo desde: https://github.com/nektos/act" -ForegroundColor Red
    Write-Host "O usando chocolatey: choco install act-cli" -ForegroundColor Yellow
    exit 1
}

Write-Host "=== Ejecutando workflow con act ===" -ForegroundColor Yellow
act -j test

if ($LASTEXITCODE -eq 0) {
    Write-Host "=== Workflow ejecutado exitosamente con act ===" -ForegroundColor Green
} else {
    Write-Host "=== Error en el workflow con act ===" -ForegroundColor Red
    exit 1
}

Write-Host "=== Proceso completado ===" -ForegroundColor Cyan