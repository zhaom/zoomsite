<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>工单基本信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/worksheet/zlmtWorksheet/">工单基本信息列表</a></li>
    <li class="active"><a href="${ctx}/worksheet/zlmtWorksheet/form?id=${zlmtWorksheet.id}">一般故障处理申请单</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="zlmtWorksheet" action="${ctx}/worksheet/zlmtWorksheet/saveAudit" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="act.taskId"/>
    <form:hidden path="act.taskName"/>
    <form:hidden path="act.taskDefKey"/>
    <form:hidden path="act.procInsId"/>
    <form:hidden path="act.procDefId"/>
    <form:hidden id="flag" path="act.flag"/>
    <sys:message content="${message}"/>
    <fieldset>
        <legend>审批申请</legend>
        <table class="table-form">
            <tr>
                <td class="tit">工单编码：</td><td>
                    ${zlmtWorksheet.worksheetCode}
            </td>
                <td>标题：</td><td>
                    ${zlmtWorksheet.worksheetTitle}
            </td>
            </tr><tr>	<td class="tit">要求开始处理时间：</td><td>
            <fmt:formatDate value="${zlmtWorksheet.reqBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td><td class="tit">要求处理结束时间：</td><td>
            <fmt:formatDate value="${zlmtWorksheet.reqThruTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </td>

        </tr>
            <tr>
                <td class="tit">故障现象：</td>
                <td colspan="3">
                    ${zlmtWorksheet.worksheetReason}
                </td>
            </tr>
            <tr>
                <td class="tit">处理要求：</td>
                <td colspan="3">
                        ${zlmtWorksheet.worksheetReq}
                </td>
            </tr>

<%--            <tr>
                <td class="tit">涉及资源：</td>
                <td colspan="3">
                    ${}
                </td>
            </tr>--%>
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
                    <form:textarea path="remarks" class="required" rows="5" maxlength="200" cssStyle="width:500px" disabled="true"/>
                </td>
            </tr>
            <tr>
                <td class="tit">您的意见</td>
                <td colspan="3">
                    <form:textarea path="act.comment" class="required" rows="5" maxlength="20" cssStyle="width:500px"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="审批通过" onclick="$('#flag').val('yes')"/>&nbsp;
        <input id="btnSubmit2" class="btn btn-inverse" type="submit" value="审批不通过" onclick="$('#flag').val('no')"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <c:if test="${not empty zlmtWorksheet.id}">
        <act:histoicFlow procInsId="${zlmtWorksheet.act.procInsId}" />
    </c:if>
</form:form>
</body>
</html>