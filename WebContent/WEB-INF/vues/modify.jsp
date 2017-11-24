<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	
			<h2>Modification Personne</h2>
		
			<form method="post" action="<c:url value="/validateModify"/>">
			
				<h3>Id : </h3>
				<INPUT name="id" value="${personne.id}" readonly="readonly"></INPUT> <br>
				<h3>Prenom : </h3>
				<INPUT name="prenom" value="${personne.prenom}"></INPUT> <br>
				<h3>Nom : </h3>
				<INPUT name="nom" value="${personne.nom}"></INPUT> <br />
				<h3>Date de naissance : (ex: 07/11/1913)</h3>
				<INPUT name="dateDeNaissance" value = <fmt:formatDate value="${personne.dateDeNaissance.time}" pattern="dd/MM/yyyy"/> ></INPUT><br />
				
				<input type="submit" value="Valider" />
		
			</form>
		
			<br />
			<a href="<c:url value="/listePersonnes"/>">Retour</a>
			<br />
			<a href="<c:url value="/accueil"/>">Accueil</a>
			
		</div>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
	 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
	 	<script src="bootstrap/js/bootstrap.min.js" ></script>
	</body>

</html>

