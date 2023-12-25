package br.com.itall.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import br.com.itall.model.UsuarioModel;
import br.com.itall.model.dao.util.ConnectDAO;

/**
 * DAO da entidade UsuarioModel
 * @author MarcosVP
 * @see br.com.itall.model.UsuarioModel
 */
public class UsuarioDAO extends ConnectDAO {
	
	public UsuarioModel inc(UsuarioModel usuario) throws SQLException {
	
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
