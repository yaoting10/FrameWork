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
                                       class="form-control">
                            </div>
                            <label class="control-label col-lg-2">编号：</label>
                            <div class="col-lg-4">
                                <input name="userNumber"
                                       type="text"
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
                                       value="${user.phone}"
                                       class="form-control">
                            </div>
                            <label class="control-label col-lg-2">身份证：</label>

                            <div class="col-lg-4">
                                <input name="idCard"
                                       type="text"
                                       value="${user.idCard}"
                                       class="form-control">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="control-label col-lg-2">登录密码：</label>
                            <div class="col-lg-4">
                                <input name="password"
                                       type="text"
                                       value="${user.password}"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-lg-1 col-lg-offset-6">
                                <button type="submit" id="submit_btn" class="btn btn-primary btn-sm btn-line">保存</button>
                                <a class="btn btn-primary btn-sm btn-line" href="/user"
                                   data-original-title="返回" title="">返回</a>
                            </div>

                        </div>
                        <input hidden="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" id="csrf_Token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </div>
            </c:if>
           <%-- <c:if test="${empty User}">
                <div class="body collapse in" id="div-5" aria-expanded="true" style="">
                    <form:form class="form-horizontal">
                        <div class="form-group row">
                            <label class="control-label col-lg-2">用户名：</label>

                            <div class="col-lg-4">
                                <input type="text" readonly="" value=""
                                       class="form-control">
                            </div>
                            <label class="control-label col-lg-2">编号：</label>

                            <div class="col-lg-4">
                                <input type="text"
                                       value=""
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-2">类型：</label>

                            <div class="col-lg-4">
                                <input type="text" readonly=""
                                       value="<c:if test="${user.userType == 0}">管理员</c:if><c:if test="${user.userType == 1}">业务员</c:if>"/>"
                                class="form-control">
                            </div>
                            <label class="control-label col-lg-2">性别：</label>

                            <div class="col-lg-4">
                                <input type="text" readonly=""
                                       value="<c:if test="${user.userSex == 0}">男</c:if><c:if test="${user.userSex == 1}">女</c:if>"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-2">身份证：</label>

                            <div class="col-lg-4">
                                <input type="text" readonly=""
                                       value="${user.idCard}"
                                       class="form-control">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-lg-1 col-lg-offset-6">
                                <button type="submit" id="submit_btn" class="btn btn-primary btn-sm btn-line">保存</button>
                                <a class="btn btn-primary btn-sm btn-line" href="/admin/loan/service/charge/"
                                   data-original-title="返回" title="">返回</a>
                            </div>

                        </div>
                    </form:form>
                </div>
            </c:if>--%>


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
<script>
    $(document).ready(function () {
        $('#userVo').bootstrapValidator({
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                userName: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                userNumber: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '编号不能为空'
                        }
                    }
                },
                password: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>