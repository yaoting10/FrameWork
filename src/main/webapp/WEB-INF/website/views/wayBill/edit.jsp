<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>运单详情</title>
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
                        <a href="#">运单管理</a> <span class="divider">/</span>
                    </li>
                    <li>
                        <a href="#">运单详情</a>
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
                                <form:form commandName="wayBill" method="post" id="wayBill"
                                           action="/wayBill/edit" class="form-horizontal">
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">运单号：</label>

                                        <div class="col-lg-4">
                                            <input id="awb"
                                                   name="awb"
                                                   readonly=""
                                                   type="text"value="${wayBill.awb}"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">重量：</label>
                                        <div class="col-lg-4">
                                            <input id="weight"
                                                   name="weight"
                                                   type="text"
                                                   readonly=""
                                                   value="${wayBill.weight}"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">详细地址：</label>

                                        <div class="col-lg-4">
                                            <input id="address"
                                                   name="address"
                                                   type="text"
                                                   readonly=""
                                                   value="${wayBill.address}"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">区域：</label>
                                        <div class="col-lg-4">
                                            <input id="area"
                                                   name="area"
                                                   readonly=""
                                                   type="text"
                                                   value="${wayBill.cost.area}"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">运输方式：</label>
                                        <div class="col-lg-4">
                                            <select id="type"
                                                    name="type"
                                                    class="form-control input-sm"
                                                    disabled
                                                    aria-controls="dataTable">
                                                <option <c:if test="${wayBill.type ==1}">selected="selected"</c:if> value="1">空运</option>
                                                <option <c:if test="${wayBill.type ==2}">selected="selected"</c:if> value="2">汽运</option>
                                            </select>
                                        </div>
                                        <label class="control-label col-lg-2">业务员编号：</label>
                                        <div class="col-lg-4">
                                            <input id="userNumber"
                                                   name="userNumber"
                                                   type="text"
                                                   readonly=""
                                                   value="${wayBill.user.userNumber}"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">操作费：</label>
                                        <div class="col-lg-4">
                                            <input id="handlingCost"
                                                   name="handlingCost"
                                                   type="text"
                                                   readonly=""
                                                   value="${wayBill.handlingCost}元"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">续重费：</label>
                                        <div class="col-lg-4">
                                            <input id="addedWeigh"
                                                   name="addedWeigh"
                                                   type="text"
                                                   readonly=""
                                                   value="${wayBill.addedWeigh}元"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">中转费：</label>
                                        <div class="col-lg-4">
                                            <input id="totalPrice"
                                                   name="totalPrice"
                                                   type="text"
                                                   readonly=""
                                                   value="${wayBill.total}元"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">运单时间：</label>
                                        <div class="col-lg-4">
                                            <div class="input-group input-append date">
                                                <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                                <input type="text" readonly="" name="createTime" id="createTime"
                                                       class="form-control"  value="<fmt:formatDate value="${wayBill.createTime}" pattern="yyyy-MM-dd"></fmt:formatDate>">
                                            </div>
                                        </div>

                                        <div class="col-lg-1 col-lg-offset-6">
                                           <%-- <button type="submit" id="submit_btn" class="btn btn-primary btn-sm btn-line">保存</button>--%>
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
                awb: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '运单号不能为空'
                        },
                        regexp:{
                            message: '运单号不能大于12',
                            regexp:'^\\w{1,12}$'
                        }
                    }
                },
                weight: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '重量不能为空'
                        }
                    }
                },
                area: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '区域不能为空'
                        }
                    }
                },
                weight: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '重量不能为空'
                        }
                    }
                },
                createTime: {
                    group: '.col-lg-2',
                    validators: {
                        notEmpty: {
                            message: '运单时间不能为空'
                        }
                    }
                }
            }
        });
    });
</script>
</body>
</html>