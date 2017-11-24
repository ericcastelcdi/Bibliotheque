<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Formatage des dates :
<ul>
  <li>Standard date/time: <fmt:formatDate value="${cal.time}" type="both" /></li>
  <li>Standard date: <fmt:formatDate value="${cal.time}" type="date" /></li>
  <li>Day: <fmt:formatDate value="${cal.time}" pattern="d" /></li>
  <li>Month: <fmt:formatDate value="${cal.time}" pattern="M" /></li>
  <li>Year: <fmt:formatDate value="${cal.time}" pattern="yyyy" /></li>
  <li>dd-MM-yyyy: <fmt:formatDate value="${cal.time}" pattern="dd-MM-yyyy" /></li>
  <li>MM/dd/yyyy: <fmt:formatDate value="${cal.time}" pattern="MM/dd/yyyy" /></li>
</ul>-->

<html>

<head>
	<title>BIBLIOTHEQUE - Personnes</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>

<body>
<!-- on ajoute le menu du site -->
	<%@include file="menu.jsp"%>

	<h2>Liste des personnes</h2>
	
	<table border="1">
	
		<tr>
			<th>id</th>
			<th>prenom</th>
			<th>nom</th>
			<th>dateDeNaissance</th>
		</tr>
		
		<c:forEach var="personne" items="${persons}">
			<tr>		
				<td><c:out value="${personne.id}" /></td>
				<td><c:out value="${personne.prenom}" /></td>
				<td><c:out value="${personne.nom}" /></td>
				<td><fmt:formatDate value="${personne.dateDeNaissance.time}" pattern="dd/MM/yyyy"/></td>
				<td><a href="<c:url value="/modify?id=${personne.id}"/> " >Modifier</a></td>
				<td><a href="<c:url value="/delete?id=${personne.id}"/> " >Supprimer</a></td>
			</tr>
		</c:forEach>
					
	</table>
	
	<br>
	<a href="<c:url value="/add"/>">Ajout</a>
	<br>
	<a href="<c:url value="/accueil"/>">Retour</a>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
 	<script src="bootstrap/js/bootstrap.min.js" ></script>
</body>

</html>


