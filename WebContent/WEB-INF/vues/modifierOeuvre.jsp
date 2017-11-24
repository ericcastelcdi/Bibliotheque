<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

	<head>
		<title>BIBLIOTHEQUE - Oeuvres</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	</head>
	
	<body>
	<!-- on ajoute le menu du site -->
		<%@include file="menu.jsp"%>
	
		<h2>Modification Oeuvre</h2>
		
		<form method="post" action="<c:url value="/validerModifierOeuvre"/>">
		
			<font size=4>ISBN :</font> 
			<INPUT name="isbn" value="${oeuvre.isbn}" readonly="readonly"></INPUT><br/><br/>
			<font size=4>Titre :</font> 
			<INPUT name="titreOeuvre" value="${oeuvre.titreOeuvre}"></INPUT><br/><br/>
			<font size=4>Sous-titre :</font> 
			<INPUT name="sousTitreOeuvre" value="${oeuvre.sousTitreOeuvre}"></INPUT><br/><br/>
			<font size=4>Date d'édition (jj/mm/yyyy) :</font> 
			<INPUT name="dateOeuvre" value = <fmt:formatDate value="${oeuvre.dateOeuvre.time}" pattern="dd/MM/yyyy"/> ></INPUT> <br/><br/>
			<font size=4 >Auteur :</font>
			<input type='hidden' name = "idAuteur" value="<c:out value="${oeuvre.auteur.idAuteur}"/>">
			<INPUT name="auteur" style ="width: 300px" value="${oeuvre.auteur}" readonly="readonly"></INPUT>
			
			<font size=4>  Changer : </font>	
				
				<select name="selectAuteur" id="auteurs" style="width:270px ; overflow: scroll">
					<option value="Aucun" ><c:out value="---------- Selectionner un auteur ----------"/>
	   				<c:forEach var="auteur" items="${auteurs}">
	   				
		   				<c:choose>		   				
						    <c:when test="${idAuteurSelectionne != auteur.idAuteur}">
       							<option value="<c:out value="${auteur.idAuteur}"/>" ><c:out value="${auteur}" />
	    					</c:when>
	    					
	    					<c:otherwise>
	        					<option value="<c:out value="${auteur.idAuteur}"/>" selected="selected"><c:out value="${auteur}" />
	    					</c:otherwise>		
						</c:choose>
						
					</c:forEach>
				</select> 
				
			<a href="<c:url value="/ajouterAuteur?page=modification"/>"><input type="button" name="creerAuteur" value="Ajout nouvel auteur" /></a><br /><br />
				
			<font size=4>Genre :</font>
			<INPUT name="genre" style ="width: 300px" value="${oeuvre.nomDuGenre}" readonly="readonly"></INPUT>
			<font size=4>  Changer : </font>
			
				<select name="selectGenre" id="genres" style="width:270px">
					<option value="Aucun"><c:out value="---------- Selectionner un genre ----------"/>
	   				<c:forEach var="genre" items="${genres}">
						<option><c:out value="${genre.nomGenre}" />
					</c:forEach>
				</select> <br /><br />
			
			<input type="submit" value="Enregistrer modifications" />
	
		</form>
	
		<br />
		<a href="<c:url value="/listeOeuvres"/>">Retour</a>
		<br />
		<a href="<c:url value="/accueil"/>">Accueil</a>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
		<script src="bootstrap/js/bootstrap.min.js" ></script>
	</body>

</html>

