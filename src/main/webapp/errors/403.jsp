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

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</head>
<body class="container">

<div class="alert alert-danger" role="alert">
    <h1 class="alert-heading"><i class="fa fa-warning" aria-hidden="true"></i> Access Denied !</h1>
    <div>
        Access to this page is not granted.
        <br/>
        <i class="fa fa-link" aria-hidden="true"></i> <code>${pageContext.errorData.requestURI}</code>
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
