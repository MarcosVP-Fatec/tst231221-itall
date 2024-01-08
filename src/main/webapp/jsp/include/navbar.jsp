<%-- Compomente de menu do sistema 
@author MarcosVP
@since 24/12/2023
@version 1.01.0

Coloque na primeira linha após a tag <body>

<body>
	<jsp:include page="/jsp/include/navbar.jsp" />

--%>

<nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">

	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/"><b>IT.ALL</b></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">

				<!-- Home -->
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="${pageContext.request.contextPath}/">
						Home</a></li>

				<!-- Vendas -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdownVendas"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Vendas</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownVendas">
						<li><a class="dropdown-item"
							data-bs-toggle="tooltip" data-bs-placement="top" title="Em desenvolvimento"
							onclick='msgShowAlerta("Pedido Novo => em desenvolvimento",3)'>Pedido - Novo</a></li>
						<li><a class="dropdown-item"
							data-bs-toggle="tooltip" data-bs-placement="top" title="Em desenvolvimento"
							onclick='msgShowAlerta("Pedido Lista => em desenvolvimento",3)'>Pedido - Lista</a></li>
					</ul></li>

				<!-- Cadastros -->
				
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdownCadastros"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Cadastros</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownUsuarios">
					
						<!-- Clientes -->
						
						<li><a class="dropdown-item"
							data-bs-toggle="tooltip" data-bs-placement="top" title="Inclusão de clientes"
							href="${pageContext.request.contextPath}/cliente?opc=pagenew">Cliente - Novo</a></li>
						<li><a class="dropdown-item"
							data-bs-toggle="tooltip" data-bs-placement="top" title="Listagem de clientes"
							href="${pageContext.request.contextPath}/cliente?opc=pagelist">Cliente - Lista</a></li>
						
						<li class="dropdown-divider"></li>
						
						<li><a class="dropdown-item"
							data-bs-toggle="tooltip" data-bs-placement="top" title="Em desenvolvimento"
							onclick='msgShowAlerta("Produto Novo => em desenvolvimento",3)'>Produto - Novo</a></li>
						<li><a class="dropdown-item" 
							data-bs-toggle="tooltip" data-bs-placement="top" title="Em desenvolvimento"
							onclick='msgShowAlerta("Produto Lista => em desenvolvimento",3)'>Produto - Lista</a></li>
					</ul></li>

				<!-- Usuário -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsuarios"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Usuários</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownUsuarios">
						<li><a class="dropdown-item"
							data-bs-toggle="tooltip" data-bs-placement="top" title="Inclusão de usuários"
							href="${pageContext.request.contextPath}/usr?opc=pagenew">Novo</a></li>
						<li><a class="dropdown-item"
							data-bs-toggle="tooltip" data-bs-placement="top" title="Visualize todos os usuários para alterar ou excluir"
							href="${pageContext.request.contextPath}/usr?opc=pagelist">Lista</a></li>
					</ul></li>

				<!-- Ajuda -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdownAjuda"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Ajuda</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownAjuda">
						<li><a class="dropdown-item" target="_blank"
							href="${pageContext.request.contextPath}/guide/javadoc/index.html">Manual
								Técnico</a></li>
					</ul></li>


				<!-- 				<li class="nav-item"><a class="nav-link disabled">Disabled</a>
				</li>
 -->
			</ul>
		</div>
	</div>
</nav>