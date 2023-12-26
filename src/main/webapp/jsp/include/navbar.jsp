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
				<li class="nav-item"><a class="nav-link active" aria-current="page" 
				    href="${pageContext.request.contextPath}/">
					Home</a></li>

				<!-- Usuário -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Usuários</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="${pageContext.request.contextPath}/usr?opc=usuarionovo">Novo</a></li>
						<li><a class="dropdown-item" href="#">Lista</a></li>
<!-- 						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="#">Something else here</a></li>
 -->					</ul></li>
 
				<!-- Ajuda -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Ajuda</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" target="_blank" href="${pageContext.request.contextPath}/guide/javadoc/index.html">Manual Técnico</a></li>
					</ul></li>


<!-- 				<li class="nav-item"><a class="nav-link disabled">Disabled</a>
				</li>
 -->
			</ul>
			<form class="d-flex">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</div>
</nav>