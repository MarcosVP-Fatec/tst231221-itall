package br.com.itall.service.cad;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.model.dto.cad.ClienteDTO;
import br.com.itall.model.entity.cad.ClienteModel;

/**
 * "Service" da entidade ClienteModel
 * @author MarcosVP
 * @see br.com.itall.model.entity.cad.ClienteModel
 * @since 02/01/2024
 * @version 1.01.0
 */
public interface ClienteService {

	/**
	 * Método que insere ou altera um cliente
	 * 
	 * @param cliente (ClienteModel)
	 * @param isInc (boolean) que indica se é inclusão (true) ou alteração (false)
	 * @return ClienteModel
	 * @throws Exception Lançamento geral de erros de execução.
	 */
	public ClienteModel clienteAltInc(ClienteModel cliente, boolean isInc) throws Exception;
	
	/**
	 * Lista todos os clientes em ordem alfabética
	 * 
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @return List&lt;ClienteDTO&gt; Lista de clientes DTO para envio ao <i>Client</i>. 
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 * @throws NamingException Superclasse de todas as exceções lançadas por operações nas interfaces Context e DirContext. Foi adaptada para estar em conformidade com o mecanismo de encadeamento de exceções de uso geral. 
	 * @throws Exception Lançamento geral de erros de execução.
	 * 
	 */
	public List<ClienteDTO> listAllClientes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, NamingException, Exception;
	
	/**
	 * Método que exclui cliente pelo seu id
	 * 
	 * @param id (Long)
	 * @return Cliente que foi incluído localizado para exclusão
	 * @throws Exception Lançamento geral de erros de execução.
	 */
	public ClienteModel clienteDel(Long id) throws Exception;
	
	/**
	 * Método que gera um ClienteModel pelo seu identificador
	 * 
	 * @param id (Long) identificador do cliente.
	 * @return ClienteModel
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public ClienteModel findById(Long id) throws SQLException ;
	
}
