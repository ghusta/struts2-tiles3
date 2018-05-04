<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>Security Scan</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>

    <style>
        div.card {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Security Scan (@Annot.)</h1>

    <%-- ROW 1 --%>
    <div class="row">
        <div class="col">
            <div class="card border-primary">
                <label class="card-header">Liste Namespaces</label>
                <ul class="list-group list-group-flush text-primary">
                    <s:iterator value="namespaces" var="ns">
                        <li class="list-group-item">${ns == "" ? "_" : ns}</li>
                    </s:iterator>
                </ul>
                <div class="card-footer">
                    <small class="text-muted">Total namespaces : <s:property value="namespaces.size" /></small>
                </div>
            </div>
        </div>
    </div>

    <%-- ROW 2 --%>
    <div class="row">
        <div class="col">
            <div class="card">
                <label class="card-header">Liste Actions ("")</label>
                <ul class="list-group list-group-flush">
                    <s:iterator value="actionNames" var="action1">
                        <li class="list-group-item">${action1 == "" ? "_" : action1}</li>
                    </s:iterator>
                </ul>
            </div>
        </div>

        <div class="col">
            <div class="card">
                <label class="card-header">Liste Actions ("/tiles")</label>
                <ul class="list-group list-group-flush">
                    <s:iterator value="actionNamesNsTiles" var="action2">
                        <li class="list-group-item">${action2}</li>
                    </s:iterator>
                </ul>
            </div>
        </div>

        <div class="col">
            <div class="card">
                <label class="card-header">Liste Actions ("/foo")</label>
                <ul class="list-group list-group-flush">
                    <s:iterator value="actionNamesNsFoo" var="action3">
                        <li class="list-group-item">${action3}</li>
                    </s:iterator>
                </ul>
            </div>
        </div>
    </div>

</div>
</body>
</html>
