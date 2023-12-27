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

<!-- Mensagens de ERRO -->
<div class="alert alert-danger alert-dismissible fade show local_msg animacao-surgir" 
    id="msgErro" role="alert" style="display: none; margin-top: 10px;">
	<strong><span><c:out value="${msg_erro}" /></span></strong>
	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<!-- Mensagens de ALERTA -->
<div class="alert alert-warning alert-dismissible fade show local_msg animacao-surgir"
	id="msgAlerta" role="alert" style="display: none; margin-top: 10px;">
	<strong><span><c:out value="${msg_alerta}" /></span></strong>
	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<!-- Mensagens de SUCESSO -->
<div class="alert alert-success alert-dismissible fade show local_msg animacao-surgir"
	id="msgSucesso"	role="alert" style="display: none; margin-top: 10px;">
	<strong><span><c:out value="${msg_sucesso}" /></span></strong>
	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
 
<!-- Script que exibe as mensagens na abertura da tela -->
<c:if test="${msg_erro != null}">  
	<script>$("#msgErro").show();</script>
</c:if>
<c:if test="${msg_alerta != null}">  
	<script>$("#msgAlerta").show();</script>
</c:if>
<c:if test="${msg_sucesso != null}">  
	<script>$("#msgSucesso").show();</script>
</c:if>

