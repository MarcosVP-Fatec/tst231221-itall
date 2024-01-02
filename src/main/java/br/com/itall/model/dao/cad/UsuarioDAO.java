package br.com.itall.model.dao.cad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.itall.model.dao.util.ConnectDAO;
import br.com.itall.model.dao.util.SqlPreparedStatementUpdate;
import br.com.itall.model.dto.cad.UsuarioDTO;
import br.com.itall.model.entity.cad.UsuarioModel;
import br.com.itall.tool.Texto;

/**
 * DAO da entidade UsuarioModel
 * 
 * @author MarcosVP
 * @see br.com.itall.model.entity.cad.UsuarioModel
 */
public class UsuarioDAO extends ConnectDAO {

	/**
	 * Método para inclusão de usuário
	 * 
	 * @param usuario (UsuarioModel)
	 * @return UsuarioModel (Com o id já identificado)
	 * @throws Exception Tratamento de erros em runtime da inclusão do usuário
	 */
	public UsuarioModel inc(UsuarioModel usuario) throws Exception {

		try {
			
			final String sql = "insert into usuarios (nome, email, senha, data_criacao) values (?,?,?,?)";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail().getDescription());
			ps.setString(3, usuario.getSenha());
			ps.setTimestamp(4, Timestamp.valueOf(usuario.getDataCriacao()));
			
			Texto.logSQL(ps);
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
			usuario.setId(idGerado);

		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na inclusão do usuário: %s",e.getMessage()));
		}
		
		return usuario;

	}

	/**
	 * Método para alteração de usuário
	 * 
	 * @param usuario (UsuarioModel)
	 * @return UsuarioModel
	 * @throws Exception Tratamento de erros em runtime da inclusão do usuário
	 */
	public UsuarioModel alt(UsuarioModel usuario) throws Exception {

		try {
			
			String sql = "update usuarios (SET) where id = ?";
			
			UsuarioModel usuDestino = findById(usuario.getId());
			
			SqlPreparedStatementUpdate psup = SqlPreparedStatementUpdate.get(usuario, usuDestino, sql, usuario.getId());
			
			sql = psup.getSqlExpression(); 

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql);
			
			psup.multiSets(ps);

			Texto.logSQL(ps);
			getConexao().setAutoCommit(false);
			ps.executeUpdate();
			getConexao().commit();

		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na inclusão do usuário: %s",e.getMessage()));
		}
		
		return usuario;

	}

	/**
	 * Lista todos os usuários em ordem alfabética
	 * 
	 * @return List&lt;UsuarioDTO&gt;
	 * @throws SQLException Trata erros de execução do SQL
	 * @throws NamingException Trata erros de sintaxe do SQL
	 */
	public List<UsuarioDTO> listarTodosUsuarios() throws SQLException, NamingException {

		String sql = "select * from usuarios u order by upper(u.nome), u.email";
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

		conectar();
		PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		Texto.logSQL(ps);
		getConexao().setAutoCommit(true);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			
			lista.add(new UsuarioDTO(rs.getLong("id")
					                ,rs.getString("nome")
					                ,rs.getString("email")
					                ,rs.getTimestamp("data_criacao").toLocalDateTime()
					                ));
		}
		
		rs.close();
		ps.close();
		desconectar();
		return lista;
	}
	
	
	/**
	 * Verifica se um determinado e-mail existe na tabela de usuários
	 * 
	 * @param email (String) Conta de e-mail
	 * @return boolean
	 * @throws SQLException Trata erros de execução SQL.
	 */
	public boolean existByEmail(String email) throws SQLException {
		
		try {
			
			final String sql = "select u.id from usuarios u where u.email = ?";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, email.trim().toLowerCase());
			
			Texto.logSQL(ps);
			getConexao().setAutoCommit(true);
			boolean existe = ps.executeQuery().next(); 

			ps.close();
			desconectar();
			
			return existe;

		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na pesquisa do email \"%s\": %s",email,e.getMessage()));
		}
		
	}

	/**
	 * Verifica se um determinado e-mail existe na tabela de usuários para outro usuário
	 * 
	 * @param email (String) Conta de e-ail
	 * @param id (Long) Identificador do usuário atual
	 * @return boolean
	 * @throws SQLException Trata erros de execução SQL.
	 */
	public boolean existByEmailOderUser(String email, Long id) throws SQLException {
		
		try {
			
			final String sql = "select u.id from usuarios u where u.id != ? and u.email = ?";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setLong(1, id);
			ps.setString(2, email.trim().toLowerCase());
			
			Texto.logSQL(ps);
			getConexao().setAutoCommit(true);
			boolean existe = ps.executeQuery().next(); 

			ps.close();
			desconectar();
			
			return existe;

		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na pesquisa do email \"%s\": %s",email,e.getMessage()));
		}
		
	}

	/**
	 * Busca um UsuarioModel pelo seu id
	 * 
	 * @param id (Long) identificador do usuário.
	 * @return UsuarioModel
	 * @throws SQLException Trata erros de execução SQL.
	 */
	public UsuarioModel findById(Long id) throws SQLException {
		
		UsuarioModel usuario = null;
		
		try {
			
			final String sql = "select * from usuarios u where u.id = ?";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setLong(1, id );
			
			Texto.logSQL(ps);
			getConexao().setAutoCommit(true);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario = usuarioModelFromResultSet(rs);
			}

		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na localização do usuário (Id: %d): ",id,e.getMessage()));
		}
		
		desconectar();
		return usuario;
		
	}

	/**
	 * Monta um UsuarioModel a partir de um ResultSet
	 * @param rs (ResultSet)
	 * @return UsuarioModel
	 */
	public UsuarioModel usuarioModelFromResultSet(ResultSet rs) {
		try {
            return new UsuarioModel(rs.getLong("id")
	                               ,rs.getString("nome")
	                               ,rs.getString("email")
	                               ,false
	                               ,rs.getString("senha")
	                               ,rs.getTimestamp("data_criacao").toLocalDateTime()
	                               );

		} catch (Exception e) {
			Texto.logConsole(e);
		}
		return null;
	}

	/**
	 * Método para exclusão de usuário
	 * 
	 * @param id (Long) Identificador do usuário
	 * @throws SQLException Tratamento de erros de SQL 
	 */
	public void del(Long id) throws SQLException {

		try {
			
			final String sql = "delete from usuarios where id = ?";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql);

			ps.setLong(1, id );

			getConexao().setAutoCommit(false);
			ps.executeUpdate();
			getConexao().commit();

		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na inclusão do usuário: %s",e.getMessage()));
		}
		
		desconectar();
		
	}

}
