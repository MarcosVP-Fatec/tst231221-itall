# Inicia o servico web

echo
echo --------------------------------------------------------
echo Starting Web Server...
echo --------------------------------------------------------
echo

mvn package

export ARQWAR='itall-1.01.0-SNAPSHOT.war'
export VSTOMCAT='apache-tomcat-8.5.97'
export CATALINA_HOME=/workspace/apache-tomcat-8.5.97
export CATALINA_BASE=$CATALINA_HOME
export PATH=$CATALINA_HOME/bin:$PATH
export CATALINA_TMPDIR=$CATALINA_HOME/temp
export CLASSPATH=$CATALINA_HOME/bin/bootstrap.jar;$CATALINA_HOME/bin/tomcat-juli.jar
export MAVEN_OPTS=-Dfile.encoding=UTF-8
export CATALINA_OPTS=-Dfile.encoding=UTF-8
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export JRE_HOME=$JAVA_HOME
export PATH=$JAVA_HOME/bin:$PATH
sudo chmod 777 "$CATALINA_HOME/bin/bootstrap.jar"
sudo chmod 777 "$CATALINA_HOME/bin/tomcat-juli.jar"

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

cp target/$ARQWAR $CATALINA_HOME/webapps/root.war

catalina.sh start
