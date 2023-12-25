package br.com.itall.service;

import javax.servlet.http.HttpServletRequest;

import br.com.itall.model.UsuarioModel;

/**
 * "Service" da entidade UsuarioModel
 * @author MarcosVP
 * @see br.com.itall.model.UsuarioModel
 * @since 24/12/2023
 */
public interface UsuarioService {

	/**
	 * @apiNote Gera um UsuarioModel a partir do HttpServletRequest
	 * @param request (HttpServletRequest)
	 * @return UsuarioModel (nullable)
	 */
	public UsuarioModel usuarioModelFromRequest(HttpServletRequest request);

	/**
	 * @apiNote Insere um novo usuário
	 * @param usuario (UsuarioModel)
	 * @throws Exception 
	 */
	public UsuarioModel usuarioInc(UsuarioModel usuario) throws Exception;

}
