<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源基本信息管理</title>
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
		<li class="active"><a href="${ctx}/resource/zlmtResource/">资源基本信息列表</a></li>
		<shiro:hasPermission name="resource:zlmtResource:edit"><li><a href="${ctx}/resource/zlmtResource/form">资源基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zlmtResource" action="${ctx}/resource/zlmtResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>资源编码：</label><form:input path="resourceCode" htmlEscape="false" maxlength="50" class="input-medium"/>
		<label>资源名称：</label><form:input path="resourceName" htmlEscape="false" maxlength="50" class="input-medium"/>
		<label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${zlmtResource.office.id}" labelName="office.name" labelValue="${zlmtResource.office.name}"
											title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资源编码</th>
				<th>资源名称</th>
				<th>采购时间</th>
				<th>上架时间</th>
				<th>设备状态</th>
				<th>所属单位</th>
				<th>资源类型</th>
				<th>型号</th>
				<shiro:hasPermission name="resource:zlmtResource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zlmtResource">
			<tr>
				<td>
						${zlmtResource.resourceCode}
				</td>
				<td>
						${zlmtResource.resourceName}
				</td>
				<td>
					<fmt:formatDate value="${zlmtResource.purchaseDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${zlmtResource.maintDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(zlmtResource.resourceStatus, 'resource_status', '')}
				</td>
				<td>
					${zlmtResource.office.name}
				</td>
				<td>
						${fns:getDictLabel(zlmtResource.resourceType, 'resource_type', '未知')}
				</td>
				<td>
						${zlmtResource.resourceModel}
				</td>
				<shiro:hasPermission name="resource:zlmtResource:edit"><td>
    				<a href="${ctx}/resource/zlmtResource/form?id=${zlmtResource.id}">修改</a>
					<a href="${ctx}/resource/zlmtResource/delete?id=${zlmtResource.id}" onclick="return confirmx('确认要删除该资源基本信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>