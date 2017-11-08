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
<title>Visualiser du Bulletin </title>
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
				<a class="nav-item nav-link" href="<c:url value='/mvc/employes/liste'> </c:url>">Employés
				</a> <a class="nav-item nav-link" href="<c:url value='/mvc/bulletins/liste'> </c:url>">Bulletin</a>
			</div>
		</div>
	</nav>
	<section>
		<div class="row">
			<div class="media col-10">
				<a href="<c:url value='/mvc/bulletins/liste'> </c:url>" ><img class="d-flex align-self-center mr-3"
					src="http://www.xn--icne-wqa.com/images/icones/1/7/aiga-left-arrow.png"
					alt="Flèche noir" width="70px"></a>
				<div class="media-body">
					<h1 class="mt-0 text-center">Bulletin de salaire</h1>
				</div>
			</div>
		</div>
		<div class="ml-3">
		<div class="row"><h6 class = "offset-10">Période</h6></div>
		<div class="row"><p class="offset-10">Du ${bulletin.periode.dateDebutFormat} au ${bulletin.periode.dateFinFormat}</p></div>
		<div class="row"><h6>Entreprise</h6></div>
		<div class="row">${bulletin.remunerationEmploye.entreprise.denomination}</div>
		<div class="row">Siret : ${bulletin.remunerationEmploye.entreprise.siret}</div>
		<div class="row"><h6 class = "offset-10">Matricule : ${bulletin.remunerationEmploye.matricule}</h6></div>
		
		<div class="row"><h6>Salaire</h6></div>
		<table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th scope="col">Rubrique</th>
                <th scope="col">Base</th>
                <th scope="col">Taux Salarial</th>
                <th scope="col">Montant Salarial</th>
                <th scope="col">Taux Patronal</th>
                <th scope="col">Cot. Patronales</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">Salaire de base</th>
                <td>${bulletin.remunerationEmploye.grade.nbHeuresBase }</td>
                <td>${bulletin.remunerationEmploye.grade.tauxBase }</td>
                <td>${resultat.salaireDeBase}</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th scope="row">Prime Except.</th>
                <td></td>
                <td></td>
                <td>${bulletin.primeExceptionnelle }</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th scope="row"></th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th scope="row">Salaire Brut</th>
                <td></td>
                <td></td>
                <td>${resultat.salaireDeBase + bulletin.primeExceptionnelle}</td>
                <td></td>
                <td></td>
            </tr>
        </tbody>
    </table>
    <div class="row"><h6>Cotisations</h6></div>
			<table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th scope="col">Rubrique</th>
                <th scope="col">Base</th>
                <th scope="col">Taux Salarial</th>
                <th scope="col">Montant Salarial</th>
                <th scope="col">Taux Patronal</th>
                <th scope="col">Cot. Patronales</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsNonImposables}" var="CNI">
            <tr>
                <th scope="row">${CNI.code} ${CNI.libelle}</th>
                <td>${resultat.salaireDeBase + bulletin.primeExceptionnelle}</td>
                <td>${CNI.tauxSalarial}</td>
                <td><c:if test="${!empty CNI.tauxSalarial || CNI.tauxSalarial != 00}">${CNI.tauxSalarial * (resultat.salaireDeBase + bulletin.primeExceptionnelle)}</c:if></td>
                <td>${CNI.tauxPatronal}</td>
                <td><c:if test="${!empty CNI.tauxPatronal}">${CNI.tauxPatronal * (resultat.salaireDeBase + bulletin.primeExceptionnelle)}</c:if></td>
            </tr>
            </c:forEach>
            <tr>
                <th scope="row">Total Retenue</th>
                <td></td>
                <td></td>
                <td>${resultat.totalRetenueSalarial }</td>
                <td></td>
                <td>${resultat.totalCotisationsPatronales}</td>
            </tr>
        </tbody>
    </table>
	<div class="row"><h6>Net Imposable : ${resultat.netImposable }</h6></div>
			<table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th scope="col">Rubrique</th>
                <th scope="col">Base</th>
                <th scope="col">Taux Salarial</th>
                <th scope="col">Montant Salarial</th>
                <th scope="col">Taux Patronal</th>
                <th scope="col">Cot. Patronales</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsImposables}" var="CI">
            <tr>
                <th scope="row">${CI.code} ${CI.libelle}</th>
                <td>${resultat.salaireDeBase + bulletin.primeExceptionnelle}</td>
                <td>${CI.tauxSalarial}</td>
                <td><c:if test="${!empty CI.tauxSalarial}">${CI.tauxSalarial * (resultat.salaireDeBase + bulletin.primeExceptionnelle)}</c:if></td>
                <td>${CI.tauxPatronal}</td>
                <td><c:if test="${!empty CI.tauxPatronal}">${CI.tauxPatronal * (resultat.salaireDeBase + bulletin.primeExceptionnelle)}</c:if></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="row"><h6 class = "offset-10">NET A PAYER : ${resultat.netAPayer }</h6></div>
		</div>
		
		</section>
		</body>
		</html>