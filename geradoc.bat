@echo off
echo Gerando o javadoc....
echo.

javadoc -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -d src\main\webapp\guide\javadoc -subpackages br.com.itall -sourcepath src/main/java -source 1.8 -doctitle "ITALL Comercial"

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



