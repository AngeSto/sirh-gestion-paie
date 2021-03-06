<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
	integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
	integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
	crossorigin="anonymous"></script>
<title>Créer un employé</title>
</head>

<body class="container-fluid">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="./liste">Employés
				</a> <a class="nav-item nav-link" href="../bulletins/liste">Bulletin</a>
			</div>
		</div>
	</nav>
	<section>
		<div class="row">
			<div class="media col-10">
				<a href="./liste" ><img class="d-flex align-self-center mr-3"
					src="http://www.xn--icne-wqa.com/images/icones/1/7/aiga-left-arrow.png"
					alt="Flèche noir" width="70px"></a>
				<div class="media-body">
					<h1 class="mt-0 text-center">Ajouter un employé</h1>
				</div>
			</div>
		</div>
		<form:form method="post" modelAttribute="employe">
			<div class="form-group row">
				<label for="inputMatricule" class="col-3 col-form-label text-right">Matricule</label>
				<div class="col-5">
					<form:input type="text" class="form-control" id="inputMatricule"
						placeholder="Matricule" path="matricule" required="required"/>
				</div>
			</div>


			<div class="form-group row">
				<label for="inputEntreprise" class="col-3 col-form-label text-right">Entreprise</label>
				<div class="col-5">
					<form:select class="custom-select mb-3 col-5 mb-md-0"
						id="selectEntreprise" required="required" path="entreprise">
						<option selected>Veuillez choisir</option>
						<c:forEach items="${listeEntreprise}" var="entreprise">
						<option>${entreprise.denomination}</option>
						</c:forEach>
						
						
					</form:select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputProfil" class="col-3 col-form-label text-right">Profil</label>
				<div class="col-5">
					<form:select class="custom-select mb-3 col-5 mb-md-0" id="selectProfil"
						required="required" path="profilRemuneration">
						<option selected>Veuillez choisir</option>
						<c:forEach items="${listeProfil}" var="profil">
						<option>${profil.code}</option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputGrade" class="col-3 col-form-label text-right">Grade</label>
				<div class="col-5">
					<form:select class="custom-select mb-3 col-5 mb-md-0" id="selectGrade"
						required="required" path="grade">
						<option selected>Veuillez choisir</option>
						<c:forEach items="${listeGrade}" var="grade">
						<option>${grade.code}</option>
						</c:forEach>
					</form:select>
					<div class="float-right">
						<input type="submit" class="btn btn-primary" id="btnAjouter"
							value="Ajouter">
					</div>
				</div>
			</div>
		</form:form>
	</section>
</body>

</html>