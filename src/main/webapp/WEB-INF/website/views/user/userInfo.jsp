<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>业务员详情</title>
    <%@ include file="/WEB-INF/website/views/common/header.jsp" %>
    <style>
        .help-block{
            color: red;
        }
    </style>
</head>
<body>
<div class="bg-light lter" id="wrap">
<%@ include file="/WEB-INF/website/views/common/top.jsp"%>
<%@ include file="/WEB-INF/website/views/common/left.jsp" %>
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div id="content">
<div class="outer">
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">用户列表</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">用户详情</a>
        </li>
    </ul>
</div>
<div class="inner bg-light lter">
<div class="row">
    <div class="col-lg-12">
        <div class="box">
            <header>
                <div class="icons">
                    <i class="fa fa-th"></i>
                </div>
                <h5>基本信息</h5>
                <!-- .toolbar -->
                <div class="toolbar">
                    <nav style="padding: 8px;">
                        <a class="btn btn-default btn-xs collapse-box" href="javascript:;">
                            <i class="fa fa-minus"></i>
                        </a>
                        <a class="btn btn-default btn-xs full-box" href="javascript:;">
                            <i class="fa fa-expand"></i>
                        </a>
                    </nav>
                </div>
                <!-- /.toolbar -->
            </header>
            <c:if test="${not empty user}">
                <div class="body collapse in" id="div-5" aria-expanded="true" style="">
                    <form:form commandName="userVo" method="post"
                               action="/user/edit" class="form-horizontal">
                        <div class="form-group row">
                            <label class="control-label col-lg-2">用户名：</label>

                            <div class="col-lg-4">
                                <input name="userName"
                                       type="text"value="${user.userName}"
                                       readonly
                                       class="form-control">
                            </div>
                            <label class="control-label col-lg-2">编号：</label>
                            <div class="col-lg-4">
                                <input name="userNumber"
                                       type="text"
                                       readonly
                                       value="${user.userNumber}"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-2">类型：</label>

                            <div class="col-lg-4">
                                <select id="userType"
                                        name="userType"
                                        class="form-control input-sm"
                                        disabled
                                        aria-controls="dataTable">
                                    <option <c:if test="${user.userType ==1}">selected="selected"</c:if> value="1">管理员</option>
                                    <option <c:if test="${user.userType ==2}">selected="selected"</c:if> value="2">业务员</option>
                                </select>
                            </div>
                            <label class="control-label col-lg-2">性别：</label>

                            <div class="col-lg-4">
                                <select id="userSex"
                                        name="userSex"
                                        class="form-control input-sm"
                                        disabled
                                        aria-controls="dataTable">
                                    <option <c:if test="${user.userSex ==0}">selected="selected"</c:if> value="0">男</option>
                                    <option <c:if test="${user.userSex ==1}">selected="selected"</c:if> value="1">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-2">电话号码：</label>
                            <div class="col-lg-4">
                                <input name="phone"
                                       type="text"
                                       readonly
                                       value="${user.phone}"
                                       class="form-control">
                            </div>
                            <label class="control-label col-lg-2">身份证：</label>

                            <div class="col-lg-4">
                                <input name="idCard"
                                       type="text"
                                       readonly
                                       value="${user.idCard}"
                                       class="form-control">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="control-label col-lg-2">登录密码：</label>
                            <div class="col-lg-4">
                                <input name="password"
                                       type="text"
                                       readonly
                                       value="${user.password}"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-lg-1 col-lg-offset-6">
                                <a class="btn btn-primary btn-sm btn-line" href="/index"
                                   data-original-title="返回" title="">返回</a>
                            </div>

                        </div>
                        <input hidden="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" id="csrf_Token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </div>
            </c:if>

        </div>
    </div>
</div>

</div>
<!-- /.inner -->
</div>
<!-- /.outer -->
</div>
<!-- /#content -->

</div>
<!-- /#wrap -->
<%@ include file="/WEB-INF/website/views/common/footer.jsp" %>
</body>
</html>