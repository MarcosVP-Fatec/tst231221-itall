@echo off

REM 
REM Comando de prompt para geração do JavaDoc
REM 
REM Na raiz do repositório usar: .res\geradoc
REM Este script utiliza os arquivos javadoc.properties e javadoc.xml.
REM 

echo Gerando o javadoc....
echo.

if exist javadoc.xml (SET VOLTA=nao) else (SET VOLTA=sim)

if %VOLTA%==sim cd .res

javadoc -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -d ..\src\main\webapp\guide\javadoc -subpackages br.com.itall -sourcepath ../src/main/java -source 1.8 -doctitle "ITALL Comercial"

if %errorlevel% EQU 0 (

   echo.
   echo Documento gerado com SUCESSO!

) else (

   echo.
   echo =================================================
   echo =================================================
   echo.
   echo "CORRIJA OS ERROS!"
   echo.
   echo =================================================
   echo =================================================

)

:fim
if %VOLTA%==sim cd ..

