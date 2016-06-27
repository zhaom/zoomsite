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
	<form:form id="inputForm" modelAttribute="zlmtWorksheet" action="${ctx}/worksheet/zlmtWorksheet/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden id="flag" path="act.flag"/>
		<sys:message content="${message}"/>
	<fieldset>
		<legend>一般故障处理单</legend>
		<table class="table-form">
			<tr>
				<td class="tit">工单编码：</td><td>
				<form:input path="worksheetCode" htmlEscape="false" maxlength="64" class="input-small "/>
			</td>
				<td>标题：</td><td>
				<form:input path="worksheetTitle" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</td>
			</tr><tr>	<td class="tit">要求开始处理时间：</td><td>
				<input name="reqBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${zlmtWorksheet.reqBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</td><td class="tit">要求处理结束时间：</td><td>
				<input name="reqThruTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${zlmtWorksheet.reqThruTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</td>

			</tr>
			<tr>
				<td class="tit">故障现象：</td>
				<td colspan="3">
					<form:input path="worksheetReason" htmlEscape="false" maxlength="256" class="input-xlarge" style="width:720px;"/>
				</td>
			</tr>
			<tr>
				<td class="tit">处理要求：</td>
				<td colspan="3">
					<form:input path="worksheetReq" htmlEscape="false" maxlength="256" class="input-xlarge " style="width:720px;"/>
				</td>
			</tr>
			<tr>
				<td class="tit">涉及资源：</td>
				<td colspan="3">
					<input type="input" name="resources" id="resources">逗号分隔添加多个资源的编码
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
					<form:textarea path="remarks" class="required" rows="5" maxlength="200" cssStyle="width:500px"/>
				</td>
			</tr>
			</table>
		</fieldset>

		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交申请" onclick="$('#flag').val('yes')"/>&nbsp;
				<c:if test="${not empty zlmtWorksheet.id}">
					<input id="btnSubmit2" class="btn btn-inverse" type="submit" value="销毁申请" onclick="$('#flag').val('no')"/>&nbsp;
				</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<c:if test="${not empty zlmtWorksheet.id}">
			<act:histoicFlow procInsId="${zlmtWorksheet.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>