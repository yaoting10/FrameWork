<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta name="msapplication-TileColor" content="#5bc0de" />
    <jsp:include page="common/header.jsp"></jsp:include>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="/resources/website/lib/bootstrap/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="/resources/website/lib/font-awesome/css/font-awesome.min.css">

    <!-- Metis core stylesheet -->
    <link rel="stylesheet" href="/resources/website/css/main.css">

</head>
<body class="login">
<div class="form-signin">
    <div class="text-center">
        <%--<img src="assets/img/logo.png" alt="Metis Logo">--%>
        <h4>快递费用管理系统</h4>
        <h3>管理账户登录</h3>
    </div>
    <hr>
    <div class="tab-content">
        <div id="login" class="tab-pane active">
            <form action="/admin/login" method="post">
                <p class="text-muted text-center">
                    <!--Enter your username and password-->
                    <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
                        <span class="error">用户名密码错误</span>
                    </c:if>
                    <c:if test="${empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
                        <span>请输入用户名和密码</span>
                    </c:if>
                </p>
                <input type="text" placeholder="用户名" class="form-control top" id="username" name="username">
                <input type="password" placeholder="密码" class="form-control bottom" id="passoword" name="password">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </form>
        </div>

    </div>
</div>
<script src="/resources/website/lib/jquery/jquery.min.js"></script>
<script src="/resources/website/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    (function($) {
        $(document).ready(function() {
            $('.list-inline li > a').click(function() {
                var activeForm = $(this).attr('href') + ' > form';
                //console.log(activeForm);
                $(activeForm).addClass('animated fadeIn');
                //set timer to 1 seconds, after that, unload the animate animation
                setTimeout(function() {
                    $(activeForm).removeClass('animated fadeIn');
                }, 1000);
            });
        });
    })(jQuery);
</script>
</body>
</html>