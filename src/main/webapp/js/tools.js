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
 * @param msg string Mensagem
 * @param nfecha int tempo em segundos para fechar (Default não fecha)  
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShowErro(msg,nfecha) { return msgShow("msgErro", msg, nfecha); }

/****************************************************************************************
 * @author MarcosVP
 * @description Função que abre um timeout para fechar a mensagem
 * @param id Id da Mensagem
 * @param nfechar Tempo em Segundos
 */
function fecharMsg(id,nfechar){
	if (nfechar && nfechar > 0){
		if (nfechar > 600) nfechar = 600; //5 minutos
		window.setTimeout(function() { $("#"+id).hide(); }, nfechar*1000);
	} 
} 
/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem de ALERTA na tela
 * @param msg string Mensagem
 * @param nfecha int tempo em segundos para fechar (Default não fecha)  
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShowAlerta(msg, nfecha) { return msgShow("msgAlerta", msg, nfecha); }

/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem de SUCESSO na tela
 * @param msg string Mensagem
 * @param nfecha int tempo em segundos para fechar (Default não fecha)  
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShowSucesso(msg, nfecha) { return msgShow("msgSucesso", msg, nfecha); } 

/****************************************************************************************
 * @author MarcosVP
 * @description Função que exibe uma mensagem conforme o id do componente
 * @param idName string id do componente da mensagem
 * @param msg string Mensagem que será exibida
 * @param nfechar int Tempo em segundos para fechar a mensagem (Default não fecha)
 * @requires jsp:include page="../include/links.jsp"  
 */
function msgShow(idName,msg,nfechar) {
	try {
		msgHideAll();
		const id = $("#"+idName);
		if (id.length > 0){
			fecharMsg(idName,nfechar)		
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
