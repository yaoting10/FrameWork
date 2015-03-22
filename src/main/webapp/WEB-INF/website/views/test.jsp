<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>

<body>
<div>
    <form action="/poiTest/xlsDto2Excel" method="post" enctype="multipart/form-data">
        excel文件：<input type="file" name="file"/><br>
        <input type="submit" value="提交" />
        <input type="hidden" id="csrf_Token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>
