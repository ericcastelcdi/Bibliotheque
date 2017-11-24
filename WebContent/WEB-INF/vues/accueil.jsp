<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

	<head>
		<title>BIBLIOTHEQUE - Accueil</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
		<link type="text/css" href="css/formatPage.css" rel="stylesheet" media="screen"/>
		<link type="text/css" href="css/tableaux.css" rel="stylesheet" media="screen"/>
	
	</head>
	
	<body>
		<!-- on ajoute le menu du site -->
		<%@include file="menu.jsp"%>
		
		<div>
			<img src="images/BIBLIOTHEQUE 2.4.jpg" class="superbg" >
		</div>
		
		<p class="bienvenue centrage">Bienvenue </p>
		<p class="bienvenue2 centrage2" >à la bibliothèque </p>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
		<script src="bootstrap/js/bootstrap.min.js" ></script>
		<script src="<c:url value="/JS/background.js"/>"></script>
	</body>

</html>


