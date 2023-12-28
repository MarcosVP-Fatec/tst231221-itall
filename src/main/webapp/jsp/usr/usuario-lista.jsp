<%-- Página de Lista de Usuarios 
@author MarcosVP
@since 26/12/2023
@version 1.01.0
--%>
<jsp:include page="/jsp/include/libs.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="/jsp/include/meta.jsp" />
<meta name="keywords" content="usuários,lista,pesquisa">

<title>Lista de Usuários</title>

<jsp:include page="/jsp/include/links.jsp" />

</head>
<body>

	<jsp:include page="../include/navbar.jsp" />

	<div class="container">
		<div class="row">
			<div class="col">
				<h2>Lista de Usuários</h2>
				<jsp:include page="/jsp/include/mensagens.jsp" />
				<table class="table table-striped">
					<thead>
						<tr>
							<th style="text-align: right;">Id</th>
							<th>Nome</th>
							<th>E-mail</th>
							<th>Data Inclusão</th>
							<th>Edição</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="usu" items="${lista_usu}">
							<tr>
								<td style="text-align: right;"><c:out value="${usu.id}" /></td>
								<td><c:out value="${usu.nome}" /></td>
								<td><c:out value="${usu.email}" /></td>
								<td><fmt:formatDate value="${usu.dataCriacao}" type="date" pattern="dd/MM/yyyy hh:mm" /></td>
								<td> 																			
									<a class="btn btn-outline-success btn-sm"
									href="${pageContext.request.contextPath}/usr?opc=usuarioalterar&id=<c:out value="${usu.id}"/>">alterar</a>
									<a class="btn btn-outline-danger btn-sm"
									onclick="return confirm('Confirma a exclusão do usuário <c:out value="${usu.nome}"/> ?');"
									href="${pageContext.request.contextPath}/usr?opc=usuario_delete&id=<c:out value="${usu.id}"/>">excluir</a>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div
					class="alert alert-danger text-center mx-auto"
					role="alert" style="display: block; margin-top: 10px;">
					*   *   *   Fim da listagem   *   *   *
				</div>

			</div>
		</div>
	</div>

</body>
</html>