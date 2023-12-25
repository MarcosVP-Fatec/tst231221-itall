/**
 * Pacote de utilitários DAO
 * @author MarcosVP
 * @since 24/12/2023
 */
package br.com.itall.model.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe de Controle do Pool de Conexões
 * Esta classe fornece conexões conforme a necessidade
 * @author MarcosVP
 * @since 25/12/2023
 * @see context.xml
 *
 */
public class ConnectDAO {
	
	private Connection con;
	
	public void conectar() throws SQLException {
		if (con == null || con.isClosed() ) con = ConnectDB.getConnection();
	}
	
	public void desconectar() throws SQLException {
		if (con != null && !con.isClosed()) con.close();
	}
	
	public Connection getConexao() {
		return this.con;
	}

}
