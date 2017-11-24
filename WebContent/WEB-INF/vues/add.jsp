<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>

	<head>
		<title>BIBLIOTHEQUE - Personnes</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	</head>
	
	<body>
	<!-- on ajoute le menu du site -->
		<%@include file="menu.jsp"%>
	
		<div class="container-fluid">
			<h2>Ajout Personne</h2>
		
			<form method="post" action="<c:url value="/validate"/>">
				<!-- le fait de mettre "post" a la place de "get" evite d'avoir les valeurs dans l'adresse de la page -->
				<h3>Prenom :</h3>
				<INPUT name="prenom" ></INPUT> <br>
				<h3>Nom :</h3>
				<INPUT name="nom" ></INPUT> <br />
				<h3>Date de naissance : </h3>
				<INPUT name="dateDeNaissance" ></INPUT> <br />
				
				<input type="submit" value="Valider" />
		
			</form>
		
			<br />
			<a href="<c:url value="/listePersonnes"/>">Retour</a>
		</div>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
	 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	 	<script src="bootstrap/js/bootstrap.min.js" ></script>
	</body>

</html>


