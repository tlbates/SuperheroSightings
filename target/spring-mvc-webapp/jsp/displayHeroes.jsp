<%-- 
    Document   : displayHeroes
    Created on : Oct 22, 2018, 6:03:08 PM
    Author     : tylerbates
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
        <link href="${pageContext.request.contextPath}/css/pageCss.css" rel="stylesheet">

    </head>
    <body>
        <div class="container text-center">
            <h1>Superhero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperpowers">
                            Superpowers
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizations">
                            Organizations
                        </a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayHeroes">
                            Heroes
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightings">
                            Sightings
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocations">
                            Locations
                        </a>
                    </li>
                </ul>    
            </div>
            <hr/>
            <h2 class="text-center">Heroes</h2>
            <div class="row">
                <a class="btn btn-danger btn-group-lg center-block"
                   href="${pageContext.request.contextPath}/getAddHeroForm">
                    Create Hero</a>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <table id="heroTable" class="table table-hover">
                        <tr>
                            <th width="10%">#</th>
                            <th width="20%">Hero Name</th>
                            <th width="20%">Powers</th>
                            <th width="20%"></th>
                        </tr>
                        <c:forEach var="hero" items="${people}">
                            <tr>
                                <td>
                                    <c:out value="${hero.id}"/>
                                </td>
                                <td>
                                    <a id="#showDetails" href="heroDetails?heroId=${hero.id}">
                                        <c:out value="${hero.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-danger dropdown-toggle"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            Superpowers
                                        </button>
                                        <div class="dropdown-menu">
                                            <c:forEach var="power" items="${hero.superpowers}">
                                                <p class="dropdown-item">${power.description}</p>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <a href="findHeroToEdit?heroId=${hero.id}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteHero?heroId=${hero.id}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>     
                </div>
                <div class="col-md-6" id="details">
                    <div class="row">
                        <h2 class="text-center">${personDet.name}</h2>
                    </div>
                    <div class="row text-center">
                        <c:if test="${personDet.hero eq 1}">
                            <h4 class="col-md-12">Superhero</h4>
                        </c:if>
                        <c:if test="${personDet.villain eq 1}">
                            <h4 class="col-md-12">Villain</h4>
                        </c:if>
                    </div>
                    <div class="row">
                        <h4 class="text-center">${personDet.description}</h4>
                    </div>
                    <div class="list-group">
                        <c:forEach var="power" items="${personDet.superpowers}">
                            <li class="text-center">${power.description}</li>
                            </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>