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
<div class="bg-light lter">
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
                        <%--<div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>区域： <input id="area"
                                                                                                   type="search"
                                                                                                   name="area"
                                                                                                   class="form-control input-sm"
                                                                                                   aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>业务员编号： <input id="userNumber"
                                                                                                      type="search"
                                                                                                      name="userNumber"
                                                                                                      class="form-control input-sm"
                                                                                                      aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>--%>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>起始时间：
                                <div class="input-group  input-append date">
                                    <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                    <input type="text" readonly="" name="beg" id="beg"
                                           class="form-control"  value="">

                                </div>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>结束时间：
                                <div class="input-group  input-append date">
                                    <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                    <input type="text" readonly="" name="end" id="end"
                                           class="form-control"  value="">

                                </div>
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
                <table id="statistics_table" class="table table-bordered table-striped">
                    <thead>

                    <th class="whitecustumer" data-dynatable-column="userNumber" data-dynatable-column>
                        业务员编号
                    </th>
                    <th class="whitecustumer" data-dynatable-column="wayBillCount">
                        总单数
                    </th>
                    <th class="whitecustumer" data-dynatable-column="totalPrice">
                        总中转费
                    </th>
                    <th class="whitecustumer" data-dynatable-column="handlingCost">
                        总操作费
                    </th>
                    <th class="whitecustumer" data-dynatable-column="addedWeigh">
                        总续重费
                    </th>
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



        $('#beg').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            forceParse: false,
            format:"yyyy-mm-dd"
        });

        $('#end').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            maxDate: new Date(),
            forceParse: false,
            format:"yyyy-mm-dd"
        });


        function userRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            for (var i = 0, len = columns.length; i < len; i++) {

                if(columns[i].id =="totalPrice"){
                    record.totalPrice = record.totalPrice.toFixed(2) +" 元";
                }

                if(columns[i].id =="handlingCost"){
                    record.handlingCost = record.handlingCost.toFixed(2) +" 元";
                }

                if(columns[i].id =="addedWeigh"){
                    record.addedWeigh = record.addedWeigh.toFixed(2) +" 元";
                }
                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        var dynatable = $('#statistics_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/wayBill/statistics/list'
            },
            writers: {
                _rowWriter: userRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var beg = $("#beg").val();
            if (beg == "") {
                dynatable.queries.remove("beg");
            } else {
                dynatable.queries.add("beg", beg);
            }

            var end = $("#end").val();
            if (end == "") {
                dynatable.queries.remove("end");
            } else {
                dynatable.queries.add("end", end);
            }
            dynatable.process();
        });
    });
</script>
