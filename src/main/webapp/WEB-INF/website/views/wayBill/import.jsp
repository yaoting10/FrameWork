<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>运单导入</title>
    <%@ include file="/WEB-INF/website/views/common/header.jsp" %>

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
                        <a href="#">运单管理</a> <span class="divider">/</span>
                    </li>
                    <li>
                        <a href="#">运单导入</a>
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
                                <form:form  method="post" id="file" action="/wayBill/import" class="form-horizontal" enctype="multipart/form-data">
                                    <div class="form-group row">
                                        <div class="col-lg-6 col-lg-offset-5">
                                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
                                                <div>
                                                     <span class="btn btn-default btn-file"><span class="fileinput-new">请选择文件</span> <span class="fileinput-exists">更改文件</span>
                                                        <input type="file" name="file">
                                                     </span>
                                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">移除文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-1 col-lg-offset-5">
                                            <button type="submit" id="submit_btn" class="btn btn-primary btn-sm btn-line">上传</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a class="btn btn-primary btn-sm btn-line" href="/wayBill"
                                               data-original-title="返回" title="">返回</a>
                                        </div>
                                    </div>
                                    <input type="hidden" id="id" name="id" value="${wayBill.id}"/>
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

       /* $('#createTime').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            forceParse: false,
            format:"yyyy-mm-dd"
        });*/

        $('#wayBill').bootstrapValidator({
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                file: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '文件不能为空'
                        }
                    }
                }

            }
        });
    });
</script>
</body>
</html>