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
<title>Créer un bulletin</title>
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
				<a class="nav-item nav-link" href="../employes/liste">Employés
				</a> <a class="nav-item nav-link" href="./liste">Bulletin</a>
			</div>
		</div>
	</nav>
	<h1 class="text-center">Créer un bulletin de salaire</h1>
	<form:form method="post" modelAttribute="bulletin">
			


			<div class="form-group row">
				<label for="inputPeriode" class="col-3 col-form-label text-right">Période</label>
				<div class="col-5">
					<form:select class="custom-select mb-3 col-5 mb-md-0"
						id="selectPeriode" required="required" path="periode">
						<option selected>Veuillez choisir</option>
						<c:forEach items="${periodes}" var="periode">
						<option value="${periode.id}">${periode.dateDebutFormat} - ${periode.dateFinFormat}</option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputMatricule" class="col-3 col-form-label text-right">Matricule</label>
				<div class="col-5">
					<form:select class="custom-select mb-3 col-5 mb-md-0" id="selectMatricule"
						required="required" path="remunerationEmploye.matricule">
						<option selected>Veuillez choisir</option>
						<c:forEach items="${employes}" var="employe">
						<option>${employe.matricule}</option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPrime" class="col-3 col-form-label text-right">Prime exceptionnelle</label>
				<div class="col-5">
					<form:input type="number" class="form-control" id="inputPrime"
						placeholder="Prime Exceptionnelle" path="primeExceptionnelle" required="required"/>
						<div class="float-right">
						<input type="submit" class="btn btn-primary" id="btnCreer"
							value="Créer">
					</div>
				</div>
			</div>
		</form:form>
	</body>
	</html>