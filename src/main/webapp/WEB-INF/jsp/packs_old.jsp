<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/header.jsp"/>
<head>
    <title>Транспортные пакеты</title>
</head>
<body>
<section>
    <h2>Транспортные пакеты</h2>
    <form method="get" action="${pageContext.request.contextPath}/rest/profile/packs">
        <input type="hidden" name="action" value="filter">
        <dl>
            <dt>С даты:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt>По дату:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr/>
    <a href="${pageContext.request.contextPath}/uploading">Загрузить траспортный пакет</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <c:forEach items="${packs}" var="pack">
            <jsp:useBean id="pack" type="ru.mshamanin.pfrxml.model.Pack"/>
            <tr data-packExcess="${pack.processed}">
                <td>
                        <%--${pack.dateTime.toLocalDate()} ${pack.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(pack.getDateTime())%>--%>
                        <%--${fn:replace(pack.dateTime, 'T', ' ')}--%>
                        ${pack.loaded}
                </td>
                <td>${pack.name}</td>
                <td><a href="dataFiles?action=open&id=${pack.id}">Открыть</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>