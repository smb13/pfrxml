<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title><spring:message code="app.title"/></title>
    <div class="card">
        <h5 class="card-header"><a href="" class="navbar-brand"><img src="resources/images/logo.svg"> <spring:message
                code="app.title"/></a></h5>
    </div>
    <%--    <a href="" class="navbar-brand"><img src="resources/images/logo.svg"> <spring:message code="app.title"/></a>--%>
    <base href="${pageContext.request.contextPath}/"/>

    <link rel="stylesheet" href="resources/css/style.css?v=2">
    <link rel="stylesheet" href="webjars/bootstrap/4.5.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/demo/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.21/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/lib/noty.css"/>
    <link rel="stylesheet" href="webjars/datetimepicker/2.5.20-1/jquery.datetimepicker.css">
    <link rel="shortcut icon" href="resources/images/icon-meal.png">

    <script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.5.3/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.21/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.21/js/dataTables.bootstrap4.min.js" defer></script>
    <script type="text/javascript" src="webjars/noty/3.1.4/lib/noty.min.js" defer></script>
    <script type="text/javascript" src="webjars/datetimepicker/2.5.20-1/build/jquery.datetimepicker.full.min.js" defer></script>
</head>
