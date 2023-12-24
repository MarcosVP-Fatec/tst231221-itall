package br.com.itall.model.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

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
