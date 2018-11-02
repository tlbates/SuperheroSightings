<%-- 
    Document   : displayLocations
    Created on : Oct 22, 2018, 7:47:09 PM
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayHeroes">
                            Heroes
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightings">
                            Sightings
                        </a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayLocations">
                            Locations
                        </a>
                    </li>
                </ul>    
            </div>
            <hr/>
            <h2 class="text-center">Locations</h2>
            <div class="row">
                <a class="btn btn-danger btn-group-lg center-block"
                               href="${pageContext.request.contextPath}/getAddLocationForm">
                    Create Location</a>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <table id="powerTable" class="table table-hover">
                        <tr>
                            <th width="20%">#</th>
                            <th width="20%">Name</th>
                            <th width="20%"></th>
                            <th width="20%"></th>
                        </tr>
                        <c:forEach var="loc" items="${locations}">
                            <tr>
                                <td>
                                    <c:out value="${loc.id}"/>
                                </td>
                                <td>
                                    <a id="#showDetails" href="locationDetails?locId=${loc.id}">
                                        <c:out value="${loc.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <a href="findLocToEdit?locId=${loc.id}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteLocation?locId=${loc.id}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>     
                </div>
                <div class="col-md-6" id="details">
                    <div class="row">
                        <h2>
                            <c:out value="${locDet.name}"/>
                        </h2>
                    </div>
                    <div class="row">
                        <h4>
                            <c:out value="${locDet.description}"/>
                        </h4>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h4>
                                <c:choose>
                                    <c:when test="${locDet.latitude == 0}"></c:when>
                                    <c:when test="${locDet.latitude != 0}">
                                        <c:out value="${locDet.latitude}"/>
                                    </c:when>
                                </c:choose>
                            </h4>
                        </div>
                        <div class="col-md-6">
                            <h4>
                                <c:choose>
                                    <c:when test="${locDet.longitude == 0}"></c:when>
                                    <c:when test="${locDet.longitude != 0}">
                                        <c:out value="${locDet.longitude}"/>
                                    </c:when>
                                </c:choose>
                            </h4>
                        </div>
                        <div class="row">
                            <h4>
                                <c:out value="${locDet.street}"/>
                            </h4>
                            <h4>
                                <c:out value="${locDet.city}"/>
                            </h4>
                            <h4>
                                <c:out value="${locDet.state}"/>
                            </h4>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <h4>
                                    <c:choose>
                                        <c:when test="${locDet.zip == 0}"></c:when>
                                        <c:when test="${locDet.zip != 0}">
                                            <c:out value="${locDet.zip}"/>
                                        </c:when>
                                    </c:choose>
                                </h4>
                            </div>
                            <div class="col-md-6">
                                <h4>
                                    <c:out value="${locDet.country}"/>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
