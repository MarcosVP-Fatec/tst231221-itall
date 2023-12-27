package br.com.itall.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.itall.model.dao.util.ConnectDAO;
import br.com.itall.model.dto.UsuarioDTO;
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
	 * @throws Exception 
	 */
	public UsuarioModel inc(UsuarioModel usuario) throws Exception {

		try {
			
			final String sql = "insert into usuario (nome, email, senha, data_criacao) values (?,?,?,?)";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail().getDescription());
			ps.setString(3, usuario.getSenha());
			ps.setTimestamp(4, Timestamp.valueOf(usuario.getDataCriacao()));

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

			usuario.setId(idGerado);

		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na inclusão do usuário: %s",e.getMessage()));
		}
		
		return usuario;

	}

	/**
	 * Método para selecionar todos os usuários
	 * 
	 * @param usuario (UsuarioModel)
	 * @return UsuarioModel (Com o id já identificado)
	 * @throws SQLException Se ocorrer erro na execução do SQL
	 * @throws NamingException Se ocorrer algum erro de Sintaxe SQL
	 */
	
	/**
	 * Lista todos os usuários em ordem alfabética
	 * 
	 * @return List&lt;Usuario&gt;
	 * @throws SQLException Trata erros de execução do SQL
	 * @throws NamingException Trata erros de sintaxe do SQL
	 */
	public List<UsuarioDTO> listarTodosUsuarios() throws SQLException, NamingException {

		String sql = "select * from usuario u order by upper(u.nome), u.email";
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

		conectar();
		PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
	 * @param email (String) Conta de e-ail
	 * @return boolean
	 * @throws SQLException Trata erros de execução SQL.
	 */
	public boolean existByEmail(String email) throws SQLException {
		
		try {
			
			final String sql = "select u.id from usuario u where u.email = ?";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, email.trim().toLowerCase());
			
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

}
