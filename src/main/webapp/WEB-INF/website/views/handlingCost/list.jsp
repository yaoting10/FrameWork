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
    <title>中转费</title>
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
                        <a href="#">中转费列表</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户提现信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>地区： <input id="area"
                                                                                                   type="search"
                                                                                                   name="area"
                                                                                                   class="form-control input-sm"
                                                                                                   aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>空运单价： <input id="airPrice"
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
                <table id="handlingCost_table" class="table table-bordered table-striped">
                    <thead>
                    <th class="whitecustumer" data-dynatable-column="area" data-dynatable-column>
                        地区
                    </th>
                    <th class="whitecustumer" data-dynatable-column="airPrice">
                        空运单价
                    </th>
                    <th class="whitecustumer" data-dynatable-column="ariLowPrice">
                        空运最低价格
                    </th>
                    <th class="whitecustumer" data-dynatable-column="carOperatePrice">
                        汽运操作费
                    </th>
                    <th class="whitecustumer" data-dynatable-column="carWeightPrice">
                        汽运续重费
                    </th>
                    <th class="whitecustumer" data-dynatable-column="carLowPrice">
                        汽运最低费用
                    </th>
                    <th class="whitecustumer" data-dynatable-column="includeArea">
                        包括地区
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
<form id="deleteForm" action="/handlingCost/delete" method="post">
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
                    operation +=  '<a class="btn btn-info btn-xs btn-line" href="/handlingCost/edit?id='+record.id+'">编辑</a> &nbsp&nbsp';
                    operation +=  '<a class="btn btn-info btn-xs btn-line" onclick="deleteHandlingCost('+record.id+')">删除</a>';
                    record.id = operation;
                }


                if(columns[i].id =="airPrice"){
                    record.airPrice = record.airPrice.toFixed(2) +" 元/kg";
                }
                if(columns[i].id =="ariLowPrice"){
                    record.ariLowPrice = record.ariLowPrice.toFixed(2) +" 元";
                }

                if(columns[i].id =="carOperatePrice"){
                    record.carOperatePrice = record.carOperatePrice.toFixed(2) +" 元";
                }

                if(columns[i].id =="carWeightPrice"){
                    record.carWeightPrice = record.carWeightPrice.toFixed(2) +" 元/kg";
                }
                if(columns[i].id =="carLowPrice"){
                    record.carLowPrice = record.carLowPrice.toFixed(2) +" 元";
                }

                if(columns[i].id =="includeArea"){
                    if(record.includeArea.length>=30)
                        record.includeArea = record.includeArea.substring(0,30) +"...";
                }

                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        var dynatable = $('#handlingCost_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/handlingCost/list'
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
           /* var userNumber = $("#userNumber").val();
            if (userNumber == "") {
                dynatable.queries.remove("userNumber");
            } else {
                dynatable.queries.add("userNumber",userNumber);
            }*/
            dynatable.process();
        });
    });

    function deleteHandlingCost(id){
        $("#id").val(id);
        $("#deleteForm").submit();
    }
</script>
