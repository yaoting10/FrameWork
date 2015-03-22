<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>中转费</title>
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
                        <a href="#">中装费</a> <span class="divider">/</span>
                    </li>
                    <li>
                        <a href="#">添加中转费</a>
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
                                <form:form commandName="handlingCost" method="post" id="handlingCost"
                                           action="/handlingCost/create" class="form-horizontal">
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">地区：</label>

                                        <div class="col-lg-4">
                                            <input id="area"
                                                   name="area"
                                                   type="text"value=""
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">空运单价：</label>
                                        <div class="col-lg-4">
                                            <input id="airPrice"
                                                   name="airPrice"
                                                   type="text"
                                                   value=""
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">空运最低价格：</label>

                                        <div class="col-lg-4">
                                            <input id="ariLowPrice"
                                                   name="ariLowPrice"
                                                   type="text"
                                                   value=""
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">汽运操作费：</label>

                                        <div class="col-lg-4">
                                            <input id="carOperatePrice"
                                                   name="carOperatePrice"
                                                   type="text"
                                                   value=""
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">汽运续重费：</label>
                                        <div class="col-lg-4">
                                            <input id="carWeightPrice"
                                                   name="carWeightPrice"
                                                   type="text"
                                                   value=""
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">汽运最低费用：</label>

                                        <div class="col-lg-4">
                                            <input id="carLowPrice"
                                                   name="carLowPrice"
                                                   type="text"
                                                   value=""
                                                   class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">包括地区：</label>
                                        <div class="col-lg-4">
                                            <textarea id="includeArea"
                                                       name="includeArea"
                                                       type="text"
                                                       value=""
                                                       class="form-control"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-lg-1 col-lg-offset-6">
                                            <button type="submit" id="submit_btn" class="btn btn-primary btn-sm btn-line">保存</button>
                                            <a class="btn btn-primary btn-sm btn-line" href="/handlingCost"
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
        $('#handlingCost').bootstrapValidator({
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                area: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '用户名为空'
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>