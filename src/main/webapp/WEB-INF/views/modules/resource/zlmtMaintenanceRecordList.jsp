<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源维护信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/resource/zlmtMaintenanceRecord/">资源维护信息列表</a></li>
		<shiro:hasPermission name="resource:zlmtMaintenanceRecord:edit"><li><a href="${ctx}/resource/zlmtMaintenanceRecord/form">资源维护信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zlmtMaintenanceRecord" action="${ctx}/resource/zlmtMaintenanceRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>资源名称：</label><form:input path="resourceName" htmlEscape="false" maxlength="50" class="input-medium"/>
		<label>日期范围：&nbsp;</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
										 value="<fmt:formatDate value="${zlmtMaintenanceRecord.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
																	value="<fmt:formatDate value="${zlmtMaintenanceRecord.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资源名称</th>
				<th>运维原因</th>
				<th>运维操作记录</th>
				<th>运维结果</th>
				<th>操作人</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="resource:zlmtMaintenanceRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zlmtMaintenanceRecord">
			<tr>
				<td>
						${zlmtMaintenanceRecord.resource.resourceName}
				</td>
				<td>
						${zlmtMaintenanceRecord.maintenanceReason}
				</td>
				<td>
						${zlmtMaintenanceRecord.maintenanceOperation}
				</td>
				<td>
						${zlmtMaintenanceRecord.maintenanceResult}
				</td>
				<td>
						${zlmtMaintenanceRecord.user.name}
				</td>
				<td>
					<fmt:formatDate value="${zlmtMaintenanceRecord.fromTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<fmt:formatDate value="${zlmtMaintenanceRecord.thruTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</td>
				<shiro:hasPermission name="resource:zlmtMaintenanceRecord:edit"><td>
    				<a href="${ctx}/resource/zlmtMaintenanceRecord/form?id=${zlmtMaintenanceRecord.id}">修改</a>
					<a href="${ctx}/resource/zlmtMaintenanceRecord/delete?id=${zlmtMaintenanceRecord.id}" onclick="return confirmx('确认要删除该资源维护信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>