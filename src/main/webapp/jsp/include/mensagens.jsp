<%-- Compomente usado para exibir mensagens nas páginas 
@author MarcosVP
@since 24/12/2023
@version 1.01.0

Coloque no local onde quer que apareça.
Este include habilita três tipos de mensagens: 
Alerta : Fundo Laranja
Erro   : Funco Vermelho
Sucesso: Fundo Verde
 
<jsp:include page="/jsp/include/links.jsp"/>

--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${msg_erro != null}">

	<br />
	<div class="alert alert-danger alert-dismissible fade show"
		id="msgErro"
		role="alert">
		<strong><span><c:out value="${msg_erro}" /></span></strong>
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>

</c:if>

<c:if test="${msg_alerta != null}">

	<br />
	<div class="alert alert-warning alert-dismissible fade show"
		id="msgAlerta"
		role="alert">
		<strong><span><c:out value="${msg_alerta}" /></span></strong>
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>

</c:if>

<c:if test="${msg_sucesso != null}">

	<br />
	<div class="alert alert-success alert-dismissible fade show"
		id="msgSucesso"
		role="alert">
		<strong><span><c:out value="${msg_sucesso}" /></span></strong>
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>

</c:if>
