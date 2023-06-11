<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<jsp:include page="fragments/header.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<%--@elvariable id="uploading" type="ru.raiffeisen.pfrxml.web.FileModel"--%>
<form:form method = "POST" modelAttribute = "uploading"
           enctype = "multipart/form-data">
    Please select a file to upload :
    <input type = "file" name = "file" />
    <input type = "submit" value = "upload" />
</form:form>

</body>
<jsp:include page="fragments/footer.jsp"/>
</html>