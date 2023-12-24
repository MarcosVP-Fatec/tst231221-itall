package br.com.itall.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneOffset;

import br.com.itall.model.UsuarioModel;
import br.com.itall.model.dao.util.ConnectDAO;

public class UsuarioDAO extends ConnectDAO {
	
	public UsuarioModel inc(UsuarioModel usuario) throws SQLException {
	
		String sql = "insert into usuario (nome, email, senha, data_criacao)"
				   + " values (?,?,?,?)";
		
		conectar();
		
		PreparedStatement ps = getConexao().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1,usuario.getNome());
		ps.setString(2,usuario.getEmail());
		ps.setString(3,usuario.getSenha());
		ps.setDate(4,new Date( usuario.getDataCriacao().toInstant(ZoneOffset.UTC).toEpochMilli() ));
		
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
