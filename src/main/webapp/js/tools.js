/****************************************************************************************
 * @author MarcosVP
 * @description Função que fecha as mensagens exibidas em tela
 * @version 1.01.0
 */
function msgHideAll() {
	if ($('#msgErro')) $('#msgErro').hide();		
	if ($('#msgAlerta')) $('#msgAlerta').hide();		
	if ($('#msgSucesso')) $('#msgSucesso').hide();
}

/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem de ERRO na tela
 * @param msg {string} 
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShowErro(msg) {
	msgHideAll();
	const id = '#msgErro'
	if ($(id)){
		$(id).find('<span>').html(msg);
		$(id).show();	
	} else {
		alert(msg);
	}
}

/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem de ALERTA na tela
 * @param msg {string} 
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShowAlerta(msg) {
	msgHideAll();
	const id = '#msgAlerta'
	if ($(id)){
		$(id).find('<span>').html(msg);
		$(id).show();	
	} else {
		alert(msg);
	}
}

/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem de SUCESSO na tela
 * @param msg {string} 
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShowAlerta(msg) {
	msgHideAll();
	const id = '#msgSucesso'
	if ($(id)){
		$(id).find('<span>').html(msg);
		$(id).show();	
	} else {
		alert(msg);
	}
}
