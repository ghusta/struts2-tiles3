<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Success !</title>
</head>
<body>

<div>
    <h1>Page secured !</h1>
</div>
<div>
    <code>Whoami : </code>
    <span><sec:authentication property="principal.username" /></span>
</div>
<br/>
<br/>
<sec:authorize access="hasRole('USER')">
<div>
Has role : USER
</div>
</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
<div>
Has role : ADMIN
</div>
</sec:authorize>
</body>
</html>
