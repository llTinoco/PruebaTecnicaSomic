@echo off
REM Cambiar al directorio donde se encuentra este script
cd /d "%~dp0"

REM Verificar si el archivo JAR existe en este directorio
if not exist "v1-0.0.1-SNAPSHOT.jar" (
    echo Error: No se encontro "v1-0.0.1-SNAPSHOT.jar" en %~dp0
    pause
    exit /b 1
)

REM Ejecutar el JAR
echo Iniciando la aplicacion...
java -jar "v1-0.0.1-SNAPSHOT.jar"

pause