package br.com.itall.controller.security;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.model.dto.cad.UsuarioDTO;
import br.com.itall.model.entity.cad.UsuarioModel;
import br.com.itall.service.UsuarioService;
import br.com.itall.service.implement.UsuarioServiceImpl1;
import br.com.itall.tool.Texto;

/**
 * SERVLET de Usuários
 * 
 * @author MarcosVP
 * @since 25/12/2023
 * @version 1.01.0
 */
@WebServlet("/usr")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** Dependência de Serviços de Usuário utilizada nesta classe */
	private UsuarioService usuarioService;

	/** Método de injeção de dependências desta classe. */
	public void init() {
		this.usuarioService = new UsuarioServiceImpl1();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		runReq(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		runReq(request, response);
	}

	private void runReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			switch (request.getParameter("opc")) {
				case "usuarionovo":
					abrePaginaInclusaoUsuario(request, response);
					break;

				case "usuario_insert":
					usuarioSqlInsert(request, response);
					break;

				case "usuario_update":
					usuarioSqlUpdate(request, response);
					break;

				case "usuarioslistar":
					abrePaginaListaDeUsuarios(request, response);
					break;

				case "usuario_delete":
					usuarioDel(request, response);
					break;

				case "usuarioalterar":
					abrePaginaAlteracaoUsuario(request, response);
					break;
			}
		} catch (Exception e) {
			Texto.logConsole(e);
			throw new ServletException(e);
		}
	}

	/**
	 * @apiNote Abre a página de Cadastro de Usuário
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException
	 */
	private void abrePaginaInclusaoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.reqAttibutes(request, null);
		request.getRequestDispatcher("jsp/usr/usuario-cadastro.jsp")
				.forward(request, response);
	}

	/**
	 * @apiNote Abre a página de Cadastro de Usuário para Alteração
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException      Tratamento de erros de IO
	 * @throws SQLException     Tratamento de erros em execução SQL
	 */
	private void abrePaginaAlteracaoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		Long id = Long.valueOf(request.getParameter("id"));
		UsuarioDTO dto = (id == null || id == 0) ? new UsuarioDTO() : new UsuarioDTO(usuarioService.findById(id));
		this.reqAttibutes(request, dto);
		request.getRequestDispatcher("jsp/usr/usuario-cadastro.jsp")
				.forward(request, response);

	}

	/**
	 * @apiNote Insere um novo usuário
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void usuarioSqlInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {
			usuarioService.usuarioAltInc(request, true);
			request.setAttribute("msg_sucesso", "Novo usuário cadastrado com sucesso!");
		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
		}
		this.abrePaginaInclusaoUsuario(request, response);

	}

	/**
	 * @apiNote Altera dados de um novo usuário
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void usuarioSqlUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {
			usuarioService.usuarioAltInc(request, false);
			request.setAttribute("msg_sucesso", "Usuário alterado com sucesso!");
		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
		}
		this.abrePaginaAlteracaoUsuario(request, response);

	}

	/**
	 * Método que abre a página de Lista de Usuários
	 * 
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws IOException      Tratamento de erros de disco
	 * @throws ServletException Tratamento de Erros de Servlet
	 */
	private void abrePaginaListaDeUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

		try {

			lista = usuarioService.listAllUsuarios(request, response);
			if (lista.size() == 0)
				request.setAttribute("msg_alerta", "Não há usuários cadastrados!");
			request.setAttribute("lista_usu", lista);

		} catch (Exception e) {

			Texto.logConsole(e);
			request.setAttribute("msg_erro", e.getMessage());

		}

		request.getRequestDispatcher("jsp/usr/usuario-lista.jsp")
				.forward(request, response);

	}

	/**
	 * @apiNote Exclui um novo usuário pelo seu id
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void usuarioDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {

			Long id = Long.parseLong(request.getParameter("id"));
			UsuarioModel usuario = usuarioService.usuarioDel(id);
			request.setAttribute("msg_sucesso", String.format("Sucesso na exclusão do usuário: %s", usuario.getNome()));

		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());

		}

		abrePaginaListaDeUsuarios(request, response);

	}

	/**
	 * Função que gera os request.setAttribute dos campos da tela.<br>
	 * Exemplo: request.setAttribute("fieldNome","Marcos");
	 * 
	 * @param request
	 */
	private void reqAttibutes(HttpServletRequest request, UsuarioDTO dto) {

		final boolean isInclu = dto == null || dto.getId() == null || dto.getId() == 0;

		request.setAttribute("acao_tela", isInclu || dto == null ? "inc" : "alt");
		request.setAttribute("fieldId", isInclu || dto == null ? "" : dto.getId());
		request.setAttribute("fieldNome", isInclu || dto == null ? "" : dto.getNome());
		request.setAttribute("fieldEmail", isInclu || dto == null ? "" : dto.getEmail());

	}

}
