package br.com.itall.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.itall.service.UsuarioService;
import br.com.itall.service.implement.UsuarioServiceImpl1;

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
    public void init(){
    	this.usuarioService = new UsuarioServiceImpl1();
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
	
		request.getRequestDispatcher("jsp/usr/usuario-novo.jsp")
		       .forward(request, response);
	}
			
	/**
	 * @apiNote Insere um novo usuário
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void usuarioInc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	
		try {
	
			usuarioService.usuarioInc(usuarioService.usuarioModelFromRequest(request));
			request.setAttribute("msg_sucesso", "Novo usuário cadastrado com sucesso!");
			
		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
			
		}
		
		request.getRequestDispatcher("jsp/usr/usuario-novo.jsp")
	           .forward(request, response);
		
	}

}
