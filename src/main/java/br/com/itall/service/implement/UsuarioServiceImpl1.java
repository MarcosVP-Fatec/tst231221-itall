/**
 * Pacote de implementações dos serviços
 */
package br.com.itall.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.model.dao.UsuarioDAO;
import br.com.itall.model.dto.UsuarioDTO;
import br.com.itall.model.entity.cad.UsuarioModel;
import br.com.itall.service.UsuarioService;
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
			String senha = request.getParameter("senha");
			
			String sData = request.getParameter("dataCriacao");
			
			LocalDateTime dataCriacao = (sData==null||sData.equals("")?null:Data.toDateTime(sData));
			
			return new UsuarioModel(id, nome, email, senha, dataCriacao);
			
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public UsuarioModel usuarioInc(UsuarioModel usuario) throws Exception {
		
		try {

			if (usuario == null) throw new RuntimeException("Nenhum usuário foi informado para esta inclusão!");
			if (usuario.getId() != null) throw new RuntimeException(String.format("Tentativa de incluir usuário com identificador informado!: %s", usuario.getId()));
			if (usuario.getNome().isEmpty()) throw new RuntimeException("Tentativa de gravar usuário com \"nome\" em branco!");
			
			if (!usuario.getEmail().isValid()) {
				throw new RuntimeException(usuario.getEmail().toMessages());
			} else {
				if (usuarioDAO.existByEmail(usuario.getEmail().getDescription())) throw new RuntimeException(String.format("O e-mail informado já existe: %s", usuario.getEmail().getDescription()));
			}
			
			if (usuario.getSenha().isEmpty()) throw new RuntimeException("Tentativa de gravar usuário com \"senha\" em branco!");
			if (usuario.getSenha().length() < UsuarioModel.SENHA_FIELD_LEN_MIN) throw new RuntimeException("Tentativa de gravar usuário com tamanho de \"senha\" inválido!");
			
			return usuarioDAO.inc(usuario);
			
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());

		} catch (Exception e) {
			final String msg = String.format("Falha inesperada ao incluir usuário: %s", e.getMessage());
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

}
