<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/3/18
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>订单列表</title>
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
                        <a href="#">订单列表</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户提现信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>订单号： <input id="awb"
                                                                                                   type="search"
                                                                                                   name="area"
                                                                                                   class="form-control input-sm"
                                                                                                   aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>重量： <input id="weight"
                                                                                                   type="search"
                                                                                                   name="airPrice"
                                                                                                   class="form-control input-sm"
                                                                                                   aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <a id="serachCondition"  class="btn btn-info btn-sm btn-line">搜索</a>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <br/>
                </div>
                <table id="wayBill_table" class="table table-bordered table-striped">
                    <thead>
                    <th class="whitecustumer" data-dynatable-column="awb" data-dynatable-column>
                        订单
                    </th>
                    <th class="whitecustumer" data-dynatable-column="weight">
                        重量
                    </th>
                    <th class="whitecustumer" data-dynatable-column="address">
                        详细地址
                    </th>
                    <th class="whitecustumer" data-dynatable-column="area">
                        区域
                    </th>
                    <th class="whitecustumer" data-dynatable-column="createTime">
                        订单时间
                    </th>
                    <th class="whitecustumer" data-dynatable-column="userNumber">
                        业务员编号
                    </th>
                    <th class="whitecustumer" data-dynatable-column="id">操作</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- /.inner -->
    </div>
    <!-- /.outer -->
</div>
<!-- /#content -->
<form id="deleteForm" action="/wayBill/delete" method="post">
    <input hidden="true" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input hidden="true" id="id" name="id"/>
</form>
</div>
<%@ include file="/WEB-INF/website/views/common/footer.jsp" %>

<script src="/resources/website/js/core.js"></script>
<script src="/resources/website/js/app.js"></script>
</body>
</html>

<script>
    $(document).ready(function() {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });

        function userRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            for (var i = 0, len = columns.length; i < len; i++) {
                if(columns[i].id == "id"){
                    var operation = "";
                    operation +=  '<a class="btn btn-info btn-xs btn-line" href="/wayBill/edit?id='+record.id+'">编辑</a> &nbsp&nbsp';
                    operation +=  '<a class="btn btn-info btn-xs btn-line" onclick="deleteWayBill('+record.id+')">删除</a>';
                    record.id = operation;
                }

                if(columns[i].id == "cost"){
                    record.id = record.id.area
                }
                if(columns[i].id == "user"){
                    record.id = record.id.userNumber
                }
                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        var dynatable = $('#wayBill_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/wayBill/list'
            },
            writers: {
                _rowWriter: userRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var userName = $("#area").val();
            if (userName == "") {
                dynatable.queries.remove("area");
            } else {
                dynatable.queries.add("area",area);
            }
            /*var userNumber = $("#userNumber").val();
            if (userNumber == "") {
                dynatable.queries.remove("userNumber");
            } else {
                dynatable.queries.add("userNumber",userNumber);
            }*/
            dynatable.process();
        });
    });

    function deleteWayBillt(id){
        $("#id").val(id);
        $("#deleteForm").submit();
    }
</script>
