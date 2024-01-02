package br.com.itall.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.model.dto.cad.UsuarioDTO;
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
	 * Método que insere ou altera um usuário
	 * 
	 * @param request (HttpServletRequest)
	 * @param isInc (boolean) que indica se é inclusão (true) ou alteração (false)
	 * @return UsuarioModel
	 * @throws Exception Trata quaisquer erros que venham a ocorrer
	 */
	public UsuarioModel usuarioAltInc(HttpServletRequest request, boolean isInc) throws Exception;
	
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
	 * Método que exclui usuário pelo seu id
	 * 
	 * @param id (Long)
	 * @return UsuarioModel que foi incluído localizado para exclusão
	 * @throws Exception Trata quaisquer erros que venham a ocorrer
	 */
	public UsuarioModel usuarioDel(Long id) throws Exception;
	
	/**
	 * Método que gera um UsuarioModel pelo seu identificador
	 * 
	 * @param id (Long) identificador do usuário.
	 * @return UsuarioModel
	 * @throws SQLException Trata erros de execução SQL.
	 */
	public UsuarioModel findById(Long id) throws SQLException ;
	
	/**
	 * Método padrão que criptografa uma senha
	 * 
	 * @param nome (String) Nome do Usuário
	 * @param email (String) Email do Usuário
	 * @param senha (String) Senha do Usuário
	 * @return String (MD5)
	 * @throws NoSuchAlgorithmException Trata erros ao calcular o hash da senha
	 */
	public String criptSenha(String nome, String email, String senha) throws NoSuchAlgorithmException;

	/**
	 * Método padrão que criptografa uma senha
	 * 
	 * @param usuario (UsuarioModel) 
	 * @param novaSenha Nova Senha do Usuário
	 * @return String (MD5)
	 * @throws NoSuchAlgorithmException Trata erros ao calcular o hash da senha
	 */
	public String criptSenha(UsuarioModel usuario, String novaSenha) throws NoSuchAlgorithmException;
}
