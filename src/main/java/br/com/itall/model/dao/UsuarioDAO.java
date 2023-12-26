package br.com.itall.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.naming.NamingException;

import br.com.itall.model.dao.util.ConnectDAO;
import br.com.itall.model.entity.cad.UsuarioModel;

/**
 * DAO da entidade UsuarioModel
 * @author MarcosVP
 * @see br.com.itall.model.entity.cad.UsuarioModel
 */
public class UsuarioDAO extends ConnectDAO {
	
	/**
	 * Método para inclusão de usuário
	 * 
	 * @param usuario (UsuarioModel)
	 * @return UsuarioModel (Com o id já identificado)
	 * @throws SQLException Se ocorrer erro na execução do SQL
	 * @throws NamingException Se ocorrer algum erro de Sintaxe SQL
	 */
	public UsuarioModel inc(UsuarioModel usuario) throws SQLException, NamingException {
	
		String sql = "insert into usuario (nome, email, senha, data_criacao)"
				   + " values (?,?,?,?)";
		
		conectar();
		
		PreparedStatement ps = getConexao().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1,usuario.getNome());
		ps.setString(2,usuario.getEmail().getDescription());
		ps.setString(3,usuario.getSenha());
		ps.setTimestamp(4,Timestamp.valueOf(usuario.getDataCriacao()));

		getConexao().setAutoCommit(false);
		int linhasAfetadas = ps.executeUpdate();
		getConexao().commit();
		
		long idGerado = 0;
		
		if (linhasAfetadas > 0) {
		    ResultSet chavesGeradas = ps.getGeneratedKeys();
		    if (chavesGeradas.next()) {
		        idGerado = chavesGeradas.getLong(1); // Obtém a chave gerada automaticamente
		    }
		}

		desconectar();
		
		return usuario.setId(idGerado);
		
	}
	
}
