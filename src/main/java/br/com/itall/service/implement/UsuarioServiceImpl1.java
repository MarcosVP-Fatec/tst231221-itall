/**
 * Pacote de implementações dos serviços
 */
package br.com.itall.service.implement;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.model.dao.cad.UsuarioDAO;
import br.com.itall.model.dto.UsuarioDTO;
import br.com.itall.model.entity.cad.UsuarioModel;
import br.com.itall.service.UsuarioService;
import br.com.itall.service.security.Criptografia;
import br.com.itall.tool.Data;

/**
 * Implementação nº 1 dos Serviços de UsuarioModel
 * @author MarcosVP
 * @since 25/12/2023
 */
public class UsuarioServiceImpl1 implements UsuarioService {
	
	private UsuarioDAO usuarioDAO;
	/** Básico */
	public UsuarioServiceImpl1() { this.init();	}
	/** Injeção das dependências do serviço */
	public void init() {
		this.usuarioDAO = new UsuarioDAO();
	}

	@Override
	public UsuarioModel usuarioModelFromRequest(HttpServletRequest request) {
		
		try {
			
			String idString = request.getParameter("id");
			Long id = (idString==null||idString.equals("")?null:Long.valueOf(idString));
			
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			Boolean isMudarSenha = request.getParameter("isMudarSenha") != null; 
			String senha = request.getParameter("senha");
			if (senha == null) senha = "";
			
			String sData = request.getParameter("dataCriacao");
			LocalDateTime dataCriacao = (sData==null||sData.equals("")?null:Data.toDateTime(sData));
			
			return new UsuarioModel(id, nome, email, isMudarSenha, senha, dataCriacao);
			
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public UsuarioModel usuarioAltInc(HttpServletRequest request, boolean isInc) throws Exception {
		
		UsuarioModel usuario = usuarioModelFromRequest(request);
		
		final String tipo = isInc ? "inclusão" : "alteração";
		try {

			if (usuario == null) throw new RuntimeException(String.format("Nenhum usuário foi informado para esta %s!", tipo));
			
			if (isInc) {
				
				if (usuario.getId() != null) throw new RuntimeException(String.format("Tentativa de incluir usuário com identificador informado: Id %s", usuario.getId()));
				if (usuario.getSenha().isEmpty()) throw new RuntimeException("Tentativa de gravar usuário com \"senha\" em branco!");
				if (usuario.getSenha().length() < UsuarioModel.SENHA_FIELD_LEN_MIN) throw new RuntimeException("Tentativa de gravar usuário com tamanho de \"senha\" inválido!");
				
			} else {
				
				if (usuario.getId() == null) throw new RuntimeException("Tentativa de alterar usuário sem identificador informado.");
				/**
				 * Para mudança de senha o usuário precisa confirmar sua senha anterior.
				 */
				if (usuario.isMudarSenha()) {
					
					String senhaAnterior = request.getParameter("senhaAnterior");
					UsuarioModel usuOld = this.findById(usuario.getId());
					
					if (senhaAnterior.equals(usuario.getSenha())) {
						throw new RuntimeException("Informe uma senha diferente para realizar a alteração!");
					}
					if (!criptSenha(usuario, senhaAnterior).equals(usuOld.getSenha()) ){
						throw new RuntimeException("Senha atual inválida!");
					}
					if (usuario.getSenha().isEmpty()) throw new RuntimeException("Tentativa de gravar usuário com \"senha\" em branco!");
					if (usuario.getSenha().length() < UsuarioModel.SENHA_FIELD_LEN_MIN) throw new RuntimeException("Tentativa de gravar usuário com tamanho de \"senha\" inválido!");
				}
				
			}
			
			if (usuario.getNome().isEmpty()) throw new RuntimeException("Tentativa de gravar usuário com \"nome\" em branco!");

			if (!usuario.getEmail().isValid()) {
				throw new RuntimeException(usuario.getEmail().toMessages());
			} else {
				if (isInc) {
					if (usuarioDAO.existByEmail(usuario.getEmail().getDescription())) throw new RuntimeException(String.format("O e-mail informado já existe: %s", usuario.getEmail().getDescription()));
				} else {
					if (usuarioDAO.existByEmailOderUser(usuario.getEmail().getDescription(),usuario.getId())) throw new RuntimeException(String.format("O e-mail informado já existe para outro usuário: %s", usuario.getEmail().getDescription()));
				}
			}
			
			if (!usuario.getSenha().isEmpty()) {
				usuario.setSenha(this.criptSenha(usuario, usuario.getSenha()));
			}
			
			if (isInc) {
				return usuarioDAO.inc(usuario);
			} else {
				//Este ajuste é para não alterar a senha
				if (usuario.getSenha().isEmpty()) {
					usuario.setSenha(null);
				}
				return usuarioDAO.alt(usuario);
			}
			
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());

		} catch (Exception e) {
			final String msg = String.format("Falha inesperada na %s do usuário: %s", tipo, e.getMessage());
			try {e.printStackTrace();} finally {}
			throw new Exception(msg);
		}
		
	}
	
	/**
	 * Lista todos os usuários em ordem alfabética
	 * 
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws Exception Erros na execução da pesquisa
	 */
	@Override
	public List<UsuarioDTO> listAllUsuarios(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			return usuarioDAO.listarTodosUsuarios();
		} catch (Exception e) {
			throw new Exception(String.format("Erro na pesquisa de usuarios: %s",e.getMessage()));
		}
		
	}

	@Override
	public UsuarioModel usuarioDel(Long id) throws Exception {
		UsuarioModel usuario = null;
		try {
			if (id == null || id == 0) throw new RuntimeException("O identificador do usuário não foi informado!");
			usuario = usuarioDAO.findById(id);
			
			if (usuario == null) throw new RuntimeException(String.format("Usuário não localizado: Id %d", id));
			
			usuarioDAO.del(id);
			
		} catch (Exception e) {
			final String msg = String.format("Falha inesperada ao excluir usuário: %s", e.getMessage());
			try {e.printStackTrace();} finally {}
			throw new Exception(msg);
		}
		return usuario;
	}

	@Override
	public UsuarioModel findById(Long id) throws SQLException {
		return usuarioDAO.findById(id);
	}

	@Override
	public String criptSenha(String nome, String email, String senha) throws NoSuchAlgorithmException {
		return Criptografia.pwToMD5(nome.trim() + email.trim() + senha.trim() );
	}

	@Override
	public String criptSenha(UsuarioModel usuario, String novaSenha) throws NoSuchAlgorithmException {
		return this.criptSenha( usuario.getNome() , usuario.getEmail().getDescription() , novaSenha );
	}

}
