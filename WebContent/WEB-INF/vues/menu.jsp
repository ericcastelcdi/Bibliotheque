<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri= "/WEB-INF/c.tld" prefix="c"%>

<html>

	<head>
		<title>BIBLIOTHEQUE - Accueil</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link type="text/css" href="bootstrap/css/bootstrap.css" rel="stylesheet">
		<link type="text/css" href="css/boutons.css" rel="stylesheet">
	</head>
	
	<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		
	  		<a class="navbar-brand " href="<c:url value="/accueil"/>">BIBLIOTHEQUE<span class="sr-only">(current)</span></a>
	  		
		  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
	
		  	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		  
			    <ul class="navbar-nav mr-auto">
			      <li class="nav-item">
			      <a class="nav-link" href="<c:url value="/listeGenres"/>">Genres</a>
			      </li>
			       <li class="nav-item">
			        <a class="nav-link" href="<c:url value="/listeOeuvres"/>">Oeuvres</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value="/listeAuteurs"/>">Auteurs</a>
			      </li>
			      <li class="nav-item">
			      	<a class="nav-link" href="<c:url value="/listeAbonnes"/>">Abonnés</a>
			      </li>
			      <!--  <li class="nav-item">
			        <a class="nav-link" href="<c:url value="/listePersonnes"/>">Personnes</a>
			      </li>-->
			      
			    </ul>
			    
			    <form class="form-inline my-2 my-lg-0">
			      	<input class="form-control mr-sm-2" type="search" placeholder="Rechercher ..." aria-label="Search">
			      	<button class="btn btn-secondary my-2 my-sm-0 boutonHeight36" type="submit">
			      		<img src="images/loupe2.png" alt="rechercher">
			      	</button>
			    </form>
			    
		  	</div>
	  	</nav>

		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
		<script src="bootstrap/js/bootstrap.min.js" ></script>
	</body>

</html>