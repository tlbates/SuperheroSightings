<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sightings-Home</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
        <link href="${pageContext.request.contextPath}/css/pageCss.css" rel="stylesheet">
    </head>
    <style>
        @font-face {
            font-family: "Bangers";
            src: url("css/fonts/Bangers.ttf");
        }
    </style>
    <body>
        <div class="container text-center">
            <h1 class="text-center">Superhero Sightings</h1>
            <hr/>
            <div class="row">
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn btn-danger btn-group-lg center-block"
                               href="${pageContext.request.contextPath}/displaySuperpowers">Superpowers</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn btn-danger btn-group-lg center-block"
                               href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn btn-danger btn-group-lg center-block"
                               href="${pageContext.request.contextPath}/displayHeroes">Heroes</a>
                        </div>
                    </div>
                        <div class="row"></div>
                    <div class="row">
                        <table id="recents" class="table table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">Location</th>
                                    <th scope="col">Date</th>
                                </tr>
                            </thead>
                            <c:forEach var="sighting" items="${recentSightings}">
                                <tr>
                                    <td>
                                        <a href="sightingDetails?sightId=${sighting.id}">
                                            <c:out value="${sighting.id}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${sighting.location.name}"/>
                                    </td>
                                    <td>
                                        <c:out value="${sighting.date}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                        <div class="row"></div>
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn btn-danger btn-group-lg center-block"
                               href="${pageContext.request.contextPath}/displaySightings">Sightings</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn btn-danger btn-group-lg center-block"
                               href="${pageContext.request.contextPath}/displayLocations">Locations</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

