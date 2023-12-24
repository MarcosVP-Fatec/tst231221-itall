<jsp:include page="../include/content.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Novo Usuário</title>

<jsp:include page="../include/links.jsp" />

	<script>
		function preValid() {
			if (document.getElementById("fieldSenha1").value !== document.getElementById("fieldSenha2").value) {
				alert("As senhas digitados estão diferentes!");
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
				      action="${pageContext.request.contextPath}/public?opc=usuarioinserir"
				      method="post">
					<div class="mb-2">
						<label for="fieldNome" class="form-label">Nome Completo</label> <input
							type="text" class="form-control" name="nome" maxlength="80"
							required id="fieldNome"
							placeholder=".....Informe o seu nome completo...">
					</div>

					<div class="mb-2">
						<label for="fieldEmail" class="form-label">Endereço de
							E-mail</label> <input type="email" class="form-control" name="email"
							maxlength="255" required id="fieldEmail"
							placeholder=".....Fique tranquilo! Seu e-mail não será divulgado.">
					</div>

					<div class="mb-1">
						<label for="fieldSenha1" class="form-label">Senha</label> <input
							type="password" class="form-control" name="senha" maxlength="80"
							required id="fieldSenha1"
							placeholder=".....Digite uma senha de acesso.">
					</div>

					<div class="mb-2">
						<label for="fieldSenha2" class="form-label">Repita a Senha</label>
						<input type="password" class="form-control" maxlength="80"
							required id="fieldSenha2"
							placeholder=".....Repita a senha digitada anteriormente.">
					</div>

					<button type="submit" class="btn btn-primary">Incluir</button>
					
				</form>
				
				<jsp:include page="../include/mensagens.jsp" />
				
			</div>
		</div>
	</div>

</body>
</html>