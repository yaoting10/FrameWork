<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/website/views/common/commonInclude.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>导出</title>
    <meta charset="utf-8"/>

    <jsp:include page="/WEB-INF/website/views/common/header.jsp"></jsp:include>

</head>
<body>
<div class="bg-light lter" id="wrap">
    <!-- top -->
    <jsp:include page="/WEB-INF/website/views/common/top.jsp">
        <jsp:param name="title" value="数据导出"/>
    </jsp:include>
    <!--left-->
    <jsp:include  page="/WEB-INF/website/views/common/left.jsp"></jsp:include>


    <div id="content">
        <div class="outer">
            <div class="inner bg-light lter">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="box inverse">
                            <header>
                                <div class="icons">
                                    <i class="fa fa-th-large"></i>
                                </div>
                                <h5>数据导出</h5>
                            </header>
                            <div id="div-2" class="body">
                                <form:form id="export_form" class="form-horizontal bv-form" action="/wayBill/export" method="post">
                                    <div class="form-group">
                                        <label class="control-label col-lg-4">选择导出模型</label>
                                        <div class="col-lg-4">
                                            <select name="exportCategory" id="exportCategory">
                                                <option value="WAY_BILL">运单导出</option>
                                                <option value="STATISTICS">统计导出</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div id="selectTime" class="form-group">
                                        <label class="control-label col-lg-4">选择时间</label>
                                        <div class="col-lg-3">
                                            <div class="input-group  input-append date">
                                                <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                                <input type="text" readonly="" name="from" id="from"
                                                       class="form-control"  value="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="input-group  input-append date">
                                                <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                                <input type="text" readonly="" name="to" id="to"
                                                       class="form-control"  value="">

                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-lg-1 col-lg-offset-4">
                                            <a id="submit_btn" class="btn btn-danger submit_btn">导出</a>
                                        </div>

                                    </div>

                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>

                                </form:form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/website/views/common/footer.jsp"/>

</div>

<script>
    $(document).ready(function() {
        $(".submit_btn").click(function () {
            $("#export_form").submit();
            return false;
        });

        $('#from').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            forceParse: false,
            format:"yyyy-mm-dd"
        });
        $('#to').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            forceParse: false,
            format:"yyyy-mm-dd"
        });

        $("#exportCategory").click(function () {
            if($("#exportCategory").val()=="EXPECT_STATS"){
                $("#selectTime").hide();
            }else{
                var date = new Date();
                $(".date-box").data("date", date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                $(".date-field").val(date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate());
                $("#selectTime").show();
            }
        });



    });
</script>
</body>
</html>
