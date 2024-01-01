# Inicia o servico web
# As variáveis de ambiente são iniciadas no .gitpod.yml

echo
echo --------------------------------------------------------
echo Starting Web Server...
echo --------------------------------------------------------
echo

mvn package

export ARQWAR='itall-1.01.0-SNAPSHOT.war'

#------------------------------------------
# Fecha a porta 8080 se já estiver em uso
#------------------------------------------
export PORTA=8080
if lsof -Pi :$PORTA -sTCP:LISTEN -t >/dev/null; then
    echo "A porta $PORTA está em uso. Tentando encerrar..."
    PROCESS_TO_KILL=$(lsof -ti :$PORTA)
    kill $PROCESS_TO_KILL
    echo "Porta $PORTA encerrada."
fi

cp target/$ARQWAR $CATALINA_HOME/webapps/itall.war

catalina.sh start
