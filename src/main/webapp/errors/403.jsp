<!DOCTYPE html>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%
    response.setStatus(403);
%>
<html>
<head>
    <title>Access Denied</title>

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
        integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</head>
<body class="container">

<div class="alert alert-danger" role="alert">
    <h1 class="alert-heading"><i class="fa fa-warning fa-fw" aria-hidden="true"></i> Access Denied !</h1>
    <div>
        Access to this page is not granted.
        <br/>
        <i class="fa fa-link fa-fw" aria-hidden="true"></i> <code>${pageContext.errorData.requestURI}</code>
    </div>
<!-- Error from : ${pageContext.errorData} -->
<!-- Throwable : ${pageContext.errorData.throwable} -->
<!-- URI : ${pageContext.errorData.requestURI} -->
<%--
    System.err.println(
        pageContext.getAttribute("errorData"));
--%>
</div>

</body>
</html>
