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

import br.com.itall.model.dto.UsuarioDTO;
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
				
			case "listarusuarios":
				listAllUsuarios(request, response);
				break;	

			case "removerusuario":
				usuarioDel(request, response);
				break;	
			}
		} catch (Exception e) {
			Texto.logConsole(e);
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

	/**
	 * Método de controle da visão de lista de usuário
	 * 
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws IOException Tratamento de erros de disco
	 * @throws ServletException Tratamento de Erros de Servlet
	 */
	private void listAllUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		
		try {
			
			lista = usuarioService.listAllUsuarios(request,response);
			if (lista.size() == 0) request.setAttribute("msg_alerta", "Não há usuários cadastrados!");
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
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void usuarioDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	
		try {
	
			Long id = Long.parseLong(request.getParameter("id"));
			UsuarioModel usuario = usuarioService.usuarioDel(id);
			request.setAttribute("msg_sucesso", String.format("Sucesso na exclusão do usuário: %s", usuario.getNome()));
			
		} catch (Exception e) {
			request.setAttribute("msg_erro", e.getMessage());
			
		}
		
		listAllUsuarios(request, response);
		
	}
}
