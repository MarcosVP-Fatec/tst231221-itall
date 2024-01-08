package br.com.itall.controller.security;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.controller.ControllerDefault;
import br.com.itall.model.dto.cad.UsuarioDTO;
import br.com.itall.model.entity.cad.UsuarioModel;
import br.com.itall.service.cad.UsuarioService;
import br.com.itall.service.implement.cad.UsuarioServiceImpl1;
import br.com.itall.tool.Data;
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
	private static final long serialVersionUID = 7569964284537202039L;
	
	/** Dependência de Serviços de Usuário utilizada nesta classe */
	private UsuarioService serv;

	/** Método de injeção de dependências desta classe. */
	public void init() {
		this.serv = new UsuarioServiceImpl1();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControllerDefault.runRequisition(this, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControllerDefault.runRequisition(this, request, response);
	}

	/**
	 * Função que gera os request.setAttribute dos campos da tela.<br>
	 * Exemplo: request.setAttribute("fieldNome","Marcos");
	 * 
	 * @param request (HttpServletRequest) 
	 * @param dto (UsuarioDTO)
	 */
	private void reqAttibutes(HttpServletRequest request, UsuarioDTO dto) {

		final boolean isInclu = dto == null || dto.getId() == null || dto.getId() == 0;

		request.setAttribute("acao_tela" , isInclu || dto == null ? "inc" : "alt"          );
		request.setAttribute("fieldId"   , isInclu || dto == null ? "" 	  : dto.getId()    );
		request.setAttribute("fieldNome" , isInclu || dto == null ? ""    : dto.getNome()  );
		request.setAttribute("fieldEmail", isInclu || dto == null ? ""    : dto.getEmail() );

	}

	/**
	 * Abre a página de Cadastro de Usuário (Inclusão)
	 * 
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 */
	public void abrePaginaInclusao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.reqAttibutes(request, null);
		request.getRequestDispatcher("jsp/usr/usuario-cadastro.jsp")
				.forward(request, response);
	}

	/**
	 * Abre a página de Cadastro de Usuário para Alteração
	 * 
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public void abrePaginaAlteracao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		Long id = Long.valueOf(request.getParameter("id"));
		UsuarioDTO dto = (id == null || id == 0) ? new UsuarioDTO() : new UsuarioDTO(serv.findById(id));
		this.reqAttibutes(request, dto);
		request.getRequestDispatcher("jsp/usr/usuario-cadastro.jsp")
				.forward(request, response);

	}

	/**
	 * Insere um novo usuário.
	 * 
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public void sqlInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {
			serv.usuarioAltInc( this.modelFromRequest(request), true);
			request.setAttribute("msg_sucesso", "Novo usuário cadastrado com sucesso!");
		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
		}
		this.abrePaginaInclusao(request, response);

	}

	/**
	 * Altera dados de um usuário.
	 * 
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public void sqlUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {
			serv.usuarioAltInc( this.modelFromRequest(request), false);
			request.setAttribute("msg_sucesso", "Usuário alterado com sucesso!");
		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
		}
		this.abrePaginaAlteracao(request, response);

	}

	/**
	 * Abre a página de Lista de Usuários.
	 * 
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 */
	public void abrePaginaLista(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

		try {

			lista = serv.listAllUsuarios();
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
	 * Exclui um novo usuário pelo seu id
	 * 
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public void sqlDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {

			Long id = Long.parseLong(request.getParameter("id"));
			UsuarioModel usuario = serv.usuarioDel(id);
			request.setAttribute("msg_sucesso", String.format("Sucesso na exclusão do usuário: %s", usuario.getNome()));

		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());

		}

		abrePaginaLista(request, response);

	}

	/**
	 * Monta um objeto UsuarioModel a partir do HttpServletRequest
	 * 
	 * @param request (HttpServletRequest)
	 * @return UsuarioModel (nullable)
	 */
	private UsuarioModel modelFromRequest(HttpServletRequest request) {
		
		try {
			
			String idString = request.getParameter("id");
			Long id = (idString==null||idString.equals("")?null:Long.valueOf(idString));
			
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			Boolean isMudarSenha = request.getParameter("isMudarSenha") != null; 
			String senha = request.getParameter("senha");
			if (senha == null) senha = "";
			
			String sData = request.getParameter("dataCriacao");
			LocalDateTime dataCriacao = (sData==null||sData.equals("")?null:Data.convertStringToLocalDateTime(sData));
			
			return new UsuarioModel(id, nome, email, isMudarSenha, senha, dataCriacao)
					               .setSenhaAnterior(request.getParameter("senhaAnterior"));
			
		} catch (Exception e) {
			Texto.logConsoleErro(String.format("Falha ao instanciar a classe <%s>: %s","UsuarioModel",e.getMessage()));
			return null;
		}
		
	}
	
}
