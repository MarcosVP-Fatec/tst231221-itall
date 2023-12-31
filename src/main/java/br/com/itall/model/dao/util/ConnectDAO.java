package br.com.itall.model.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

/**
 * Classe de Controle do Pool de Conexões. <br>
 * Esta classe fornece conexões conforme a necessidade da aplicação. <br>
 * Utilzada com o extensão das classes DAO.
 * 
 * @author MarcosVP
 * @since 25/12/2023
 * @version 1.01.0
 * @see <a href="META-INF/context.xml">context.xml</a> 
 *
 */
public class ConnectDAO {
	
	private Connection con;
	
	/**
	 * Método que pede uma conexão e a guarda para uso da classe filha DAO.
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 * @throws NamingException Superclasse de todas as exceções lançadas por operações nas interfaces Context e DirContext. Foi adaptada para estar em conformidade com o mecanismo de encadeamento de exceções de uso geral.
	 */
	public void conectar() throws SQLException, NamingException {
		if (con == null || con.isClosed() ) con = ConnectDB.getConnection();
	}
	
	/**
	 * Método que fecha a conexão recebida anteriormente para uso da classe filha DAO.<br>
	 * Antes de fechar a conexão faz um Rollback(). 
	 * 
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL). 
	 */
	public void desconectar() throws SQLException {
		if (con != null && !con.isClosed()) {
			try { getConexao().rollback(); } catch (Exception e) {}
			con.close();
		}
	}
	
	/**
	 * Get que retorna a conexão atual <br>
	 * Não pode realizar uma conexão porque pode ser utilizada várias vêzes dentro do DAO filho.	  
	 * @return con (Connection)
	 */
	public Connection getConexao() {
		return this.con;
	}

}
