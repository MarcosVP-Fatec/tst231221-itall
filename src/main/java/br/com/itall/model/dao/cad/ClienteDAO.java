package br.com.itall.model.dao.cad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.itall.model.dao.util.ConnectDAO;
import br.com.itall.model.dao.util.SqlPreparedStatementUpdate;
import br.com.itall.model.dto.cad.ClienteDTO;
import br.com.itall.model.entity.cad.ClienteModel;
import br.com.itall.tool.Data;
import br.com.itall.tool.Texto;

/**
* DAO da entidade ClienteModel
* 
* @author MarcosVP
* @see br.com.itall.model.entity.cad.ClienteModel
*/
public class ClienteDAO extends ConnectDAO {

	/**
	 * Método para inclusão de cliente
	 * 
	 * @param cliente (ClienteModel)
	 * @return ClienteModel (Com o id já identificado)
	 * @throws Exception Lançamento geral de erros de execução.
	 */
	public ClienteModel inc(ClienteModel cliente) throws Exception {

		try {
			
			conectar();

			PreparedStatement ps = cliente.generateSqlInsert(getConexao());

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

			cliente.setId(idGerado);

		} catch (Exception e) {
			desconectar(); //e.printStackTrace();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na inclusão do cliente \"%s\": %s",cliente.getNome(),e.getMessage()));
		}
		
		return cliente;
	}

	/**
	 * Método para alteração de cliente
	 * 
	 * @param cliente (ClienteModel)
	 * @return ClienteModel
	 * @throws Exception Lançamento geral de erros de execução.
	 */
	public ClienteModel alt(ClienteModel cliente) throws Exception {

		try {
			
			String sql = "update clientes (SET) where id = ?";
			
			ClienteModel cliDestino = findById(cliente.getId());
			
			SqlPreparedStatementUpdate psup = SqlPreparedStatementUpdate.get(cliente, cliDestino, sql, cliente.getId());
			
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
			throw new RuntimeException(String.format("Erro inesperado na inclusão do cliente: %s",e.getMessage()));
		}
		
		return cliente;

	}

	/**
	 * Monta um ClienteModel a partir de um ResultSet
	 * @param rs (ResultSet)
	 * @return ClienteModel
	 */
	public ClienteModel clienteModelFromResultSet(ResultSet rs) {
		try { 
           return new ClienteModel(rs.getLong("id")
        		                  ,rs.getString("nome")
        		                  ,rs.getString("sobrenome")
        		                  ,rs.getString("sexo")
        		                  ,Data.convertDateToLocalDate(rs.getDate("dataNascimento"))
        		                  ,rs.getString("nacionalidade")
        		                  ,rs.getString("email")
        		                  ,rs.getString("endereco")
        		                  ,rs.getString("cidade")
        		                  ,rs.getString("estado")
        		                  ,rs.getString("telefone"));
        		   
		} catch (Exception e) {
			Texto.logConsole(e);
		}
		return null;
	}

	/**
	 * Busca um ClienteModel pelo seu id
	 * 
	 * @param id (Long) identificador do cliente.
	 * @return ClienteModel
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public ClienteModel findById(Long id) throws SQLException {
		
		ClienteModel cliente = null;
		
		try {
			
			final String sql = "select * from clientes C where C.id = ?";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setLong(1, id );
			
			Texto.logSQL(ps);
			getConexao().setAutoCommit(true);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cliente = clienteModelFromResultSet(rs);
			}
			
		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na localização do cliente (Id: %d): ",id,e.getMessage()));
		}
		
		desconectar();
		return cliente;
	}

	/**
	 * Lista todos os cliente em ordem alfabética
	 * 
	 * @return List&lt;ClienteDTO&gt;
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 * @throws NamingException Superclasse de todas as exceções lançadas por operações nas interfaces Context e DirContext. Foi adaptada para estar em conformidade com o mecanismo de encadeamento de exceções de uso geral.
	 */
	public List<ClienteDTO> listarTodosClientes() throws SQLException, NamingException {

		String sql = "select * from clientes C order by upper(C.nome), upper(C.sobrenome), C.email";
		List<ClienteDTO> lista = new ArrayList<ClienteDTO>();

		conectar();
		PreparedStatement ps = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		Texto.logSQL(ps);
		getConexao().setAutoCommit(true);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			
			lista.add(new ClienteDTO(rs.getLong("id")
		                  			,rs.getString("nome")
		                  			,rs.getString("sobrenome")
		                  			,rs.getString("email")
		                  			,rs.getString("cidade")
		                  			,rs.getString("estado")
		                  			,rs.getString("telefone")));
		}
		
		rs.close();
		ps.close();
		desconectar();
		return lista;
	}
	
	/**
	 * Verifica se um determinado e-mail existe na tabela de clientes (Pesquisa exata).
	 * 
	 * @param email (String) Conta de e-mail
	 * @return boolean
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public boolean existByEmail(String email) throws SQLException {
		
		try {
			
			final String sql = "select C.id from clientes C where C.email = ?";

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
	 * Verifica se um determinado e-mail existe na tabela de clientes para outro cliente
	 * 
	 * @param email (String) Conta de e-ail
	 * @param id (Long) Identificador do cliente atual
	 * @return boolean
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public boolean existByEmailOderUser(String email, Long id) throws SQLException {
		
		try {
			
			final String sql = "select C.id from clientes C where C.id != ? and C.email = ?";

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
	 * Método para exclusão de cliente
	 * 
	 * @param id (Long) Identificador do cliente
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL). 
	 */
	public void del(Long id) throws SQLException {

		try {
			
			final String sql = "delete from clientes where id = ?";

			conectar();

			PreparedStatement ps = getConexao().prepareStatement(sql);

			ps.setLong(1, id );

			getConexao().setAutoCommit(false);
			ps.executeUpdate();
			getConexao().commit();

		} catch (Exception e) {
			desconectar();
			Texto.logConsole(e);
			throw new RuntimeException(String.format("Erro inesperado na inclusão do cliente: %s",e.getMessage()));
		}
		
		desconectar();
		
	}
}

