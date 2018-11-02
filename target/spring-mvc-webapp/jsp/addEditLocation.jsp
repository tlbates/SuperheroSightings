<%-- 
    Document   : addEditLocation
    Created on : Oct 25, 2018, 8:38:01 PM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocations">
                            Locations
                        </a>
                    </li>
                </ul>    
            </div>
            <hr/>
            <h2 class="text-center">Location</h2>
            <div class="row">
                <h2>Add New Location</h2>
            </div>
            <div class="row">
                <c:set var="toEdit" scope="session" value="${edit}"/>
                <c:set var="toAdd" scope="session" value="${add}"/>
                <c:if test="${toEdit}">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="editLocation">
                            <div class="form-group">
                                <label for="orgid" class="col-md-4 control-label">ID: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="locId" value="${locToEdit.id}" readonly/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-md-4 control-label">Name: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" placeholder="${locToEdit.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-md-4 control-label">Description: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="description" placeholder="${locToEdit.description}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="longitude" class="col-md-4 control-label">Longitude: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="longitude" placeholder="${locToEdit.longitude}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="latitude" class="col-md-4 control-label">Latitude: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="latitude" placeholder="${locToEdit.latitude}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="street" class="col-md-4 control-label">Street: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="street" placeholder="${locToEdit.street}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="city" class="col-md-4 control-label">City: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="city" placeholder="${locToEdit.city}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="state" class="col-md-4 control-label">State: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="state" placeholder="${locToEdit.state}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="zip" class="col-md-4 control-label">ZIP: </label>
                                <div class="col-md-8">
                                    <input type="number" class="form-control" name="zip" placeholder="${locToEdit.zip}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="country" class="col-md-4 control-label">Country: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="country" placeholder="${locToEdit.country}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Submit Sighting"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-3"></div>
                </c:if>
                <c:if test="${add}">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createLocation">
                            <div class="form-group">
                                <label for="name" class="col-md-4 control-label">Name: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" placeholder="Name" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-md-4 control-label">Description: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="description" placeholder="Description" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="longitude" class="col-md-4 control-label">Longitude: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="longitude" placeholder="Longitude"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="latitude" class="col-md-4 control-label">Latitude: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="latitude" placeholder="Latitude"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="street" class="col-md-4 control-label">Street: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="street" placeholder="Street"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="city" class="col-md-4 control-label">City: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="city" placeholder="City"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="state" class="col-md-4 control-label">State: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="state" placeholder="State"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="zip" class="col-md-4 control-label">ZIP: </label>
                                <div class="col-md-8">
                                    <input type="number" class="form-control" name="zip" placeholder="ZIP"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="country" class="col-md-4 control-label">Country: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="country" placeholder="Country"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Submit Location"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-3"></div>
                </c:if>
            </div>

        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
