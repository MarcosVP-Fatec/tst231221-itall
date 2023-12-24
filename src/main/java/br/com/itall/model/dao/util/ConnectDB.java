package br.com.itall.model.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @apiNote Classe de conexão com o Banco de Dados <br>
 *          Esta classe utiliza a configuração dos arquivos <br>
 *          - context.xml <br>
 *          - web.xml
 * @author MarcosVP
 * 
 */
public class ConnectDB {

	private static final String RESOURCE = "java:/comp/env/jdbc/mysql";

	/**
	 * @apiNote Método que lê o recurso e distribui as conexões conforme necessário. <br>
	 *          > ConectDB.getConnection()
	 * 
	 */
	public static Connection getConnection() {
		try {
			return ((DataSource) (new InitialContext()).lookup(RESOURCE)).getConnection();
		} catch (SQLException | NamingException e) {
			throw new RuntimeException(e);
		}

	}

}
