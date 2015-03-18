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
    <title>业务员</title>
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
                        <a href="#">业务员列表</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户提现信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>姓名： <input id="userName"
                                                                                                    type="search"
                                                                                                    name="username"
                                                                                                    class="form-control input-sm"
                                                                                                    aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>编号： <input id="userNumber"
                                                                                                    type="search"
                                                                                                    name="serial"
                                                                                                    class="form-control input-sm"
                                                                                                    aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                           <%-- <div id="dataTable_filter" class="dataTables_filter"><label>提现状态： <select id="status"
                                                                                                      name="status"
                                                                                                      class="form-control input-sm"
                                                                                                      aria-controls="dataTable">
                                <option value="">-请选择-</option>
                                <option value="0">待处理</option>
                                <option value="1">接受</option>
                                <option value="2">拒绝</option>
                            </select>
                            </label> &nbsp;&nbsp;<a id="serachCondition"  class="btn btn-info btn-sm btn-line">搜索</a>
                            </div>--%>
                        </div>
                    </div>

                    <div class="row">
                        <br/>
                    </div>
                    <table id="user_table" class="table table-bordered table-striped">
                        <thead>
                        <th class="whitecustumer" data-dynatable-column="userNumber" data-dynatable-column>
                            编号
                        </th>
                        <th class="whitecustumer" data-dynatable-column="userName">
                            姓名
                        </th>
                        <th class="whitecustumer" data-dynatable-column="userSex">
                            性别
                        </th>
                        <th class="whitecustumer" data-dynatable-column="idCard">
                            身份证
                        </th>
                        <th class="whitecustumer" data-dynatable-column="phone">
                            电话号码
                        </th>
                        <th class="whitecustumer" data-dynatable-column="userType">
                            类型
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
    <form id="optAccpet" action="/admin/withdraw/accpet" method="post">
        <input hidden="true" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input hidden="true" id="awithdarwId" name="id"/>
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
                    operation +=  '<a class="btn btn-info btn-xs btn-line" href="/user/edit?userId='+record.id+'">编辑</a> &nbsp&nbsp';
                    operation +=  '<a class="btn btn-info btn-xs btn-line" href="#">删除</a>';
                    record.id = operation;
                }
                if(columns[i].id == "userType"){
                    if(record.userType == "1"){
                        record.userType = "管理员";
                    }
                    if(record.userType == "2"){
                        record.userType = "业务员";
                    }
                }

                if(columns[i].id == "userSex"){
                    if(record.userSex == "0"){
                        record.userSex = "男";
                    }
                    if(record.userSex == "1"){
                        record.userSex = "女";
                    }
                }
                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        var dynatable = $('#user_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/user/list'
            },
            writers: {
                _rowWriter: userRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var userName = $("#userName").val();
            if (userName == "") {
                dynatable.queries.remove("userName");
            } else {
                dynatable.queries.add("userName",userName);
            }
            var userNumber = $("#userNumber").val();
            if (userNumber == "") {
                dynatable.queries.remove("userNumber");
            } else {
                dynatable.queries.add("userNumber",userNumber);
            }
            dynatable.process();
        });
    });
</script>
