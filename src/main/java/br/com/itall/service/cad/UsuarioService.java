package br.com.itall.service.cad;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

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
	 * Método que insere ou altera um usuário
	 * 
	 * @param usuario (UsuarioModel)
	 * @param isInc (boolean) que indica se é inclusão (true) ou alteração (false)
	 * @return UsuarioModel
	 * @throws Exception Lançamento geral de erros de execução.
	 */
	public UsuarioModel usuarioAltInc(UsuarioModel usuario, boolean isInc) throws Exception;
	
	/**
	 * Lista de todos os usuários em ordem alfabética
	 * 
	 * @return List&lt;UsuarioDTO&gt; Lista de usuários DTO para envio ao <i>Client</i>. 
	 * @throws Exception Lançamento geral de erros de execução.
	 * 
	 */
	public List<UsuarioDTO> listAllUsuarios() throws Exception;
	
	/**
	 * Método que exclui usuário pelo seu id
	 * 
	 * @param id (Long)
	 * @return UsuarioModel que foi incluído localizado para exclusão
	 * @throws Exception Lançamento geral de erros de execução.
	 */
	public UsuarioModel usuarioDel(Long id) throws Exception;
	
	/**
	 * Método que gera um UsuarioModel pelo seu identificador
	 * 
	 * @param id (Long) identificador do usuário.
	 * @return UsuarioModel
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public UsuarioModel findById(Long id) throws SQLException ;
	
	/**
	 * Método padrão que criptografa uma senha
	 * 
	 * @param email (String) Email do Usuário
	 * @param senha (String) Senha do Usuário
	 * @return String (MD5)
	 * @throws NoSuchAlgorithmException Trata erros ao calcular o hash da senha
	 */
	public String criptSenha(String email, String senha) throws NoSuchAlgorithmException;

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
