<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源维护信息管理</title>
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
			$("#btnQuery").click(function(){
				$.ajax({
					url: "${ctx}/resource/zlmtResource/getResourceIdByCode",
					data: {
						resourceCode:$("#resourceCode").val()
					},
					success: function(data) {
						alert(data);
						alert(data.code);
						if(data.code == "S"){
							$('#resource\\.id').val(data.resourceId);
						}else if(data.code == "N"){
							alert("输入的编码资源不存在！");
						}else{
							alert("输入的编码存在多条资源，请处理资源冲突！");
						}
					}
				});
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/resource/zlmtMaintenanceRecord/">资源维护信息列表</a></li>
		<li class="active"><a href="${ctx}/resource/zlmtMaintenanceRecord/form?id=${zlmtMaintenanceRecord.id}">资源维护信息<shiro:hasPermission name="resource:zlmtMaintenanceRecord:edit">${not empty zlmtMaintenanceRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="resource:zlmtMaintenanceRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zlmtMaintenanceRecord" action="${ctx}/resource/zlmtMaintenanceRecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="resource.id"  />
		<sys:message content="${message}"/>

		<div class="control-group">
			<label class="control-label">资源编码：</label>
			<div class="controls">
				<form:input path="resourceCode" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<input id="btnQuery" class="btn btn-primary" type="button" value="验 证"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维护人：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${zlmtMaintenanceRecord.user.id}" labelName="user.name" labelValue="${zlmtMaintenanceRecord.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">运维原因：</label>
			<div class="controls">
				<form:textarea path="maintenanceReason" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">运维操作记录：</label>
			<div class="controls">
				<form:textarea path="maintenanceOperation" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">运维结果：</label>
			<div class="controls">
				<form:textarea path="maintenanceResult" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="fromTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${zlmtMaintenanceRecord.fromTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="thruTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${zlmtMaintenanceRecord.thruTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="resource:zlmtMaintenanceRecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>