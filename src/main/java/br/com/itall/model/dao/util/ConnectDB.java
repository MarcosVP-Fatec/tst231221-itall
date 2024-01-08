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
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 * @throws NamingException Superclasse de todas as exceções lançadas por operações nas interfaces Context e DirContext. Foi adaptada para estar em conformidade com o mecanismo de encadeamento de exceções de uso geral.
	 *  
	 */
	public static Connection getConnection() throws SQLException, NamingException {
		return ((DataSource) (new InitialContext()).lookup(RESOURCE)).getConnection();
	}

}
