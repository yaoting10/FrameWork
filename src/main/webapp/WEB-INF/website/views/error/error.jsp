<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <%@ include file="/WEB-INF/website/views/common/header.jsp"%>
</head>
<body>
<div class="" id="wrap">
    <%@ include file="/WEB-INF/website/views/common/top.jsp"%>
    <%@ include file="/WEB-INF/website/views/common/left.jsp"%>
    <div id="content">
        <div class="outer">
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="/index">首页</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
                &nbsp;
                <div class="col-lg-8 col-lg-offset-4">
                    <img src="/resources/website/img/prompt/abnormal.png" alt=""/>
                </div>
                <c:if test="${empty errorMsg}">
                <div class="col-lg-8 col-lg-offset-3">
                    <p class="mt15" style="font-size:30px;">对不起啦！~页面出错了，你可以再试试哦~</p>
                </div>
                </c:if>
                <c:if test="${ not empty errorMsg}">
                    <div class="col-lg-8 col-lg-offset-4">
                        <p class="mt15" style="font-size:30px;">${errorMsg}</p>
                    </div>
                </c:if>

            </div><!-- /.inner -->
        </div><!-- /.outer -->
    </div><!-- /#content -->

</div><!-- /#wrap -->
<%@ include file="/WEB-INF/website/views/common/footer.jsp"%>

</body>
</html>
