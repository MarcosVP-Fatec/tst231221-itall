package br.com.itall.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.model.UsuarioModel;
import br.com.itall.model.dao.UsuarioDAO;
import br.com.itall.tool.Data;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/public")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsuarioDAO usuarioDAO;
	
    public HomeController() { super(); }
    
    public void init() {
    	this.usuarioDAO = new UsuarioDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		runReq(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		runReq(request, response);
	}
	
	private void runReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			switch (request.getParameter("opc")) {
			case "usuarionovo":
				usuarioNovo(request,response);
				break;
			case "usuarioinserir":
				usuarioInc(request,response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	/**
	 * @apiNote Abre a página de Cadastro de Usuário
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException
	 */
	private void usuarioNovo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("jsp/public/usuario-novo.jsp")
		       .forward(request, response);
	}
			
	/**
	 * @apiNote Abre a página de Cadastro de Usuário
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void usuarioInc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		UsuarioModel usuario = new UsuarioModel(null, nome, email, senha, null);
		
		usuarioDAO.inc(usuario);
		
		request.setAttribute("msg_sucesso", "Usuário cadastrado com sucesso!");
		request.getRequestDispatcher("jsp/public/usuario-novo.jsp")
	           .forward(request, response);

	}

}
