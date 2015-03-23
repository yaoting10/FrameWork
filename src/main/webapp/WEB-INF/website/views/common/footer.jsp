<%--
  Created by IntelliJ IDEA.
  User: lxl
  Date: 2014-12-09
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="Footer bg-light lter">
    <p>版权所有©ECCS   粤ICP备14069326号-1</p>
</footer><!-- /#footer -->
<!--Modernizr 2.8.2-->
<script src="/resources/website/lib/modernizr/modernizr.min.js"></script>

<!--jQuery 2.1.1 -->
<script src="/resources/website/lib/jquery/jquery.min.js"></script>

<!--Bootstrap -->
<script src="/resources/website/lib/bootstrap/js/bootstrap.min.js"></script>

<!-- MetisMenu -->
<script src="/resources/website/lib/metismenu/metisMenu.min.js"></script>

<!-- Screenfull -->
<script src="/resources/website/lib/screenfull/screenfull.js"></script>
<script src="/resources/website/lib/jquery.uniform/jquery.uniform.min.js"></script>
<script src="/resources/website/lib/chosen/chosen.jquery.min.js"></script>

<!--[if lt IE 9]>
<script src="/resources/website/lib/html5shiv/html5shiv.js"></script>
<script src="/resources/website/lib/respond/respond.min.js"></script>
<![endif]-->

<!--jquery dynatable-->
<script src="/resources/website/lib/jquery-dynatable/jquery.dynatable.js"></script>
<!--switch-->
<script src="/resources/website/lib/switch/js/bootstrap-switch.min.js"></script>

<!--time select-->
<script src="/resources/website/lib/moment/moment.min.js"></script>
<script src="/resources/website/lib/moment/langs.min.js"></script>
<script src="/resources/website/lib/daterangepicker/daterangepicker.js"></script>
<script src="/resources/website/lib/datepicker/js/bootstrap-datepicker.js"></script>
<script src="/resources/website/lib/timepicker/js/bootstrap-timepicker.min.js"></script>
<!--jquery loading -->
<script src="/resources/website/lib/loading/js/jquery.showLoading.min.js"></script>
<script src="/resources/website/lib/screenfull/screenfull.js"></script>
<script src="/resources/website/lib/plupload/plupload.full.min.js"></script>
<script src="/resources/website/lib/plupload/jquery.plupload.queue/jquery.plupload.queue.min.js"></script>
<script src="/resources/website/lib/jquery.gritter/js/jquery.gritter.min.js"></script>
<script src="/resources/website/lib/jquery.uniform/jquery.uniform.min.js"></script>
<script src="/resources/website/lib/jasny-bootstrap/js/jasny-bootstrap.min.js"></script>
<script src="/resources/website/lib/jquery-form/jquery.form.js"></script>
<script src="/resources/website/lib/formwizard/jquery.form.wizard.js"></script>
<script src="/resources/website/lib/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="/resources/website/lib/jquery-validation/src/localization/messages_ja.js"></script>
<script src="/resources/website/lib/holderjs/holder.js"></script>


<!-- Metis core scripts -->
<script src="/resources/website/js/core.min.js"></script>

<!-- Metis demo scripts -->
<script src="/resources/website/js/app.js"></script>

<!-- Bootstrap validation need-->
<link rel="stylesheet" href="/resources/website/lib/bootstrapvalidator/dist/css/bootstrapValidator.css"/>
<script type="text/javascript" src="/resources/website/lib/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="/resources/website/lib/bootstrapvalidator/dist/js/language/zh_CN.js"></script>

<script>
    $(document).ready(function () {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });
        //dynatable init config
        $.dynatableSetup({
            features: {
                paginate: true,
                sort: false,
                pushState: false,
                search: false,
                recordCount: true,
                perPageSelect: true
            },
            table: {
                defaultColumnIdStyle: 'camelCase',
                columns: null,
                headRowSelector: 'thead tr', // or e.g. tr:first-child
                bodyRowSelector: 'tbody tr',
                headRowClass: 'whitecustumer',
                copyHeaderClass:false
            },
            inputs: {
                queries: null,
                sorts: null,
                multisort: ['ctrlKey', 'shiftKey', 'metaKey'],
                page: null,
                queryEvent: 'blur change',
                recordCountTarget: null,
                recordCountPlacement: 'after',
                paginationLinkTarget: null,
                paginationLinkPlacement: 'after',//defalut after
                paginationPrev: '前一页',
                paginationNext: '下一页',
                paginationGap: [1,2,2,1],
                recordCountPageBoundTemplate: '{pageLowerBound} 至 {pageUpperBound}',
                recordCountPageUnboundedTemplate: '{pageLowerBound} 至 {pageUpperBound}',
                recordCountFilteredTemplate: ' (总共 {recordsTotal} 条)',
                recordCountText:"从",
                recordCountTotalTemplate: '条',
                searchTarget: null,
                searchPlacement: 'before',//defalut before
                perPageTarget: null,
                perPagePlacement: 'before',//defalut before
                perPageText: '每页: ',
                processingText: '加载中...',
                pageText: '页码: ',
                processingText: '加载中 <img src="/resources/website/lib/jquery-dynatable/loading.gif" />'
            },
            dataset: {
                ajax: true,
                ajaxOnLoad: true,
                ajaxMethod: 'GET',
                ajaxDataType: 'json',
//            totalRecordCount: null,
//            queries: null,
//            queryRecordCount: null,
//            page: null,
                perPageDefault: 10,
                perPageOptions: [10,20,50,100],
//            sorts: null,
//            sortsKeys: null,
//            sortTypes: {},
                records:[]
            },
            table: {
                defaultColumnIdStyle: 'trimDash'
            },
            params: {
                dynatable: 'dynatable',
                queries: "queries",//query condition model
                sorts: 'sorts',
                page: 'page', //current page,default page
                perPage: 'size',//size of per page,default perPage
                offset: 'offset',
                records: 'records',
                record: null,
                queryRecordCount: 'queryRecordCount',
                totalRecordCount: 'totalRecordCount'
            }
        });

        $('#fileUpload').uniform({
            fileDefaultHtml : "未选择",
            fileButtonHtml : "选择图片"
        });
    });
</script>
