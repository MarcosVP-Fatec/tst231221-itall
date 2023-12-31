package br.com.itall.controller.cad;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.controller.ControllerDefault;
import br.com.itall.model.dao.sta.EstadoDAO;
import br.com.itall.model.dto.cad.ClienteDTO;
import br.com.itall.model.entity.cad.ClienteModel;
import br.com.itall.service.cad.ClienteService;
import br.com.itall.service.implement.cad.ClienteServiceImpl1;
import br.com.itall.tool.Data;
import br.com.itall.tool.Texto;

/**
 * SERVLET de Clientes
 * 
 * @author MarcosVP
 * @since 04/01/2024
 * @version 1.01.0
 */
@WebServlet("/cliente")
public class ClienteController extends HttpServlet {

	private static final long serialVersionUID = -322209684253564827L;

	/** Dependência de Serviços de Cliente utilizada nesta classe */
	private ClienteService serv;

	/** Método de injeção de dependências desta classe. */
	public void init() {
		this.serv = new ClienteServiceImpl1();
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
	 * @param request
	 */
	private void reqAttibutes(HttpServletRequest request, ClienteDTO dto) {

		final boolean isInclu = dto == null || dto.getId() == null || dto.getId() == 0;

		request.setAttribute("acao_tela" 			, isInclu || dto == null ? "inc" : "alt");
		request.setAttribute("fieldId"   			, isInclu || dto == null ? "" : dto.getId());
		request.setAttribute("fieldNome" 			, isInclu || dto == null ? "" : dto.getNome());
		request.setAttribute("fieldSobrenome" 		, isInclu || dto == null ? "" : dto.getSobrenome());
		request.setAttribute("fieldSexo" 			, isInclu || dto == null ? "" : dto.getSexo());
		request.setAttribute("fieldDataNascimento" 	, isInclu || dto == null ? "" : Data.convertDateToStringHtml(dto.getDataNascimento()));
		request.setAttribute("fieldNacionalidade" 	, isInclu || dto == null ? "" : dto.getNacionalidade());
		request.setAttribute("fieldEmail"			, isInclu || dto == null ? "" : dto.getEmail());
		request.setAttribute("fieldEndereco" 		, isInclu || dto == null ? "" : dto.getEndereco());
		request.setAttribute("fieldCidade" 			, isInclu || dto == null ? "" : dto.getCidade());
		request.setAttribute("fieldEstado" 			, isInclu || dto == null ? "" : dto.getEstado());
		request.setAttribute("fieldTelefone" 		, isInclu || dto == null ? "" : dto.getTelefone());

		request.setAttribute("fieldSizes", new ClienteModel().getFieldSizes());
		
		request.setAttribute("estados", EstadoDAO.findAll() );
		
	}
	
	/**
	 * Abre a página de Cadastro de Cliente (Inclusão)
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 */
	public void abrePaginaInclusao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.reqAttibutes(request, null);
		request.getRequestDispatcher("jsp/cad/cliente-cadastro.jsp")
				.forward(request, response);
	}

	/**
	 * Abre a página de Cadastro de Cliente para Alteração
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
		ClienteDTO dto = (id == null || id == 0) ? new ClienteDTO() : new ClienteDTO(serv.findById(id));
		this.reqAttibutes(request, dto);
		request.getRequestDispatcher("jsp/cad/cliente-cadastro.jsp")
				.forward(request, response);

	}

	/**
	 * Insere um novo cliente.
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
			serv.clienteAltInc( this.modelFromRequest(request), true);
			request.setAttribute("msg_sucesso", "Novo cliente cadastrado com sucesso!");
		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
		}
		this.abrePaginaInclusao(request, response);

	}

	/**
	 * Altera dados de um novo cliente
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 * @throws SQLException Lançada para fornecer informações sobre erros de acesso ao banco de dados (SQL).
	 */
	public void sqlUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {
			serv.clienteAltInc( this.modelFromRequest(request), false);
			request.setAttribute("msg_sucesso", "Cliente alterado com sucesso!");
		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
		}
		this.abrePaginaAlteracao(request, response);

	}

	/**
	 * Abre a página de Lista de Cliente.
	 * 
	 * @param request  (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws IOException Lançada quando ocorre algum tipo de exceção de I/O produzida por falhas ou interrupções.
	 * @throws ServletException Lançada por um <i><b>Servlet</b></i> para problemas específicos no processamento de solicitações HTTP ou outras dificuldades.
	 */
	public void abrePaginaLista(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ClienteDTO> lista = new ArrayList<ClienteDTO>();

		try {

			lista = serv.listAllClientes(request, response);
			if (lista.size() == 0)
				request.setAttribute("msg_alerta", "Não há clientes cadastrados!");
			request.setAttribute("lista_principal", lista);

		} catch (Exception e) {

			Texto.logConsole(e);
			request.setAttribute("msg_erro", e.getMessage());

		}

		request.getRequestDispatcher("jsp/cad/cliente-lista.jsp")
				.forward(request, response);

	}

	/**
	 * Exclui um novo cliente pelo seu id
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
			ClienteModel cliente = serv.clienteDel(id);
			request.setAttribute("msg_sucesso", String.format("Sucesso na exclusão do cliente: %s", (cliente==null?String.valueOf(id):cliente.getNome()) ));

		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
		}

		abrePaginaLista(request, response);

	}
	
	/**
	 * Monta um objeto ClienteModel a partir do HttpServletRequest
	 * 
	 * @param request (HttpServletRequest)
	 * @return ClienteModel (nullable)
	 */
	private ClienteModel modelFromRequest(HttpServletRequest request) {
		
		try {
			
			String idString = request.getParameter("id");
			Long id = (idString==null||idString.equals("")?null:Long.valueOf(idString));
			
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			String sexo = request.getParameter("sexo");
			String sData = request.getParameter("dataNascimento");
			LocalDate dataNascimento = (sData==null||sData.equals("")?null:Data.convertStringToLocalDate(sData));
			String nacionalidade = request.getParameter("nacionalidade");
			String email = request.getParameter("email");
			String endereco = request.getParameter("endereco");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String telefone = request.getParameter("telefone");
			
			return new ClienteModel(id, nome, sobrenome, sexo, dataNascimento, nacionalidade, email, endereco, cidade, estado, telefone);
			
		} catch (Exception e) {
			Texto.logConsoleErro(String.format("Falha ao instanciar a classe <%s>: %s","ClienteModel",e.getMessage()));
			return null;
		}
		
	}

}

