<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>

</head>
<body>
<h1>Hello !</h1>

<p>
    <a href="<s:url action="index"/>">Accueil</a>
    <br/>

</p>

<p>
    Context-Path : <c:out value="${pageContext.request.contextPath}" />
</p>

</body>
</html>
