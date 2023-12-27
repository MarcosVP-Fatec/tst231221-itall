<%-- Página de Usuário Novo (Cadastro de Usuário) 
@author MarcosVP
@since 24/12/2023
@version 1.01.0
--%>
<jsp:include page="/jsp/include/libs.jsp"/>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="/jsp/include/meta.jsp"/>
<meta name="keywords" content="usuário novo, cadastre-se">

<title>Novo Usuário</title>

<jsp:include page="/jsp/include/links.jsp"/>

	<script>
		function preValid() {
			msgHideAll();
			if (document.getElementById("fieldSenha1").value !== document.getElementById("fieldSenha2").value) {
				msgShowErro("As senhas digitadas estão diferentes!");
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
				
				<h2>Cadastro de Usuário</h2>
				
				<form onsubmit="return preValid();"
				      action="${pageContext.request.contextPath}/usr?opc=usuarioinserir"
				      method="post">
				      
					<!-- NOME -->
					<div class="mb-2">
						<label for="fieldNome" class="form-label">Nome Completo</label> <input
							type="text" class="form-control" name="nome" maxlength="80"
							required id="fieldNome"
							placeholder=".....Informe o seu nome completo...">
					</div>

					<!-- EMAIL -->
					<div class="mb-2">
						<label for="fieldEmail" class="form-label">Endereço de
							E-mail</label> <input type="email" class="form-control" name="email"
							maxlength="255" required id="fieldEmail"
							placeholder=".....Fique tranquilo! Seu e-mail não será divulgado.">
					</div>

					<!-- SENHA -->
					<div class="mb-1">
						<label for="fieldSenha1" class="form-label">Senha</label> <input
							type="password" class="form-control" name="senha" maxlength="80"
							required id="fieldSenha1"
							placeholder=".....Digite uma senha de acesso.">
					</div>

					<!-- SENHA REPETE -->
					<div class="mb-2">
						<label for="fieldSenha2" class="form-label">Repita a Senha</label>
						<input type="password" class="form-control" maxlength="80"
							required id="fieldSenha2"
							placeholder=".....Repita a senha digitada anteriormente.">
					</div>

					<button type="submit" class="btn btn-primary" aria-pressed="true">Incluir</button>
					<a href="#" class="btn btn-secondary active" role="button" onclick="gotoHome();" aria-pressed="false">Voltar</a>
										
				</form>
				
			</div>
			
			<jsp:include page="/jsp/include/mensagens.jsp" />
			
		</div>
	</div>

</body>
</html>