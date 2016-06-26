<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工单处理记录信息管理</title>
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
		<li><a href="${ctx}/worksheet/zlmtWorksheetNote/">工单处理记录信息列表</a></li>
		<li class="active"><a href="${ctx}/worksheet/zlmtWorksheetNote/form?id=${zlmtWorksheetNote.id}">工单处理记录信息<shiro:hasPermission name="worksheet:zlmtWorksheetNote:edit">${not empty zlmtWorksheetNote.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="worksheet:zlmtWorksheetNote:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zlmtWorksheetNote" action="${ctx}/worksheet/zlmtWorksheetNote/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">工单id：</label>
			<div class="controls">
				<form:input path="worksheetId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理意见：</label>
			<div class="controls">
				<form:input path="noteContent" htmlEscape="false" maxlength="512" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意见类型 : 处理、审批意见、备注、附件、参考：</label>
			<div class="controls">
				<form:select path="noteType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('note_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息 : 备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="worksheet:zlmtWorksheetNote:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>