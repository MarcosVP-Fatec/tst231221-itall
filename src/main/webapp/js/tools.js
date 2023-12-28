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
	} catch (ignorar) {
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

/****************************************************************************************
 * @author MarcosVP
 * @example confirma("Você gosta de amora",()=>{alert("sim, eu gosto")},()=>{alert("Não gosto")})
 * @description Função que abre um modal de confirmação
 */
function confirma( texto , f_sim, f_nao ){
	if (texto){
		texto = texto.trim();
		if (texto.indexOf('?') == -1) texto += ' ?';
		$('#modalConfirmaTexto').html(texto);

		if (typeof f_sim == 'function'){
			$('#modalConfirmaSim').click(f_sim);	
		} else {
			$('#modalConfirmaSim').click(new Function('return true;'));
			$('#modalConfirmaSim').attr('href', getContext() + f_sim);		
		}

		if (typeof f_nao == 'function') {
			$('#modalConfirmaNao').click(f_nao);
		} else {
			$('#modalConfirmaNao').click(new Function('return true;'));
			$('#modalConfirmaNao').attr('href', getContext() + f_nao);		
		}
		$("#modalConfirma").modal("show");
	}
}
