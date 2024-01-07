<%-- Página de Usuário Novo (Cadastro de Usuário) 
@author MarcosVP
@since 24/12/2023
@version 1.01.0
--%>
<jsp:include page="/jsp/include/libs.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="/jsp/include/meta.jsp" />
<meta name="keywords" content="usuário novo, cadastre-se">

<title>Novo Usuário</title>

<jsp:include page="/jsp/include/links.jsp" />

<script>
	// Habilita ou desabilita a digitação de e-mail conforme checkbox
	function seQueroMudarSenha() {
		const isQueroMudar = document.getElementById('fieldQueroAlterarSenha').checked;
		const idSenha1 = document.getElementById('fieldSenha1');
		const idSenha2 = document.getElementById('fieldSenha2');
		const idSenha3 = document.getElementById('fieldSenha3');
		idSenha1.disabled = !isQueroMudar;
		idSenha2.disabled = !isQueroMudar;
		idSenha3.disabled = !isQueroMudar;
		idSenha1.value = "";
		idSenha2.value = "";
		idSenha3.value = "";
	}

	function preValid() {
		msgHideAll();
		if (document.getElementById("fieldSenha1").value !== document
				.getElementById("fieldSenha2").value) {
			msgShowErro("As senhas digitadas estão diferentes!",3);
			return false;
		}
		return true;
	}
</script>

</head>
<body>

	<jsp:include page="../include/navbar.jsp" />

	<div class="container">
		<div class="row">
			<div class="col">

				<h2>
					Cadastro de Usuário
					<c:if test="${acao_tela == 'inc'}"> (Inclusão)</c:if>
					<c:if test="${acao_tela == 'alt'}"> (Alteração)</c:if>
				</h2>

				<form onsubmit="return preValid();"
					<c:if test="${acao_tela eq 'inc'}">
				        action="${pageContext.request.contextPath}/usr?opc=usuario_insert"
					</c:if>
					<c:if test="${acao_tela eq 'alt'}">
				        action="${pageContext.request.contextPath}/usr?opc=usuario_update&id=${fieldId}"
					</c:if>
					method="post">



					<!-- ID -->
					<div style="display: none;">
						<label for="fieldId" class="form-label">Identificador</label> <input
							type="text" class="form-control" name="id" value="${fieldId}"
							id="fieldId">
					</div>

					<!-- NOME -->
					<div class="mb-2">
						<label for="fieldNome" class="form-label">Nome Completo</label> <input
							type="text" class="form-control" name="nome" maxlength="80"
							required value="${fieldNome}" id="fieldNome"
							placeholder=".....Informe o seu nome completo...">
					</div>

					<!-- EMAIL -->
					<div class="mb-2">
						<label for="fieldEmail" class="form-label">Endereço de
							E-mail</label> <input type="email" class="form-control" name="email"
							maxlength="255" required value="${fieldEmail}" id="fieldEmail"
							placeholder=".....Fique tranquilo! Seu e-mail não será divulgado.">
					</div>

					<!-- QUERO ALTERAR SENHA -->
					<div class="mb-1"
						<c:if test="${acao_tela eq 'inc'}">style="display: none;"</c:if>>
						<input type="checkbox" class="form-check-input"
							id="fieldQueroAlterarSenha" name="isMudarSenha"
							<c:if test="${acao_tela eq 'alt'}">
							   onchange="seQueroMudarSenha();"
							</c:if>
							<c:if test="${acao_tela eq 'inc'}">
							   checked disabled readonly
							</c:if>>
						<label for="fieldQueroAlterarSenha" class="form-check-label">Quero
							alterar minha senha</label>
					</div>

					<!-- SENHA PARA CONFIRMAÇÃO -->
					<c:if test="${acao_tela eq 'alt'}">
					<div class="mb-1">
						<label for="fieldSenha3" class="form-label">Senha atual</label> <input type="password"
							class="form-control" name="senhaAnterior" maxlength="80" id="fieldSenha3"
							required
 					        disabled
					 	    placeholder=".....Digite a sua senha atual para confirmação.">
					</div>
					</c:if>

					<!-- SENHA -->
					<div class="mb-1">
						<label for="fieldSenha1" class="form-label">Senha<c:if
								test="${acao_tela == 'alt'}"> Nova</c:if></label> <input type="password"
							class="form-control" name="senha" maxlength="80" id="fieldSenha1"
							required
							<c:if test="${acao_tela eq 'inc'}">
							   placeholder=".....Digite uma senha de acesso."
							</c:if>
							<c:if test="${acao_tela eq 'alt'}">
							   disabled
							   placeholder=".....Digite uma NOVA senha de acesso. (Somente se desejar alterar)"
							</c:if>>
					</div>

					<!-- SENHA REPETE -->
					<div class="mb-2">
						<label for="fieldSenha2" class="form-label">Repita a Senha</label>
						<input type="password" class="form-control" maxlength="80"
							required id="fieldSenha2"
							<c:if test="${acao_tela eq 'alt'}">
							   disabled
							</c:if>
							placeholder=".....Repita a senha digitada anteriormente.">
					</div>

					<button type="submit" class="btn btn-primary" aria-pressed="true">
						<c:if test="${acao_tela == 'inc'}">Incluir</c:if>
						<c:if test="${acao_tela == 'alt'}">Alterar</c:if>
					</button>
					<a href="#" class="btn btn-secondary active" role="button"
						onclick="gotoHome();" aria-pressed="false">Voltar</a>

				</form>

			</div>

			<jsp:include page="/jsp/include/mensagens.jsp" />

		</div>
	</div>

</body>
</html>