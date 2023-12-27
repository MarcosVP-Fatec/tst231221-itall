package br.com.itall.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.model.dto.UsuarioDTO;
import br.com.itall.model.entity.cad.UsuarioModel;

/**
 * "Service" da entidade UsuarioModel
 * @author MarcosVP
 * @see br.com.itall.model.entity.cad.UsuarioModel
 * @since 24/12/2023
 */
public interface UsuarioService {

	/**
	 * Monta um objeto UsuarioModel a partir do HttpServletRequest
	 * 
	 * @param request (HttpServletRequest)
	 * @return UsuarioModel (nullable)
	 */
	public UsuarioModel usuarioModelFromRequest(HttpServletRequest request);

	/**
	 * Método que insere um novo usuário
	 * 
	 * @param usuario (UsuarioModel)
	 * @return UsuarioModel que foi incluído com o id já identificado.
	 * @throws Exception Trata quaisquer erros que venham a ocorrer
	 */
	public UsuarioModel usuarioInc(UsuarioModel usuario) throws Exception;
	
	/**
	 * Lista todos os usuários em ordem alfabética
	 * 
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @return List&lt;UsuarioDTO&gt; Lista de usuários DTO para envio ao <i>Client</i>. 
	 * @throws SQLException Trata erros de execução do SQL
	 * @throws ServletException Trata erros de controller
	 * @throws IOException Trata erros de IO
	 * @throws NamingException Trata erros de sintaxe SQL 
	 * @throws Exception Erros diversos de execução
	 */
	public List<UsuarioDTO> listAllUsuarios(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, NamingException, Exception;
	
	/**
	 * Método que exclui novo usuário pelo seu id
	 * 
	 * @param id (Long)
	 * @return UsuarioModel que foi incluído localizado para exclusão
	 * @throws Exception Trata quaisquer erros que venham a ocorrer
	 */
	public UsuarioModel usuarioDel(Long id) throws Exception;


}
