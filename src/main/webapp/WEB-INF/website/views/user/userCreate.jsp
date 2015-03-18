<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>添加业务员</title>
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
    <div id="content">
        <div class="outer">
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">用户列表</a> <span class="divider">/</span>
                    </li>
                    <li>
                        <a href="#">添加业务员</a>
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
                                <div class="body collapse in" id="div-5" aria-expanded="true" style="">
                                    <form:form commandName="userVo" method="post" id="userVo"
                                               action="/user/create" class="form-horizontal">
                                        <div class="form-group row">
                                            <label class="control-label col-lg-2">用户名：</label>

                                            <div class="col-lg-4">
                                                <input id="userName"
                                                       name="userName"
                                                       type="text"value=""
                                                       class="form-control">
                                            </div>
                                            <label class="control-label col-lg-2">编号：</label>
                                            <div class="col-lg-4">
                                                <input id="userNumber"
                                                       name="userNumber"
                                                       type="text"
                                                       value=""
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
                                                    <option value="1">管理员</option>
                                                    <option value="2">业务员</option>
                                                </select>
                                            </div>
                                            <label class="control-label col-lg-2">性别：</label>

                                            <div class="col-lg-4">
                                                <select id="userSex"
                                                        name="userSex"
                                                        class="form-control input-sm"
                                                        aria-controls="dataTable">
                                                    <option value="0">男</option>
                                                    <option value="1">女</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="control-label col-lg-2">电话号码：</label>
                                            <div class="col-lg-4">
                                                <input id="phone"
                                                       name="phone"
                                                       type="text"
                                                       value=""
                                                       class="form-control">
                                            </div>
                                            <label class="control-label col-lg-2">身份证：</label>

                                            <div class="col-lg-4">
                                                <input id="idCard"
                                                       name="idCard"
                                                       type="text"
                                                       value=""
                                                       class="form-control">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="control-label col-lg-2">登录密码：</label>
                                            <div class="col-lg-4">
                                                <input id="password"
                                                       name="password"
                                                       type="text"
                                                       value=""
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
                                        <input type="hidden" id="csrf_Token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form:form>
                                </div>
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
                            message: '用户名为空'
                        }
                    }
                },
                userNumber: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '编号为空'
                        }
                    }
                },
                password: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '密码为空'
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>