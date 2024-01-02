package br.com.itall.service;

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
	 * Monta um objeto ClienteModel a partir do HttpServletRequest
	 * 
	 * @param request (HttpServletRequest)
	 * @return ClienteModel (nullable)
	 */
	public ClienteModel clienteModelFromRequest(HttpServletRequest request);

	/**
	 * Método que insere ou altera um cliente
	 * 
	 * @param request (HttpServletRequest)
	 * @param isInc (boolean) que indica se é inclusão (true) ou alteração (false)
	 * @return ClienteModel
	 * @throws Exception Trata quaisquer erros que venham a ocorrer
	 */
	public ClienteModel clienteAltInc(HttpServletRequest request, boolean isInc) throws Exception;
	
	/**
	 * Lista todos os clientes em ordem alfabética
	 * 
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @return List&lt;ClienteDTO&gt; Lista de clientes DTO para envio ao <i>Client</i>. 
	 * @throws SQLException Trata erros de execução do SQL
	 * @throws ServletException Trata erros de controller
	 * @throws IOException Trata erros de IO
	 * @throws NamingException Trata erros de sintaxe SQL 
	 * @throws Exception Erros diversos de execução
	 */
	public List<ClienteDTO> listAllClientes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, NamingException, Exception;
	
	/**
	 * Método que exclui cliente pelo seu id
	 * 
	 * @param id (Long)
	 * @return Cliente que foi incluído localizado para exclusão
	 * @throws Exception Trata quaisquer erros que venham a ocorrer
	 */
	public ClienteModel clienteDel(Long id) throws Exception;
	
	/**
	 * Método que gera um ClienteModel pelo seu identificador
	 * 
	 * @param id (Long) identificador do cliente.
	 * @return ClienteModel
	 * @throws SQLException Trata erros de execução SQL.
	 */
	public ClienteModel findById(Long id) throws SQLException ;
	
}
