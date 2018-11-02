<%-- 
    Document   : displayOrganizations
    Created on : Oct 22, 2018, 7:41:48 PM
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
            <h1 class="text-center">Superhero Sightings</h1>
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
                    <li role="presentation" class="active">
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocations">
                            Locations
                        </a>
                    </li>
                </ul>    
            </div>
            <hr/>
            <h2 class="text-center">Organizations</h2>
            <div class="row">
                <a class="btn btn-danger btn-group-lg center-block"
                   href="${pageContext.request.contextPath}/getAddOrganizationForm">
                    Create Organization</a>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <table id="organizationTable" class="table table-hover">
                        <tr>
                            <th width="10%">#</th>
                            <th width="20%">Name</th>
                            <th width="20%">Heroes</th>
                        </tr>
                        <c:forEach var="org" items="${organizations}">
                            <tr>
                                <td>
                                    <c:out value="${org.id}"/>
                                </td>
                                <td>
                                    <a id="#showDetails" href="organizationDetails?orgId=${org.id}">
                                        <c:out value="${org.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            Heroes
                                        </button>
                                        <div class="dropdown-menu">
                                            <c:forEach var="person" items="${org.people}">
                                                <p class="dropdown-item">${person.name}</p>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <a href="findOrgToEdit?orgId=${org.id}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteOrganization?orgId=${org.id}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>     
                </div>
                <div class="col-md-6" id="details">
                    <div class="row">
                        <h2 class="text-center">${orgDet.name}</h2>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h4 class="text-center">Phone: ${orgDet.phone}</h4>
                        </div>
                        <div class="col-md-6">
                            <h4 class="text-center">Email: ${orgDet.email}</h4>
                        </div>
                    </div>
                    <div class="row">
                        <h4 class="text-center">Location Name: ${orgDet.location.name}</h4>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
