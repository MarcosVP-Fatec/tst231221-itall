package br.com.itall.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Biblioteca de funções padrões dos Controllers
 * 
 * @author MarcosVP
 * @since 07/01/2024
 * @version 1.01.0
 */
public abstract class ControllerDefault {
	
	/**
	 * Padrão para tratamento das requisições CRUD<br>
	 * Abre as páginas e executa os comandos SQL.
	 *  
	 * @param controller (Class) Classe do <i><b>Controller</b></i> que chamou este método.
	 * @param request (HttpServletRequest)
	 * @param response (HttpServletResponse)
	 */
	public static void runRequisition( Object controller
			                         , HttpServletRequest request
			                         , HttpServletResponse response
			                         )  {
		String nomeDoMetodo = null;
		String opc = request.getParameter("opc");
		switch (opc) {
		case "pagenew"	: nomeDoMetodo = "abrePaginaInclusao"; 
			break;
		case "pagelist"	: nomeDoMetodo = "abrePaginaLista";
			break;
		case "pageupd"	: nomeDoMetodo = "abrePaginaAlteracao";
			break;
		case "insert"	: nomeDoMetodo = "sqlInsert";
			break;
		case "update"	: nomeDoMetodo = "sqlUpdate";
			break;
		case "delete"	: nomeDoMetodo = "sqlDelete";
			break;
		}
		
		if (nomeDoMetodo == null) throw new IllegalArgumentException(String.format("Não foi identificado o nome do método a ser executado para a opção \"%s\"", opc));
		
        try {
			
			controller.getClass()
			          .getMethod(nomeDoMetodo, HttpServletRequest.class, HttpServletResponse.class)
			          .invoke(controller, request, response);
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				e ) {

			e.printStackTrace();
		}
			
	}
	

}
