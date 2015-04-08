<%--
  Created by IntelliJ IDEA.
  User: lxl
  Date: 2014-12-09
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="top">
    <!-- .navbar -->
    <nav class="navbar navbar-static-top">
        <div class="container-fluid">

            <!-- Brand and toggle get grouped for better mobile display -->
            <header class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">
                </a>
            </header>
            <div class="navbar-title">ECCS后台管理系统</div>
            <div class="topnav">
                <div class="btn-group">
                    <a id="logout_btn" href="#" data-toggle="tooltip" data-original-title="退出" data-placement="bottom" class="btn btn-metis-1 btn-sm">
                        <i class="fa fa-power-off"></i>
                    </a>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </nav><!-- /.navbar -->
    <form id="logout_form" action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div><!-- /#top -->

