<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>工单基本信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/worksheet/zlmtWorksheet/">工单基本信息列表</a></li>
    <li class="active"><a href="${ctx}/worksheet/zlmtWorksheet/form?id=${zlmtWorksheet.id}">一般故障处理申请单</a></li>
</ul><br/>

    <fieldset>
        <legend>审批申请</legend>
        <table class="table-form">
            <tr>
                <td class="tit">工单编码：</td><td>
                    ${zlmtWorksheet.worksheetCode}
            </td>
                <td>标题：</td><td >
                ${zlmtWorksheet.worksheetTitle}
            </td>
            </tr><tr>	<td class="tit">要求开始处理时间：</td><td>
            <fmt:formatDate value="${zlmtWorksheet.reqBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td><td class="tit">要求处理结束时间：</td><td>
            <fmt:formatDate value="${zlmtWorksheet.reqThruTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>

        </tr>
            <tr>
                <td class="tit">发起申请原因：</td>
                <td colspan="3">
                    ${zlmtWorksheet.worksheetReason}
                </td>
            </tr>
            <tr>
                <td class="tit">要求处理结果：</td>
                <td colspan="3">
                    ${zlmtWorksheet.worksheetReq}
                </td>
            </tr>
            <tr>
                <td class="tit">实际处理开始时间：</td>
                <td>
                    <fmt:formatDate value="${zlmtWorksheet.realBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                </td>
                <td class="tit">实际处理结束时间：</td>
                <td>
                    <fmt:formatDate value="${zlmtWorksheet.realThruTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            <tr>
                <td class="tit">备注信息：</td>
                <td colspan="3">
                    ${zlmtWorksheet.remarks}
                </td>
            </tr>
        </table>
    </fieldset>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <c:if test="${not empty zlmtWorksheet.id}">
        <act:histoicFlow procInsId="${zlmtWorksheet.act.procInsId}" />
    </c:if>
</body>
</html>