<%-- 
    Document   : addEditHeroes
    Created on : Oct 25, 2018, 4:30:27 PM
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
            <h2 class="text-center">Heroes</h2>
            <div class="row">
                <h2>Add New Heroes</h2>
            </div>
            <div class="row">
                <c:set var="toEdit" scope="session" value="${edit}"/>
                <c:set var="toAdd" scope="session" value="${add}"/>
                <c:if test="${toEdit}">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="editHero">
                            <div class="form-group">
                                <label for="id" class="col-md-4 control-label">ID: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="heroId" value="${heroToEdit.id}" readonly/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-md-4 control-label">Name: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" placeholder="${heroToEdit.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-md-4 control-label">Description: </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="description" placeholder="${heroToEdit.description}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="heroorvillain" class="col-md-4 control-label">Hero:</label>
                                <div class="col-md-8">
                                    <input type="radio" class="form-control" name="heroorvillain" value="hero" 
                                           <c:if test="${heroToEdit.hero eq 1}">checked</c:if>/>
                                    </div>
                                    <label for="heroorvillain" class="col-md-4 control-label">Villain:</label>
                                    <div class="col-md-8">
                                        <input type="radio" class="form-control" name="heroorvillain" value="villain" 
                                        <c:if test="${heroToEdit.villain eq 1}">checked</c:if>/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="categories" class="col-md-4 control-label">Superpowers:</label>
                                    <div class="col-md-8">
                                        <select class="selectpicker" multiple data-live-search="true" name="superpowers" required>
                                        <c:forEach items="${superpowers}" var="power">
                                            <option value="${power.id}">${power.description}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Submit Hero"/>
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
                              action="createHero">
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
                                <label for="heroorvillain" class="col-md-4 control-label">Hero:</label>
                                <div class="col-md-8">
                                    <input type="radio" class="form-control" name="heroorvillain" value="hero" checked/>
                                </div>
                                <label for="heroorvillain" class="col-md-4 control-label">Villain:</label>
                                <div class="col-md-8">
                                    <input type="radio" class="form-control" name="heroorvillain" value="villain" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="categories" class="col-md-4 control-label">Superpowers:</label>
                                <div class="col-md-8">
                                    <select class="selectpicker" multiple data-live-search="true" name="superpowers" required>
                                        <c:forEach items="${superpowers}" var="power">
                                            <option value="${power.id}">${power.description}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Submit Hero"/>
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

