<%-- Página de Cliente Novo (Cadastro de Cliente) 
@author MarcosVP
@since 04/01/2023
@version 1.01.0
--%>
<jsp:include page="/jsp/include/libs.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="/jsp/include/meta.jsp" />
<meta name="keywords" content="cliente novo">

<title>Novo Cliente</title>

<jsp:include page="/jsp/include/links.jsp" />

<script>

	function preValid() {
		msgHideAll();
		let retorno = true;
		if ( $("#fieldSexo").val() == null){
			msgShowErro("Faltou informar o sexo!",3);
			retorno = false;
		} 
		return retorno;
	}
</script>

<style>
  .custom-select-height {
    height: calc(1.5em + 0.75rem + 2px); /* Altura do SELECT */
  }
</style>

</head>
<body>

	<jsp:include page="../include/navbar.jsp" />

	<div class="container">
		<div class="row">
			<div class="col">

				<h2>
					Cadastro de Cliente
					<c:if test="${acao_tela == 'inc'}"> (Inclusão)</c:if>
					<c:if test="${acao_tela == 'alt'}"> (Alteração)</c:if>
				</h2>

				<form onsubmit="return preValid();"
					<c:if test="${acao_tela eq 'inc'}">
				        action="${pageContext.request.contextPath}/cliente?opc=insert"
					</c:if>
					<c:if test="${acao_tela eq 'alt'}">
				        action="${pageContext.request.contextPath}/cliente?opc=update&id=${fieldId}"
					</c:if>
					method="post">



					<!-- ID -->
					<div style="display: none;">
						<label for="fieldId" class="form-label">Identificador</label> <input
							type="text" class="form-control" name="id" value="${fieldId}"
							id="fieldId">
					</div>

					<div class="row">
  					<div class="col-md-4">
					<!-- NOME -->
					<div class="mb-1">
						<label for="fieldNome" class="form-label mb-1">Primeiro Nome</label> <input
							type="text" class="form-control" name="nome" maxlength=<c:out value="${fieldSizes.NOME_FIELD_LEN}"/>
							required value="${fieldNome}" id="fieldNome"
							placeholder=".....Informe o primeiro nome do cliente...">
					</div>
                    </div>
                    
                    <div class="col-md-8">
					<!-- SOBRENOME -->
					<div class="mb-1">
						<label for="fieldSobrenome" class="form-label mb-1">Sobrenomes</label> <input
							type="text" class="form-control" name="sobrenome" maxlength=<c:out value="${fieldSizes.SOBRENOME_FIELD_LEN}"/>
							required value="${fieldSobrenome}" id="fieldSobrenome"
							placeholder=".....Informe os sobrenomes do cliente...">
					</div>
					</div>
					</div>
					
					
					<div class="row">
  					<div class="col-md-4">
					<!-- SEXO -->
					<div class="mb-1">
						<label for="fieldSexo" class="form-label mb-1">Sexo</label> 
						<select class="form-select custom-select-height" name="sexo" 
							required id="fieldSexo">
							<option disabled selected>.....Selecione o sexo do cliente.....</option>
							<option value="F">Feminino</option>
							<option value="M">Masculino</option>
						</select>
					</div>
                    </div>
                    
					<div class="col-md-8">
					<!-- DATA_NASCIMENTO -->
					<div class="mb-1">
						<label for="fieldDataNascimento" class="form-label mb-1">Data de Nascimento</label> <input
							type="date" class="form-control" name="dataNascimento" required 
							value="${fieldDataNascimento}" id="fieldDataNascimento"
							placeholder=".....Informe a data de nascimento do cliente...">
					</div>
					</div>
					</div>
					
					<div class="row">
  					<div class="col-md-4">
					<!-- NACIONALIDADE -->
					<div class="mb-1">
						<label for="fieldNacionalidade" class="form-label mb-1">Nacionalidade</label> <input
							type="text" class="form-control" name="nacionalidade" maxlength=<c:out value="${fieldSizes.NACIONALIDADE_FIELD_LEN}"/>
							value="${fieldNacionalidade}" id="fieldNacionalidade"
							placeholder=".....Informe a nacionalidade do cliente...">
					</div>
					</div>
					
					<div class="col-md-8">
					<!-- EMAIL -->
					<div class="mb-1">
						<label for="fieldEmail" class="form-label mb-1">Endereço de
							E-mail</label> <input type="email" class="form-control" name="email" maxlength=<c:out value="${fieldSizes.EMAIL_FIELD_LEN}"/>
							value="${fieldEmail}" id="fieldEmail"
							placeholder=".....E-mail do cliente.....">
					</div>
					</div>
					</div>

					<!-- ENDERECO -->
					<div class="mb-1">
						<label for="fieldEndereco" class="form-label mb-1">Endereco</label> <input
							type="text" class="form-control" name="endereco" maxlength=<c:out value="${fieldSizes.ENDERECO_FIELD_LEN}"/>
							value="${fieldEndereco}" id="fieldEndereco"
							placeholder=".....Informe o endereço do cliente (Logradouro, nº, Bairro)...">
					</div>

					<div class="row">
  					<div class="col-md-4">
					<!-- CIDADE -->
					<div class="mb-1">
						<label for="fieldCidade" class="form-label mb-1">Cidade</label> <input
							type="text" class="form-control" name="cidade" maxlength=<c:out value="${fieldSizes.CIDADE_FIELD_LEN}"/>
							value="${fieldCidade}" id="fieldCidade"
							placeholder=".....Informe a cidade do endereço cliente...">
					</div>
					</div>

  					<div class="col-md-3">
					<!-- ESTADO -->
					<div class="mb-1">
						<label for="fieldEstado" class="form-label mb-1">Estado</label>
						
						<select class="form-select custom-select-height" name="estado" 
							id="fieldEstado">
							<c:if test="${empty fieldEstado}">
						        <option disabled selected>.....Selecione o estado do endereço do cliente.....</option>
						    </c:if>
						    <c:forEach var="estado" items="${estados}">
						        <option ${estado.uf eq fieldEstado ? "selected" : ""}
						          value="${estado.uf}">${estado.uf} - ${estado.nome}</option>
						    </c:forEach>
						</select>
					</div>

					</div>

  					<div class="col-md-5">
					<!-- TELEFONE -->
					<div class="mb-1">
						<label for="fieldTelefone" class="form-label mb-1">Telefone</label> <input
							type="text" class="form-control" name="telefone" maxlength=<c:out value="${fieldSizes.TELEFONE_FIELD_LEN}"/>
							value="${fieldTelefone}" id="fieldTelefone"
							placeholder=".....Informe o número do telefone do cliente...">
					</div>
					</div>
					</div>

					<button type="submit" class="btn btn-primary" aria-pressed="true">
						<c:if test="${acao_tela == 'inc'}">Incluir</c:if>
						<c:if test="${acao_tela == 'alt'}">Alterar</c:if>
					</button>
					<a class="btn btn-secondary active" role="button" aria-pressed="false"
						<c:if test="${acao_tela == 'inc'}">
							href="#" onclick="gotoHome();"
						</c:if>
						<c:if test="${acao_tela == 'alt'}">
							href="${pageContext.request.contextPath}/usr?opc=pagelist"
						</c:if>
					>Voltar</a>

				</form>

			</div>

			<jsp:include page="/jsp/include/mensagens.jsp" />

		</div>
	</div>

</body>
</html>