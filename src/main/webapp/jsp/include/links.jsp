<%-- Elementos que são inicializados por padrão nas páginas 
@author MarcosVP
@since 24/12/2023
@version 1.01.0
Utilize sogo abaixo da tab <title></title>

<jsp:include page="/jsp/include/links.jsp"/>

--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<link href="${pageContext.request.contextPath}/libs/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tools.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/valida_dados.js"></script>

<!-- Animação para as mensagens surgirem da esquerda para a direita --> 
<style>
   .local_msg.animacao-surgir { animation: animacaoSurgir 0.5s ease-in-out; }

   @keyframes animacaoSurgir {
       from { transform: translateX(-100%); opacity: 0; }
       to   { transform: translateX(0);     opacity: 1; }
   }
</style>
<%-- Fim da dfinição padrão --%>

	


