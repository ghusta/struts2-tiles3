<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Testing Struts 2 + Tiles</title>

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
<div id="main-container" class="container">
    <h1>Hello !</h1>

    <br/>
    <h2>Tests basiques</h2>
    <ul class="list-group">
        <li class="list-group-item"><a href="<s:url action="index"/>">Accueil</a>&nbsp;<i>(Action Struts 2)</i></li>
        <li class="list-group-item"><a href="<s:url action="notiles/index"/>">No Tiles !</a>&nbsp;<i>(Action Struts 2)</i></li>
        <li class="list-group-item list-group-item-danger"><a href="<c:url value="not-defined.jsp" />">Page 404 ?</a></li>
    </ul>

    <br/>
    <h2>Tests Tiles 3</h2>
    <ul class="list-group">
        <li class="list-group-item"><a href="<s:url namespace="/tiles" action="test-tiles"/>">Test : test-tiles</a></li>
        <li class="list-group-item"><a href="<s:url namespace="/tiles" action="test-tiles-annotation"/>">Test Tiles with annotations / without XML</a></li>
        <li class="list-group-item"><a href="<s:url action="foo/bar" />">Test : foo/bar</a> - Conf (wildcard)</li>
    </ul>

    <br/>
    <h2>Debug</h2>
    <ul class="list-group">
        <li class="list-group-item">
            <a href="<s:url namespace="/config-browser" action="index" />"><i class="fa fa-cogs fa-lg" aria-hidden="true"></i> Debug : Config Browser</a>
        </li>
    </ul>

    <p>
        Context-Path : <code><c:out value="${pageContext.request.contextPath}"/></code>
    </p>
</div>
</body>
</html>
