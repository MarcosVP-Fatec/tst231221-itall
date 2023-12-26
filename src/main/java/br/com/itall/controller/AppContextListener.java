package br.com.itall.controller;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.itall.tool.Texto;

/**
 * Configuração de contexto da JVM. <br>
 * Ajusta o fuso horário para GMT-3
 *  
 * @author MarcosVP
 * @since 25/12/2023
 * @version 1.01.0
 * @see <a href="file:../../../../../webapp/WEB-INF/web.xml">web.xml</a>
 * 
 */
public class AppContextListener implements ServletContextListener {
	
    public void contextInitialized(ServletContextEvent ev) {
    	Texto.logConsole("Inicialização de contexto executada");
  		// Define o fuso horário como padrão para a JVM GMT-3 (Brasília)
    	Texto.logConsole("Fuso horário da JVM atribuído como \"GMT-3\"");
   		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
    }

	@Override
	public void contextDestroyed(ServletContextEvent ev) {
	}
}

