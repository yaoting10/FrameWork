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
    <title>运单列表</title>
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
                        <a href="#">运单列表</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户提现信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>运单号： <input id="awb"
                                                                                                   type="search"
                                                                                                   name="awb"
                                                                                                   class="form-control input-sm"
                                                                                                   aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>区域： <input id="area"
                                                                                                   type="search"
                                                                                                   name="area"
                                                                                                   class="form-control input-sm"
                                                                                                   aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <c:if test="${user.userType == 1}">
                            <div class="col-xs-4">
                                <div id="dataTable_filter" class="dataTables_filter"><label>业务员编号： <input id="userNumber"
                                                                                                       type="search"
                                                                                                       name="userNumber"
                                                                                                       class="form-control input-sm"
                                                                                                       aria-controls="dataTable"/>
                                </label>
                                </div>
                            </div>
                        </c:if>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>起始时间：
                                <div class="input-group  input-append date">
                                    <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                    <input type="text" readonly="" name="begDate" id="begDate"
                                           class="form-control"  value="">

                                </div>
                            </label>
                            </div>
                        </div>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>结束时间：
                                <div class="input-group  input-append date">
                                    <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                    <input type="text" readonly="" name="endDate" id="endDate"
                                           class="form-control"  value="">

                                </div>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-2">
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
                        运单号
                    </th>
                    <th class="whitecustumer" data-dynatable-column="weight">
                        重量
                    </th>
                    <th class="whitecustumer" data-dynatable-column="userNumber">
                        业务员编号
                    </th>
                    <th class="whitecustumer" data-dynatable-column="userName">
                        业务员姓名
                    </th>
                    <th class="whitecustumer" data-dynatable-column="address">
                        详细地址
                    </th>
                    <th class="whitecustumer" data-dynatable-column="area">
                        区域
                    </th>
                    <th class="whitecustumer" data-dynatable-column="createDate">
                        订单时间
                    </th>
                    <th class="whitecustumer" data-dynatable-column="type">
                        运输方式
                    </th>
                    <th class="whitecustumer" data-dynatable-column="totalPrice">
                        中转费
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
<input hidden="true" id="userId" name="userId" value="${user.id}"/>
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


        $('#begDate').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            forceParse: false,
            format:"yyyy-mm-dd"
        });

        $('#endDate').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            forceParse: false,
            format:"yyyy-mm-dd"
        });


        function userRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            for (var i = 0, len = columns.length; i < len; i++) {
                if(columns[i].id == "id"){
                    var operation = "";

                        operation +=  '<a class="btn btn-info btn-xs btn-line" href="/wayBill/edit?wayBillId='+record.id+'">编辑</a> &nbsp&nbsp';
                    <c:if test="${user.userType == 1}">
                        operation +=  '<a class="btn btn-info btn-xs btn-line" onclick="deleteWayBill('+record.id+')">删除</a>';
                    </c:if>
                    record.id = operation;
                }

                if(columns[i].id =="type"){
                    if(record.type ==2){
                        record.type = "空运";
                    }
                    if(record.type ==1){
                        record.type = "汽运";
                    }
                }

                if(columns[i].id =="totalPrice"){
                    record.totalPrice = record.totalPrice.toFixed(2) +" 元";
                }

                if(columns[i].id == "createDate"){
                    record.createDate =swtichDate(record.createDate,"date");

                }

                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }
        var userId = $("#userId").val();
        var dynatable = $('#wayBill_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/wayBill/list?userId=' + userId
            },
            writers: {
                _rowWriter: userRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var area = $("#area").val();
            if (area == "") {
                dynatable.queries.remove("area");
            } else {
                dynatable.queries.add("area",area);
            }
            var awb = $("#awb").val();
            if (awb == "") {
                dynatable.queries.remove("awb");
            } else {
                dynatable.queries.add("awb",awb);
            }

            var userNumber = $("#userNumber").val();
            if (userNumber == "") {
                dynatable.queries.remove("userNumber");
            } else {
                dynatable.queries.add("userNumber",userNumber);
            }

            var begDate = $("#begDate").val();
            if (begDate == "") {
                dynatable.queries.remove("begDate");
            } else {
                dynatable.queries.add("begDate", begDate);
            }

            var endDate = $("#endDate").val();
            if (endDate == "") {
                dynatable.queries.remove("endDate");
            } else {
                dynatable.queries.add("endDate", endDate);
            }
            dynatable.process();
        });
    });

    Date.prototype.format = function(format){
        var o = {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(), //day
            "D+" : this.getDate()-1, //day
            "h+" : this.getHours(), //hour
            "H+" : this.getHours()-8, //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S" : this.getMilliseconds() //millisecond
        }

        if(/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }

        for(var k in o) {
            if(new RegExp("("+ k +")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
            }
        }
        return format;
    }
    //-------------------------格式化日期（结束）----------------------------------------------//

    function swtichDate(millisecond,type){
        var nowDate = new Date();
        nowDate.setTime(millisecond);
        if(type == "date"){
            nowDate = nowDate.format("yyyy-MM-dd");
        }else if(type == "day"){
            nowDate = restTime(millisecond);
        }else{
            return;
        }
        return nowDate;
    }

    function deleteWayBill(id){
        $("#id").val(id);
        $("#deleteForm").submit();
    }
</script>
