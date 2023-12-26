package br.com.itall.service;

import javax.servlet.http.HttpServletRequest;

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

}
