package br.com.itall.model.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Controle do Pool de Conexões com os Bancos de Dados <br>
 * 
 * @author MarcosVP
 * @since 25/12/2023
 * @version 1.01.0
 * @see <a href="file:../../../../../webapp/META-INF/context.xml">context.xml</a>
 * @see <a href="file:../../../../../webapp/WEB-INF/web.xml">web.xml</a>
 * 
 */
public class ConnectDB {

	private static final String RESOURCE = "java:/comp/env/jdbc/mysql";

	/** 
	 * Método que lê o recurso e distribui as conexões conforme necessário. <br>
	 * @return Connection 
	 * @throws SQLException Se ocorrer erro na execução do SQL
	 * @throws NamingException Se ocorrer algum erro de Sintaxe SQL
	 *  
	 */
	public static Connection getConnection() throws SQLException, NamingException {
		return ((DataSource) (new InitialContext()).lookup(RESOURCE)).getConnection();
	}

}
