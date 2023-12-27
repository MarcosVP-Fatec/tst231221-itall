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
function msgShowErro(msg) { return msgShow("msgErro", msg); }

/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem de ALERTA na tela
 * @param msg {string} 
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShowAlerta(msg) { return msgShow("msgAlerta", msg); }

/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem de SUCESSO na tela
 * @param msg {string} 
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShowSucesso(msg) { return msgShow("msgSucesso", msg); }

/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem conforme o id do componente
 * @param msg {string} 
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShow(idName,msg) {
	try {
		msgHideAll();
		const id = $("#"+idName);
		if (id.length > 0){
			id.find('span').html(msg);
			id.show();	
		} else {
			alert(msg);
		}
	} finally {
		alert(msg);	
	} 
}
	

/****************************************************************************************
 * @author MarcosVP
 * @description Função que retorna o path de contexto da aplicação
 */
function getContext() {
    return '/' + window.location.pathname.split('/')[1]; // Adicionamos a barra inicial para formar o contexto completo
}

/****************************************************************************************
 * @author MarcosVP
 * @description Função que redireciona para a página Home
 */
function gotoHome(){ 
	window.location.href = getContext();	
}




