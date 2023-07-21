<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Testing Struts 2 + Tiles</title>

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

</head>
<body>
<div id="main-container" class="container">
    <h1>Hello ! <span class="badge bg-info"><sec:authentication property="principal.username" /></span></h1>
    <br/>

    <h2>Tests basiques</h2>
    <div class="list-group">
        <a href="<s:url action="index"/>" class="list-group-item list-group-item-action">
            Accueil
            <small>(Action Struts 2)</small>
        </a>
        <a href="<s:url action="notiles/index"/>" class="list-group-item list-group-item-action">
            No Tiles !
            <small>(Action Struts 2)</small>
        </a>
        <a href="<c:url value="not-defined.jsp" />" class="list-group-item list-group-item-action list-group-item-danger">
            Page 404 ?
        </a>
    </div>
    <br/>

    <h2>Tests Tiles 3</h2>
    <ul class="list-group">
        <li class="list-group-item"><a href="<s:url namespace="/tiles" action="test-tiles"/>">Test : test-tiles</a></li>
        <li class="list-group-item"><a href="<s:url namespace="/tiles" action="test-tiles-annotation"/>">Test Tiles with annotations / without XML</a></li>
        <li class="list-group-item"><a href="<s:url action="foo/bar" />">Test : foo/bar</a> - Conf (wildcard)</li>
    </ul>
    <br/>

    <h2>Tests Spring Security</h2>
    <label>Roles :</label>&nbsp;<em><sec:authentication property="principal.authorities" /></em>
    <ul class="list-group">
        <li class="list-group-item"><a href="<s:url namespace="/secured" action="secured"/>">Test #1</a></li>
        <li class="list-group-item"><a href="<s:url namespace="/mixed" action="mixed-user"/>">Mixed : User</a></li>
        <li class="list-group-item"><a href="<s:url namespace="/mixed" action="mixed-superuser"/>">Mixed : SuperUser</a></li>
    </ul>
    <br/>

    <h2>Debug</h2>
    <div class="list-group">
        <a href="<c:url value="config-browser/" />" class="list-group-item list-group-item-action"><i class="fa fa-cogs fa-lg fa-fw" aria-hidden="true"></i> Debug : Config Browser</a>
        <a href="<s:url namespace="/security" action="security-scan"/>" class="list-group-item list-group-item-action"><i class="fa fa-shield fa-lg fa-fw" aria-hidden="true"></i> Security Scan (@Annot.)</a>
    </div>

    <p>
        Context-Path : <code><c:out value="${pageContext.request.contextPath}" default="?"/></code>
    </p>
    <p>
        <h2>Accessible URLs :</h2>
        <ul>
<sec:authorize url="/admin/">
    <li>URL <code>/admin/</code> OK</li>
</sec:authorize>
<sec:authorize url="/admin/superadmin/">
    <li>URL <code>/admin/superadmin/</code> OK</li>
</sec:authorize>
<sec:authorize url="/secured/">
    <li>URL <code>/secured/</code> OK</li>
</sec:authorize>
        </ul>
    </p>
</div>
</body>
</html>
