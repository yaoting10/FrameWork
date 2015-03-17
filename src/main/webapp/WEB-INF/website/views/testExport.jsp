<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/export" class="idCardForm" method="post" enctype="multipart/form-data">
    <input name="file" type="file" />
    <input type="submit" value="提交"/>
    <input type="hidden" id="csrf_Token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
