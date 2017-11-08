<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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
<title>Liste des employés</title>
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
				<a class="nav-item nav-link active" href="./liste">Employés
					<span class="sr-only">(current)</span>
				</a> <a class="nav-item nav-link" href="../bulletins/liste">Bulletin</a>
			</div>
		</div>
	</nav>
	
	<h1 class="text-center">Liste des employés</h1>
	<div class="float-right">
		<a class="btn btn-primary" href="./creer" role="button">Ajouter
			un nouveau employé</a>
	</div>
	<table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th scope="col">Date/heure de création</th>
                <th scope="col">Matricule</th>
                <th scope="col">Grade</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${employes}" var="employe">
            <tr>
                <td scope="row">${employe.dateDeCreationFormat}</td>
                <td>${employe.matricule}</td>
                <td>${employe.grade.code}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>

</html>